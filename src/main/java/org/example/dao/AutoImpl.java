package org.example.dao;

import org.example.configuracion.AdministradorConexiones;
import org.example.entities.Auto;
import org.example.interfaces.AdmConexion;
import org.example.interfaces.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutoImpl implements DAO<Auto, Integer>, AdmConexion {
  private Connection conn = null;
  @Override
  public List<Auto> getAll() {
    List<Auto> lista = new ArrayList<>();
    return lista;
  }

  @Override
  public void insert(Auto objeto) {
    Auto auto = objeto;

    //Establecer la conexion a la BD
    conn = obtenerConexion();

    //Crear String consulta SQL
    String sql =
        "INSERT INTO autos (idAuto,patente,color,anio,kilometraje,marca,modelo) " +
            "VALUES (" + auto.getIdAuto() + "," +
            "'" + auto.getPatente() + "'," +
            "'" + auto.getColor() + "'," +
            + auto.getAnio()+ "," +
            + auto.getKilometraje() + "," +
            "'" + auto.getMarca() + "'," +
            "'" + auto.getModelo() + "')" ;

    //paso 3: Crear instrucciones
    Statement st = null;
    try {
      st = conn.createStatement();
      st.execute(sql);

      //paso 5 cerrar conexion
      st.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  }

  @Override
  public void update(Auto objeto) {

  }

  @Override
  public void delete(Integer id) {

  }

  @Override
  public Auto getById(Integer id) {
    return null;
  }

  @Override
  public boolean existsById(Integer id) {
    return false;
  }

  @Override
  public Connection obtenerConexion() {
    return null;
  }
}
