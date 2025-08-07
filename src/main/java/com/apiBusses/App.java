package com.apiBusses;

import controllers.BussesContriller;
import controllers.GeofenceController;
import io.javalin.Javalin;

public class App {
  public static void main(String[] args) {
    Javalin app = Javalin.create().start(7000);

    // Establecer content-type por defecto a application/json
    app.before(ctx -> ctx.contentType("application/json"));

    // Geocercas
    app.get("/geocercas", GeofenceController::listarGeocercas);
    app.post("/geocercas", GeofenceController::registrarGeocerca);
    app.put("/geocercas", GeofenceController::actualizarGeocercas);
    app.delete("/geocercas", GeofenceController::eliminarGeocercas);
    //Rutas
    app.get("/rutas", GeofenceController::listarRutas);
    app.post("/rutas", GeofenceController::registrarRutas);
    app.put("/rutas", GeofenceController::actualizarRutas);
    app.delete("/rutas", GeofenceController::eliminarRutas);
    //TramosRutas
    app.get("/tramosRuta", GeofenceController::listarTramosRuta);
    app.post("/tramosRuta", GeofenceController::registrarTramosRuta);
    app.put("/tramosRuta", GeofenceController::actualizarTramosRuta);
    app.delete("/tramosRuta", GeofenceController::eliminarTramosRuta);

    //Seguros
    app.get("/seguros", BussesContriller::listarSeguros);
    app.post("/seguros", BussesContriller::registrarSeguros);
    app.put("/seguros", BussesContriller::actualizarSeguros);
    app.delete("/seguros", BussesContriller::eliminarSeguros);
    //Camiones
    app.get("/camiones", BussesContriller::listarCamiones);
    app.post("/camiones", BussesContriller::registrarCamiones);
    app.put("/camiones", BussesContriller::actualizarCamiones);
    app.delete("/camiones", BussesContriller::eliminarCamiones);
    //Choferes
    app.get("/choferes", BussesContriller::listarChoferes);
    app.post("/choferes", BussesContriller::registrarChoferes);
    app.put("/choferes", BussesContriller::actualizarChoferes);
    app.delete("/choferes", BussesContriller::eliminarChoferes);

    //TramosRutas en lista array
    app.get("/listarGeoRut", GeofenceController::listarGeoRut);


  }
}
