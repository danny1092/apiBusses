package controllers;

import database.DBConnection;
import io.javalin.http.Context;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Buss;
import models.Driver;
import models.Geofence;
import models.Insurance;

public class BussesContriller {
  // 1. Ver seguros
  public static void listarSeguros(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      String sql = "SELECT * FROM seguros";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      List<Insurance> lista = new ArrayList<>();
      while (rs.next()) {
        Insurance ins = new Insurance();
        ins.setId(rs.getInt("id"));
        ins.setNombre(rs.getString("nombre"));;
        ins.setCostoTotal(rs.getDouble("costoTotal"));
        ins.setCostoCuota(rs.getDouble("costoCuota"));
        lista.add(ins);
      }
      ctx.json(lista);
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  // 2. Registrar seguros
  public static void registrarSeguros(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Insurance ins = ctx.bodyAsClass(Insurance.class);
      String sql = "INSERT INTO seguros (nombre, costoTotal, costoCuota) VALUES (?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, ins.getNombre());
      stmt.setDouble(2, ins.getCostoTotal());
      stmt.setDouble(3, ins.getCostoCuota());
      stmt.executeUpdate();
      ctx.status(201).result("Seguro registrado");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //3. Actualizar Seguros
  public static void actualizarSeguros(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Insurance ins = ctx.bodyAsClass(Insurance.class);
      String sql = "UPDATE seguros SET nombre=?, costoTotal=?, costoCuota=? WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, ins.getNombre());
      stmt.setDouble(2, ins.getCostoTotal());
      stmt.setDouble(3, ins.getCostoCuota());
      stmt.setInt(4, ins.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Seguro actualizado");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //4. Eliminar Seguros
  public static void eliminarSeguros(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Insurance ins = ctx.bodyAsClass(Insurance.class);
      String sql = "DELETE FROM seguros WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, ins.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Seguro eliminado");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
}

  //1. Ver camiones
  public static void listarCamiones(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      String sql = "SELECT * FROM camiones";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      List<Buss> lista = new ArrayList<>();
      while (rs.next()) {
        Buss bus = new Buss();
        bus.setId(rs.getInt("id"));
        bus.setNombre(rs.getString("nombre"));;
        bus.setNumCam(rs.getInt("numCam"));
        bus.setPlaca(rs.getString("placa"));
        bus.setIdSeguro(rs.getInt("idSeguro"));
        lista.add(bus);
      }
      ctx.json(lista);
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //2. Insertar camiones
  public static void registrarCamiones(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Buss bus = ctx.bodyAsClass(Buss.class);
      String sql = "INSERT INTO camiones (nombre, numCam, placa, idSeguro) VALUES (?, ?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, bus.getNombre());
      stmt.setInt(2, bus.getNumCam());
      stmt.setString(3, bus.getPlaca());
      stmt.setInt(4, bus.getIdSeguro());
      stmt.executeUpdate();
      ctx.status(201).result("Camion registrado");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

//3. Actualizar camiones
  public static void actualizarCamiones(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Buss bus = ctx.bodyAsClass(Buss.class);
      String sql = "UPDATE camiones SET nombre=?, numCam=?, placa=?, idSeguro=? WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, bus.getNombre());
      stmt.setInt(2, bus.getNumCam());
      stmt.setString(3, bus.getPlaca());
      stmt.setInt(4, bus.getIdSeguro());
      stmt.setInt(5, bus.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Camion actualizado");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //4. Eliminar camiones
  public static void eliminarCamiones(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Buss bus = ctx.bodyAsClass(Buss.class);
      String sql = "DELETE FROM camiones WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, bus.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Camion eliminado");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }


  //1. Ver choferes
  public static void listarChoferes(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      String sql = "SELECT * FROM choferes";
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();

      List<Driver> lista = new ArrayList<>();
      while (rs.next()) {
        Driver dri = new Driver();
        dri.setId(rs.getInt("id"));
        dri.setNombre(rs.getString("nombre"));;
        dri.setApellidos(rs.getString("apellidos"));
        dri.setIdCamion(rs.getInt("idCamion"));
        lista.add(dri);
      }
      ctx.json(lista);
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //2. Registrar choferes
  public static void registrarChoferes(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Driver dri = ctx.bodyAsClass(Driver.class);
      String sql = "INSERT INTO choferes (nombre, apellidos, idCamion) VALUES (?, ?, ?)";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, dri.getNombre());
      stmt.setString(2, dri.getApellidos());
      stmt.setInt(3, dri.getIdCamion());
      stmt.executeUpdate();
      ctx.status(201).result("Chofer registrado");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

  //3. Actualizar choferes
  public static void actualizarChoferes(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Driver dri = ctx.bodyAsClass(Driver.class);
      String sql = "UPDATE choferes SET nombre=?, apellidos=?, idCamion=? WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, dri.getNombre());
      stmt.setString(2, dri.getApellidos());
      stmt.setInt(3, dri.getIdCamion());
      stmt.setInt(4, dri.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Chofer actualizado");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

//4. Eliminar choferes
  public static void eliminarChoferes(Context ctx) {
    try (Connection conn = DBConnection.getConnection()) {
      Driver dri = ctx.bodyAsClass(Driver.class);
      String sql = "DELETE FROM choferes WHERE id=?";
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, dri.getId());
      stmt.executeUpdate();
      ctx.status(201).result("Chofer eliminado");
    } catch (Exception e) {
      ctx.status(500).result("Error: " + e.getMessage());
    }
  }

}
