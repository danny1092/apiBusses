package models;

public class TramosRuta {
  private int id;
  private int idRuta;
  private String nombreRuta;
  private int idOrigen;
  private String nombreOrigen;
  private int idDestino;
  private String nombreDestino;
  private int tiempo;

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public int getIdRuta() { return idRuta; }
  public void setIdRuta(int idRuta) { this.idRuta = idRuta; }

  public String getNombreRuta() { return nombreRuta; }
  public void setNombreRuta(String nombreRuta) { this.nombreRuta = nombreRuta; }

  public int getIdOrigen() { return idOrigen; }
  public void setIdOrigen(int idOrigen) { this.idOrigen = idOrigen; }

  public String getNombreOrigen() { return nombreOrigen; }
  public void setNombreOrigen(String nombreOrigen) { this.nombreOrigen = nombreOrigen; }

  public int getIdDestino() { return idDestino; }
  public void setIdDestino(int idDestino) { this.idDestino = idDestino; }

  public String getNombreDestino() { return nombreDestino; }
  public void setNombreDestino(String nombreDestino) { this.nombreDestino = nombreDestino; }

  public int getTiempo() { return tiempo; }
  public void setTiempo(int tiempo) { this.tiempo = tiempo; }

}
