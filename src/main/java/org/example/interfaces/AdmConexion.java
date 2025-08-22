package org.example.interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface AdmConexion {

  default Connection obtenerConexion(){

    //4 datos de conecion
    String dbDriver = "com.mysql.cj.jdbc.Driver";
    //cadena conexion a mi BD
    String dbCadenaConexion="jdbc:mysql://127.0.0.1:3306/progautos";
    //nombre de usuarioBD
    String dbUsuario="root";
    //pass bd
    String dbPass="root";

    Connection conn = null;

    try {
      Class.forName(dbDriver);

      conn = DriverManager.getConnection(dbCadenaConexion,dbUsuario,dbPass);

    } catch (ClassNotFoundException e) {
      System.out.println("No se encontro el driver de la BD");
      throw new RuntimeException(e);
    } catch (SQLException e) {
      System.out.println("No se puede conectar a la BD");
      throw new RuntimeException(e);
    }
    System.out.println("Conexion exitosa a la BD");
    return conn;
  }

}
