package org.example.dao;

import org.example.entities.Seguro;
import org.example.interfaces.AdmConexion;
import org.example.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeguroDAO implements DAO<Seguro, Integer>, AdmConexion {
    private Connection conn;

    private static final String SQL_INSERT =
            "INSERT INTO seguros (tipo, costoMensual, compania, idAuto) VALUES (?,?,?,?)";

    private static final String SQL_UPDATE =
            "UPDATE seguros SET tipo=?, costoMensual=?, compania=?, idAuto=? WHERE idSeguro=?";

    private static final String SQL_DELETE = "DELETE FROM seguros WHERE idSeguro=?";
    private static final String SQL_GETALL = "SELECT * FROM seguros";
    private static final String SQL_GETBYID = "SELECT * FROM seguros WHERE idSeguro=?";

    @Override
    public void insert(Seguro seguro) {
        conn = obtenerConexion();
        try (PreparedStatement pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, seguro.getTipo());
            pst.setDouble(2, seguro.getCostoMensual());
            pst.setString(3, seguro.getCompania());
            pst.setInt(4, seguro.getIdAuto());

            int res = pst.executeUpdate();
            if (res == 1) {
                System.out.println("Seguro insertado correctamente");
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    seguro.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando seguro", e);
        }
    }

    @Override
    public void update(Seguro seguro) {
        conn = obtenerConexion();
        try (PreparedStatement pst = conn.prepareStatement(SQL_UPDATE)) {
            pst.setString(1, seguro.getTipo());
            pst.setDouble(2, seguro.getCostoMensual());
            pst.setString(3, seguro.getCompania());
            pst.setInt(4, seguro.getIdAuto());
            pst.setInt(5, seguro.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando seguro", e);
        }
    }

    @Override
    public void delete(Integer id) {
        conn = obtenerConexion();
        try (PreparedStatement pst = conn.prepareStatement(SQL_DELETE)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando seguro", e);
        }
    }

    @Override
    public Seguro getById(Integer id) {
        conn = obtenerConexion();
        Seguro seguro = null;
        try (PreparedStatement pst = conn.prepareStatement(SQL_GETBYID)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                seguro = mapSeguro(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo seguro", e);
        }
        return seguro;
    }

    @Override
    public List<Seguro> getAll() {
        conn = obtenerConexion();
        List<Seguro> lista = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(SQL_GETALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                lista.add(mapSeguro(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando seguros", e);
        }
        return lista;
    }

    @Override
    public boolean existsById(Integer id) {
        return getById(id) != null;
    }

    private Seguro mapSeguro(ResultSet rs) throws SQLException {
        Seguro seguro = new Seguro();
        seguro.setId(rs.getInt("idSeguro"));
        seguro.setTipo(rs.getString("tipo"));
        seguro.setCostoMensual(rs.getDouble("costoMensual"));
        seguro.setCompania(rs.getString("compania"));
        seguro.setId(rs.getInt("idAuto"));
        return seguro;
    }
}
