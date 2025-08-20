package org.example;

import org.example.configuracion.AdministradorConexiones;
import org.example.dao.AutoDAO;
import org.example.entities.Auto;
import org.example.entities.Marca;

import java.sql.Connection;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        System.out.println( "Hello World!" );

        //Connection miConexion = AdministradorConexiones.obtenerConexion();
        Auto auto =
            new Auto("CCCCC", "Blanco", 2025, 897, Marca.Honda, "Prius");

        AutoDAO autoDAO = new AutoDAO();
        AutoDAO.insertarAuto(auto);

        List<Auto> miLista=autoDAO.findAll();

        //Recorro la lista de autos
        if (!miLista.isEmpty()){
            for (Auto a : miLista){
                System.out.println(a.toString());
            }
        }

    }
}
