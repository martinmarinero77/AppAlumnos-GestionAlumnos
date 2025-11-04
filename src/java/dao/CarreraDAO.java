package dao;

import modelo.Carrera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarreraDAO {

    public boolean registrar(Carrera carrera) {
        String sql = "INSERT INTO carrera (nombre, facultad_idfacultad) VALUES (?, ?)";
        boolean exito = false;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carrera.getNombre());
            pstmt.setInt(2, carrera.getFacultad_idfacultad());
            exito = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }

    public List<Carrera> consultar() {
        List<Carrera> carreras = new ArrayList<>();
        String sql = "SELECT * FROM carrera ORDER BY nombre";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                carreras.add(new Carrera(
                        rs.getInt("idcarrera"),
                        rs.getString("nombre"),
                        rs.getInt("facultad_idfacultad")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carreras;
    }

    public Carrera buscarPorId(int idcarrera) {
        Carrera carrera = null;
        String sql = "SELECT * FROM carrera WHERE idcarrera = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idcarrera);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    carrera = new Carrera(
                            rs.getInt("idcarrera"),
                            rs.getString("nombre"),
                            rs.getInt("facultad_idfacultad"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carrera;
    }

    public boolean actualizar(Carrera carrera) {
        String sql = "UPDATE carrera SET nombre = ?, facultad_idfacultad = ? WHERE idcarrera = ?";
        boolean exito = false;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carrera.getNombre());
            pstmt.setInt(2, carrera.getFacultad_idfacultad());
            pstmt.setInt(3, carrera.getIdcarrera());
            exito = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }

    public boolean eliminar(int idcarrera) {
        String sql = "DELETE FROM carrera WHERE idcarrera = ?";
        boolean exito = false;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idcarrera);
            exito = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }
}
