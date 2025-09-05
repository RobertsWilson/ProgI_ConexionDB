package org.example;

import org.example.configuracion.AdministradorConexiones;
import org.example.dao.AutoDAO;
import org.example.dao.AutoImpl;
import org.example.dao.ClienteDAO;
import org.example.dao.SeguroDAO;
import org.example.entities.Auto;
import org.example.entities.Cliente;
import org.example.entities.Marca;
import org.example.entities.Seguro;

import java.sql.Connection;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        AutoImpl autoImpl = new AutoImpl();

        Cliente c1 = new Cliente(15, "Juan", "Perez", "12345678");
        ClienteDAO clientedao = new ClienteDAO();
        clientedao.insert(c1);

        System.out.println("Hello World!");
        Auto auto =
                new Auto("CCCCCC", "Blanco", 2025, 897
                        , Marca.Honda, "Fit", c1);
        System.out.println(auto.getCliente().toString());
        /*
        // guardo en la BD
        AutoDAO autoDAO = new AutoDAO();
        AutoImpl autoImpl = new AutoImpl();
        autoDAO.insertarAuto(auto);
        // recorro la lista de autos
        //List<Auto> miLista = autoDAO.findAll();
        List<Auto> miLista = autoImpl.getAll();
        if (!miLista.isEmpty()) {
            for (Auto auto1 : miLista) {
                System.out.println(auto1.toString());
            }
        }
        //modifica auto
        Auto autoAModificar =
                new Auto(10, "AABBCC", "Gis", 2024, 897555
                        , Marca.Honda, "Fit");

        //autoDAO.update(autoAModificar);
        autoImpl.update(autoAModificar);
        //autoDAO.delete(14);

//    System.out.println("Auto encontrado: " + autoDAO.getById(25).toString());
        System.out.println("Auto encontrado: " + autoImpl.getById(25).toString());

        System.out.println("Lista de autos después de la modificación:");
        // recorro la lista de autos
        miLista = autoDAO.findAll();
        if (!miLista.isEmpty()) {
            for (Auto auto1 : miLista) {
                System.out.println(auto1.toString());
            }
        }
        System.out.println(" ----------AGREGANDO CON DAO IMPL ----------------");
        Cliente c = new Cliente(15, "Juan", "Perez", "12345678");
        ClienteDAO clientedao = new ClienteDAO();
        clientedao.insert(c);

        Auto autoTest =
                new Auto("CCCCCC", "Blanco", 2025, 0
                        , Marca.Toyota, "Corolla", c);
        autoImpl.insert(autoTest);



        System.out.println("----------AGREGANDO SEGURO CON DAO IMPL ----------------");
        Seguro s = new Seguro("Todo riesgo", 123450, "Mapfre");
        SeguroDAO segurodao = new SeguroDAO();
        segurodao.insert(s);

*/
    }

}