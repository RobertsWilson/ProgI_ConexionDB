package org.example.dao;

import org.example.configuracion.AdministradorConexiones;
import org.example.entities.Auto;
import org.example.entities.Marca;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AutoDAO {

  private static Connection conn;

  public static void insertarAuto(Auto auto) {

    //Establecer la conexion a la BD
    conn = AdministradorConexiones.obtenerConexion();

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
  public List<Auto> findAll(){

    //1-conectar
    conn = AdministradorConexiones.obtenerConexion();

    //2-Crear consulta SQL
    String sql = "SELECT * FROM autos order by patente";

    //3-Crear statment
    Statement st = null;
    ResultSet rs = null;

    List<Auto> listaAutos = new java.util.ArrayList<>();

    try {
      //crear instruccion
      st = conn.createStatement();


      //4-Ejecutar consulta y guarda el resultado en resultset
      rs = st.executeQuery(sql);


      //5-recorrer el resultset y guarda los autos en una lista
      while (rs.next()){
        Auto auto = new Auto();
        auto.setIdAuto(rs.getInt("idAuto"));
        auto.setAnio(rs.getInt("anio"));
        auto.setPatente(rs.getString("patente"));
        auto.setColor(rs.getString("color"));
        auto.setKilometraje(rs.getInt("kilometraje"));
        auto.setMarca(Marca.valueOf(rs.getString("marca")));
        auto.setModelo(rs.getString("modelo"));

        listaAutos.add(auto);
      }

      //cerrar el resultset y el statement
      st.close();
      rs.close();
      conn.close();



    } catch (SQLException e) {
      System.out.println("Error al crear el statement");
      throw new RuntimeException(e);
    }


    return listaAutos;
  }

}
