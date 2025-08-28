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
        autoimpl.delete(auto.getIdAuto());
        System.out.println("Después de eliminar, autos en concesionaria:");
        autos = autoimpl.getAll();
        for (Auto a : autos) {
            System.out.println(a);
        }
    }
}
