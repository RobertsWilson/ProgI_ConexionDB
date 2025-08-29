package org.example;

import org.example.configuracion.AdministradorConexiones;
import org.example.dao.AutoDAO;
import org.example.dao.AutoImpl;
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

        AutoImpl autoimpl = new AutoImpl();
/*
        // === Insertar Autos
        Auto auto1 = new Auto("ABC123", "Rojo", 2020, 25000, Marca.Toyota, "Corolla");
        autoimpl.insert(auto1);

        // === Lista de autos
        System.out.println("Lista de autos en la concesionaria:");
        List<Auto> autos = autoimpl.getAll();
        for (Auto a : autos) {
            System.out.println(a);
        }


        // === Buscar un auto por ID
        int idBuscado = auto1.getIdAuto(); // recién insertado
        Auto auto = autoimpl.getById(idBuscado);
        System.out.println("Auto encontrado con id " + idBuscado + ": " + auto);

        // === Modificar un auto
        auto.setColor("Negro");
        autoimpl.update(auto);
        System.out.println("Auto actualizado: " + autoimpl.getById(auto.getIdAuto()));

        // === Eliminar un auto
        autoimpl.delete(11);
        autoimpl.delete(12);
        autoimpl.delete(13);
        autoimpl.delete(14);
        autoimpl.delete(15);
        autoimpl.delete(16);
        autoimpl.delete(17);
        autoimpl.delete(18);
        autoimpl.delete(19);
        autoimpl.delete(20);
        autoimpl.delete(21);
        autoimpl.delete(22);

        System.out.println("Después de eliminar, autos en concesionaria:");
        List<Auto> autos = autoimpl.getAll();
        for (Auto a : autos) {
            System.out.println(a);
        }

*/
    }
}
