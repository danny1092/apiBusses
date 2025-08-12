CREATE DATABASE busses_db_test;
USE busses_db_test;

-- tabla de seguros
CREATE TABLE seguros (
id INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
costoTotal DECIMAL(9,2) NOT NULL,
costoCuota DECIMAL(6,2) NOT NULL
);

-- tabla de camiones
CREATE TABLE camiones (
id INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
numCam INT NOT NULL,
placa VARCHAR(12) NOT NULL,
idSeguro INT NOT NULL,
FOREIGN KEY (idSeguro) REFERENCES seguros(id)
);

-- tabla de choferes
CREATE TABLE choferes (
id INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
apellidos VARCHAR(50) NOT NULL,
idCamion INT NOT NULL,
FOREIGN KEY (idCamion) REFERENCES camiones(id)
);

-- tabla de geocercas
CREATE TABLE geocercas (
id INT PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(100) NOT NULL,
latitud DECIMAL(10,7) NOT NULL,
longitud DECIMAL(10,7) NOT NULL,
radio_metros INT NOT NULL,
activa BOOLEAN DEFAULT TRUE
);

-- 🛣️ Tabla de rutas definidas
CREATE TABLE rutas_definidas (
id INT PRIMARY KEY AUTO_INCREMENT,
nombre_ruta VARCHAR(100) NOT NULL,
geocercas_orden JSON NOT NULL -- Ej: [1,2,3]
);

-- 📍 Tabla de tramos entre geocercas
CREATE TABLE tramos_ruta (
id INT PRIMARY KEY AUTO_INCREMENT,
id_ruta INT NOT NULL,
origen_id INT NOT NULL,
destino_id INT NOT NULL,
tiempo_obj_min INT NOT NULL, -- minutos esperados
FOREIGN KEY (id_ruta) REFERENCES rutas_definidas(id),
FOREIGN KEY (origen_id) REFERENCES geocercas(id),
FOREIGN KEY (destino_id) REFERENCES geocercas(id)
);

-- 📋 Tabla de viajes realizados
CREATE TABLE viajes_realizados (
id INT PRIMARY KEY AUTO_INCREMENT,
id_camion INT NOT NULL, -- Requiere tabla de camiones si se desea relacionar
id_ruta INT NOT NULL,
fecha DATE NOT NULL,
FOREIGN KEY (id_ruta) REFERENCES rutas_definidas(id)
);

-- 📡 Tabla de pasos por geocerca
CREATE TABLE pasos_geocerca (
id INT PRIMARY KEY AUTO_INCREMENT,
id_viaje INT NOT NULL,
id_geocerca INT NOT NULL,
fecha_hora DATETIME NOT NULL,
orden_esperado INT NOT NULL,
FOREIGN KEY (id_viaje) REFERENCES viajes_realizados(id),
FOREIGN KEY (id_geocerca) REFERENCES geocercas(id)
);

-- 💰 Tabla de cobros por tiempo entre geocercas (tramos)
CREATE TABLE cobros_tiempo_tramo (
id INT PRIMARY KEY AUTO_INCREMENT,
id_viaje INT NOT NULL,
id_tramo INT NOT NULL,
minutos_esperado INT NOT NULL,
minutos_real INT NOT NULL,
minutos_exceso INT NOT NULL, -- > esperado
minutos_adelanto INT NOT NULL, -- < esperado
tarifa_minuto DECIMAL(6,2) NOT NULL,
monto_total DECIMAL(10,2) NOT NULL,
pagado BOOLEAN DEFAULT FALSE,
FOREIGN KEY (id_viaje) REFERENCES viajes_realizados(id),
FOREIGN KEY (id_tramo) REFERENCES tramos_ruta(id)
);

-- 🚨 Tabla de multas por no pasar por geocerca
CREATE TABLE multas_geocerca (
id INT PRIMARY KEY AUTO_INCREMENT,
id_viaje INT NOT NULL,
id_geocerca INT NOT NULL,
monto DECIMAL(10,2) NOT NULL,
fecha DATE NOT NULL,
pagado BOOLEAN DEFAULT FALSE,
comentario TEXT,
FOREIGN KEY (id_viaje) REFERENCES viajes_realizados(id),
FOREIGN KEY (id_geocerca) REFERENCES geocercas(id)
);



