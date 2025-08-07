package models;

import java.math.BigDecimal;

public class Geofence {

  private int id;
  private String nombre;
  private BigDecimal latitud;
  private BigDecimal longitud;
  private double radio_metros;
  private boolean activa;

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public BigDecimal getLatitud() { return latitud; }
  public void setLatitud(BigDecimal latitud) { this.latitud = latitud; }

  public BigDecimal getLongitud() { return longitud; }
  public void setLongitud(BigDecimal longitud) { this.longitud = longitud; }

  public double getRadio() { return radio_metros; }
  public void setRadio(double radio_metros) { this.radio_metros = radio_metros; }

  public boolean getActiva() { return activa; }
  public void setActiva(boolean activa) { this.activa = activa; }
}
