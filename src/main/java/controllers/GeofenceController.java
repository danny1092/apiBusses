package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.DBConnection;
import models.BusRoutes;
import models.Geofence;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.TramosRuta;

public class GeofenceController {
  private static final ObjectMapper objectMapper = new ObjectMapper();

    // 1. Ver geocercas
  public static void listarGeocercas(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      String sql = "SELECT * FROM geocercas";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      List<Geofence> lista = new ArrayList<>();
      while (rs.next()) {
        Geofence geo = new Geofence();
        geo.setId(rs.getInt("id"));
        geo.setNombre(rs.getString("nombre"));
        geo.setLatitud(rs.getBigDecimal("latitud"));
        geo.setLongitud(rs.getBigDecimal("longitud"));
        geo.setRadio(rs.getDouble("radio_metros"));
        geo.setActiva(rs.getBoolean("activa"));
        lista.add(geo);
      }
      ctx.json(lista);
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  // 2. Registrar geocercas
  public static void registrarGeocerca(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Geofence geo = ctx.bodyAsClass(Geofence.class);
      String sql = "INSERT INTO geocercas (nombre, latitud, longitud, radio_metros) VALUES (?, ?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, geo.getNombre());
      stmt.setBigDecimal(2, geo.getLatitud());
      stmt.setBigDecimal(3, geo.getLongitud());
      stmt.setDouble(4, geo.getRadio());
      stmt.executeUpdate();
      ctx.status(201).result("Geocerca registrada");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //3. Actualizar geocercas
  public static void actualizarGeocercas(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Geofence geo = ctx.bodyAsClass(Geofence.class);
      String sql = "UPDATE geocercas SET nombre=?, latitud=?, longitud=?, radio_metros=?, activa=? WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, geo.getNombre());
      stmt.setBigDecimal(2, geo.getLatitud());
      stmt.setBigDecimal(3, geo.getLongitud());
      stmt.setDouble(4, geo.getRadio());
      stmt.setBoolean(5, geo.getActiva());
      stmt.setInt(6, geo.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Geocerca actualizada");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //4. Eliminar geocercas
  public static void eliminarGeocercas(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Geofence geo = ctx.bodyAsClass(Geofence.class);
      String sql = "DELETE FROM geocercas WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, geo.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Geocerca eliminada");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  // 1. Ver rutas
  public static void listarRutas(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      String sql = "SELECT * FROM rutas_definidas";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      List<BusRoutes> listaRutas = new ArrayList<>();
      while (rs.next()) {
        BusRoutes rut = new BusRoutes();
        rut.setId(rs.getInt("id"));
        rut.setNombre(rs.getString("nombre_ruta"));
        rut.setGeoCheck(rs.getString("geocercas_orden"));
        listaRutas.add(rut);
      }
      ctx.json(listaRutas);
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  // 2. Registrar rutas
  public static void registrarRutas(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      BusRoutes rut = ctx.bodyAsClass(BusRoutes.class);

      BusRoutes ruta = ctx.bodyAsClass(BusRoutes.class);
      String geocercasJson = objectMapper.writeValueAsString(ruta.getGeocercasOrden());

      String sql = "INSERT INTO rutas_definidas (nombre_ruta, geocercas_orden) VALUES (?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, rut.getNombre());
      stmt.setString(2, geocercasJson);

      stmt.executeUpdate();
      ctx.status(201).result("Ruta registrada");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //3. Actualizar rutas
  public static void actualizarRutas(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      BusRoutes rut = ctx.bodyAsClass(BusRoutes.class);
      String sql = "UPDATE rutas_definidas SET nombre_ruta=?, geocercas_orden=? WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, rut.getNombre());
      stmt.setString(2, rut.getGeoCheck());
      stmt.setInt(3, rut.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Ruta actualizada");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //4. Eliminar rutas
  public static void eliminarRutas(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      BusRoutes rut = ctx.bodyAsClass(BusRoutes.class);
      String sql = "DELETE FROM rutas_definidas WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, rut.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Ruta eliminada");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  // 1. ver tramos de ruta
  public static void listarTramosRuta(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      String sql = "SELECT tr.id as id, tr.id_ruta as idRuta, rd.nombre_ruta as nombreRuta, tr.origen_id as idOrigen, go.nombre as nombreOrigen, tr.destino_id as idDestino, gd.nombre as nombreDestino, tr.tiempo_obj_min as tiempo FROM tramos_ruta AS tr\n"
          + "LEFT JOIN rutas_definidas AS rd\n"
          + "ON tr.id_ruta = rd.id\n"
          + "LEFT JOIN geocercas AS go\n"
          + "ON tr.origen_id = go.id \n"
          + "LEFT JOIN geocercas AS gd\n"
          + "ON tr.destino_id = gd.id";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      List<TramosRuta> lista = new ArrayList<>();
      while (rs.next()) {
        TramosRuta tra = new TramosRuta();
        tra.setId(rs.getInt("id"));
        tra.setIdRuta(rs.getInt("idRuta"));
        tra.setNombreRuta(rs.getString("nombreRuta"));
        tra.setIdOrigen(rs.getInt("idOrigen"));
        tra.setNombreOrigen(rs.getString("nombreOrigen"));
        tra.setIdDestino(rs.getInt("idDestino"));
        tra.setNombreDestino(rs.getString("nombreDestino"));
        tra.setTiempo(rs.getInt("tiempo"));
        lista.add(tra);
      }
      ctx.json(lista);
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  // 2. Registrar tramos de ruta
  public static void registrarTramosRuta(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      TramosRuta tra = ctx.bodyAsClass(TramosRuta.class);
      String sql = "INSERT INTO tramos_ruta (id_ruta, origen_id, destino_id, tiempo_obj_min) VALUES (?, ?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, tra.getIdRuta());
      stmt.setInt(2, tra.getIdOrigen());
      stmt.setInt(3, tra.getIdDestino());
      stmt.setInt(4, tra.getTiempo());
      stmt.executeUpdate();
      ctx.status(201).result("Tramo ruta registrada");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //3. Actualizar tramos de ruta
  public static void actualizarTramosRuta(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      TramosRuta rut = ctx.bodyAsClass(TramosRuta.class);
      String sql = "UPDATE tramos_ruta SET id_ruta=?, origen_id=?, destino_id=?, tiempo_obj_min=? WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, rut.getIdRuta());
      stmt.setInt(2, rut.getIdOrigen());
      stmt.setInt(3, rut.getIdDestino());
      stmt.setInt(4, rut.getTiempo());
      stmt.setInt(5, rut.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Tramo ruta actualizada");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //4. Eliminar tramos de ruta
  public static void eliminarTramosRuta(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      TramosRuta rut = ctx.bodyAsClass(TramosRuta.class);
      String sql = "DELETE FROM tramos_ruta WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, rut.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Tramo ruta eliminada");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }


  // desfragmentar orden geocercas
  public static void listarGeoRut(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      String sql = "SELECT * FROM rutas_definidas";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      List<BusRoutes> listaRutas = new ArrayList<>();
      while (rs.next()) {
        BusRoutes rut = new BusRoutes();
        rut.setId(rs.getInt("id"));
        rut.setNombre(rs.getString("nombre_ruta"));
        rut.setGeoCheck(rs.getString("geocercas_orden"));
        // Obtiene el String JSON del campo
        String jsonString = rs.getString("geocercas_orden");

        // Convierte el String JSON a una lista de enteros
        List<Integer> geocercasOrden = objectMapper.readValue(jsonString, new TypeReference<List<Integer>>() {});
        // Asigna la lista de enteros al objeto BusRoutes
        rut.setGeocercasOrden(geocercasOrden);

        listaRutas.add(rut);
      }
      ctx.json(listaRutas);
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }
}
