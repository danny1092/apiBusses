package models;

public class Buss {

  private int id;
  private String nombre;
  private int numCam;
  private String placa;
  private int idSeguro;

  public void setIdSeguro(int idSeguro) {
    this.idSeguro = idSeguro;
  }

  public int getIdSeguro() {
    return idSeguro;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public String getPlaca() {
    return placa;
  }

  public void setNumCam(int numCam) {
    this.numCam = numCam;
  }

  public int getNumCam() {
    return numCam;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
