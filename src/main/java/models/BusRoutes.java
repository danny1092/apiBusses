package models;

import java.util.List;

public class BusRoutes {

  private int id;
  private String nombre;
  private String geoCheck;
  private List<Integer> geocercasOrden; // <-- CAMBIO AQUI

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public String getGeoCheck() { return geoCheck; }
  public void setGeoCheck(String geoCheck) { this.geoCheck = geoCheck; }

  public List<Integer> getGeocercasOrden() {return geocercasOrden;}
  public void setGeocercasOrden(List<Integer> geocercasOrden) {
    this.geocercasOrden = geocercasOrden;
  }

}
