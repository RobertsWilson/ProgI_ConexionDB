package org.example.entities;

import java.util.Objects;

public class Seguro implements Comparable {

  private int idSeguros;
  private String tipo;
  private double costoMensual;
  private String compañia;

  private Integer idAuto;

  public Seguro() {
  }

  public Seguro(String tipo, double costoMensual, String compania) {
    this.tipo = tipo;
    this.costoMensual = costoMensual;
    this.compañia = compania;
  }

  public int getIdSeguros() {
    return idSeguros;
  }

  public void setIdSeguros(int idSeguros) {
    this.idSeguros = idSeguros;
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

  public String getCompañia() {
    return compañia;
  }

  public void setCompañia(String compañia) {
    this.compañia = compañia;
  }

  public Integer getIdAuto() { return idAuto; }

  public void setIdAuto(Integer idAuto) { this.idAuto = idAuto; }

  @Override
  public String toString() {
    return "Seguro{" +
            "idSeguros=" + idSeguros +
            ", tipo='" + tipo + '\'' +
            ", costoMensual=" + costoMensual +
            ", compañia='" + compañia + '\'' +
            ", idAuto=" + idAuto +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Seguro)) return false;
    Seguro seguro = (Seguro) o;
    return getIdSeguros() == seguro.getIdSeguros() && getCostoMensual() == seguro.getCostoMensual() && Objects.equals(getTipo(), seguro.getTipo()) && Objects.equals(getCompañia(), seguro.getCompañia());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIdSeguros(), getTipo(), getCostoMensual(), getCompañia());
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }
}
