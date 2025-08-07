package models;

public class Driver {

  private int id;
  private String nombre;
  private String apellidos;
  private int idCamion;

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }


  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  public String getApellidos() {
    return apellidos;
  }

  public void setIdCamion(int idCamion) {
    this.idCamion = idCamion;
  }

  public int getIdCamion() {
    return idCamion;
  }
}
