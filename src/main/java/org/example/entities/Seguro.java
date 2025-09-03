package org.example.entities;

import java.util.Objects;

public class Seguro implements Comparable {

  private int id;
  private String tipo;
  private double costoMensual;
  private String compania;

  private Integer idAuto;

  public Seguro() {
  }

  public Seguro(int id, String tipo, double costoMensual, String compania, Integer idAuto) {
    this.id = id;
    this.tipo = tipo;
    this.costoMensual = costoMensual;
    this.compania = compania;
    this.idAuto = idAuto;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public double getCostoMensual() {
    return costoMensual;
  }

  public void setCostoMensual(double costoMensual) {
    this.costoMensual = costoMensual;
  }

  public String getCompania() {
    return compania;
  }

  public void setCompania(String compania) {
    this.compania = compania;
  }

  public Integer getIdAuto() { return idAuto; }

  public void setIdAuto(Integer idAuto) { this.idAuto = idAuto; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Seguro)) return false;
    Seguro seguro = (Seguro) o;
    return getId() == seguro.getId() && getCostoMensual() == seguro.getCostoMensual() && Objects.equals(getTipo(), seguro.getTipo()) && Objects.equals(getCompania(), seguro.getCompania());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTipo(), getCostoMensual(), getCompania());
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }
}
