package org.example.dao;

import org.example.configuracion.AdministradorConexiones;
import org.example.entities.Auto;
import org.example.entities.Marca;
import org.example.interfaces.AdmConexion;
import org.example.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoImpl implements DAO<Auto, Integer>, AdmConexion {
  private Connection conn = null;

  private static final String SQL_INSERT = "INSERT INTO autos (patente,color,anio,kilometraje,marca,modelo) " +
      "VALUES(?,?,?,?,?,?)";

  private static final String SQL_UPDATE =
      "UPDATE autos SET " +
      "patente = ?" +
      "color = ?" +
      "anio = ?" +
      "kilometraje = ?" +
      "marca = ?" +
      "modelo = ?" +
      "WHERE idAuto = ?";
  private static final String SQL_DELETE = "DELETE FROM autos WHERE idAuto = ? ";
  private static final String SQL_GETALL = "SELECT * FROM autos order by patente";
  private static final String SQL_GETBYID = "SELECT * FROM autos WHERE idAuto";
  @Override
  public List<Auto> getAll() {

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
      while (rs.next()) {
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

  @Override
  public void insert(Auto objeto) {
    Auto auto = objeto;

    //Establecer la conexion a la BD
    conn = obtenerConexion();

    //Crear String consulta SQL
   /* String sql =
        "INSERT INTO autos (idAuto,patente,color,anio,kilometraje,marca,modelo) " +
            "VALUES (" + auto.getIdAuto() + "," +
            "'" + auto.getPatente() + "'," +
            "'" + auto.getColor() + "'," +
            +auto.getAnio() + "," +
            +auto.getKilometraje() + "," +
            "'" + auto.getMarca() + "'," +
            "'" + auto.getModelo() + "')";
*/
    //paso 3: Crear instrucciones
    PreparedStatement pst = null;
    try {

      //con la conexion llamo al prepareStatement pasandole la consulta SQL
      pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

      pst.setString(1, auto.getPatente());
      pst.setString(2, auto.getColor());
      pst.setInt(3, auto.getAnio());
      pst.setInt(4, auto.getKilometraje());
      pst.setString(5, auto.getMarca().toString());
      pst.setString(6, auto.getModelo());


      int resultado = pst.executeUpdate();

      if (resultado == 1){
        System.out.println("Auto insertado correctamente");
      }else {
        System.out.println("No se pudop insertar el auto");
      }

      //OBTENER LA CLAVE GENERADA
      ResultSet rs = pst.getGeneratedKeys();
      if(rs.next()){
        auto.setIdAuto(rs.getInt(1));
        System.out.println("El id asignado es: " + auto.getIdAuto());
      }

      //paso 5 cerrar conexion
      pst.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public void update(Auto objeto) {
    Auto auto = objeto;
    //Establecer la conexion

    //solo si el auto exite lo modifico
    if (this.existsById(auto.getIdAuto())) {

      String sql = "UPDATE autos SET " +
          "patente = '" + auto.getPatente() + "', " +
          "color = '" + auto.getColor() + "', " +
          "anio = " + auto.getAnio() + ", " +
          "kilometraje = " + auto.getKilometraje() + ", " +
          "marca = '" + auto.getMarca() + "', " +
          "modelo = '" + auto.getModelo() + "' " +
          "WHERE idAuto = " + auto.getIdAuto();
      conn = AdministradorConexiones.obtenerConexion();


      //Creao un statementn
      Statement st = null;

      try {
        st = conn.createStatement();
        st.execute(sql);
        st.close();
        conn.close();
      } catch (SQLException e) {
        System.out.println("Error al crear el statement");
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public void delete(Integer id) {
    Connection conn = this.obtenerConexion();
/*
    String sql = "DELETE FROM autos WHERE idAuto = " + id;
    Statement st = null;
*/
    try {
      PreparedStatement pst = conn.prepareStatement(SQL_DELETE);
      pst = conn.prepareStatement();
      pst.execute();
      st.close();
      conn.close();

    } catch (SQLException e) {
      System.out.println("Error al crear el statement");
      throw new RuntimeException(e);
    }
  }

  @Override
  public Auto getById(Integer id) {
    conn = AdministradorConexiones.obtenerConexion();

    String sql = "SELECT * FROM autos WHERE idAuto = " + id;

    Statement st = null;
    ResultSet rs = null;
    Auto auto = new Auto();

    try {
      st = conn.createStatement(); //CREO STATEMENT
      rs = st.executeQuery(sql);  //EJECUTO CONSULTA
      //SI LA CONSULTA DEVUELVE AL MENOS UN REGISTRO, EXISTE

      if (rs.next()) {
        //Asigno datos al auto
        auto.setIdAuto(rs.getInt("idAuto"));
        auto.setPatente((rs.getString("patente")));
        auto.setColor(rs.getString("color"));
        auto.setMarca(Marca.valueOf(rs.getString("marca")));
        auto.setAnio(rs.getInt("anio"));
        auto.setKilometraje(rs.getInt("kilometraje"));
        auto.setModelo(rs.getString("modelo"));

      }

      rs.close();
      st.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return auto;

  }

  @Override
  public boolean existsById(Integer id) {
    conn = AdministradorConexiones.obtenerConexion();

    String sql = "SELECT * FROM autos WHERE idAuto = " + id;

    Statement st = null;
    ResultSet rs = null;
    boolean existe = false;
    try {
      st = conn.createStatement(); //CREO STATEMENT
      rs = st.executeQuery(sql);  //EJECUTO CONSULTA
      //SI LA CONSULTA DEVUELVE AL MENOS UN REGISTRO, EXISTE

      if (rs.next()) {
        existe = true;
      }

      rs.close();
      st.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return existe;
  }

  @Override
  public Connection obtenerConexion() {
    return AdmConexion.super.obtenerConexion();
  }
}


