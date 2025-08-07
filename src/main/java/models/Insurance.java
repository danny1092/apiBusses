package models;

public class Insurance {

  private int id;
  private String nombre;
  private double costoTotal;
  private double costoCuota;

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public double getCostoTotal() { return costoTotal; }
  public void setCostoTotal(double costoTotal) { this.costoTotal = costoTotal; }

  public double getCostoCuota() { return costoCuota; }
  public void setCostoCuota(double costoCuota) { this.costoCuota = costoCuota; }

}
