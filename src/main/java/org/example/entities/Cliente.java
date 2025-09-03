package org.example.entities;

import java.util.Objects;

public class Cliente implements Comparable {

  private int id;
  private String nombre;
  private String apellido;
  private String telefono;

  public Cliente() {
  }

  public Cliente(int id, String nombre, String apellido, String telefono) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Cliente)) return false;
    Cliente cliente = (Cliente) o;
    return getId() == cliente.getId() && getTelefono() == cliente.getTelefono() && Objects.equals(getNombre(), cliente.getNombre()) && Objects.equals(getApellido(), cliente.getApellido());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getNombre(), getApellido(), getTelefono());
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }
}
