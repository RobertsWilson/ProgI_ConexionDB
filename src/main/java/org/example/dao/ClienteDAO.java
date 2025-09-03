package org.example.dao;

import org.example.configuracion.AdministradorConexiones;
import org.example.entities.Cliente;
import org.example.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements DAO<Cliente,Integer> {

    private static final String SQL_INSERT =
            "INSERT INTO clientes (nombre, apellido, telefono) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE clientes SET nombre=?, apellido=?, telefono=? WHERE idCliente=?";
    private static final String SQL_DELETE =
            "DELETE FROM clientes WHERE id=?";
    private static final String SQL_GETALL =
            "SELECT * FROM clientes ORDER BY apellido, nombre";
    private static final String SQL_GETBYID =
            "SELECT * FROM clientes WHERE id=?";

    @Override
    public List<Cliente> getAll() {
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = AdministradorConexiones.obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(SQL_GETALL);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("idCliente"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setTelefono(rs.getString("telefono"));
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener clientes", e);
        }
        return lista;
    }

    @Override
    public void insert(Cliente cliente) {
        try (Connection conn = AdministradorConexiones.obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getApellido());
            pst.setString(3, cliente.getTelefono());

            int resultado = pst.executeUpdate();
            if (resultado == 1) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
                System.out.println("Cliente insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el cliente.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar cliente", e);
        }
    }

    @Override
    public void update(Cliente cliente) {
        if (!existsById(cliente.getId())) {
            System.out.println("Cliente no encontrado, no se puede actualizar.");
            return;
        }
        try (Connection conn = AdministradorConexiones.obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(SQL_UPDATE)) {

            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getApellido());
            pst.setString(3, cliente.getTelefono());
            pst.setInt(4, cliente.getId());

            int resultado = pst.executeUpdate();
            if (resultado == 1) {
                System.out.println("Cliente actualizado correctamente.");
            } else {
                System.out.println("No se pudo actualizar el cliente.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar cliente", e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection conn = AdministradorConexiones.obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(SQL_DELETE)) {

            pst.setInt(1, id);
            int resultado = pst.executeUpdate();
            if (resultado == 1) {
                System.out.println("Cliente eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el cliente.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar cliente", e);
        }
    }

    @Override
    public Cliente getById(Integer id) {
        Cliente cliente = null;
        try (Connection conn = AdministradorConexiones.obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(SQL_GETBYID)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTelefono(rs.getString("telefono"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener cliente por ID", e);
        }
        return cliente;
    }

    @Override
    public boolean existsById(Integer id) {
        boolean existe = false;
        try (Connection conn = AdministradorConexiones.obtenerConexion();
             PreparedStatement pst = conn.prepareStatement(SQL_GETBYID)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar existencia de cliente", e);
        }
        return existe;
    }
}

