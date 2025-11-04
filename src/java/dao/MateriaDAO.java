package dao;

import modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {

    public boolean registrar(Materia materia) {
        String sql = "INSERT INTO materia (nombre) VALUES (?)";
        boolean exito = false;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, materia.getNombre());
            exito = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }

    public List<Materia> consultar() {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT * FROM materia ORDER BY nombre";
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                materias.add(new Materia(
                        rs.getInt("idmateria"),
                        rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
    }

    public Materia buscarPorId(int idmateria) {
        Materia materia = null;
        String sql = "SELECT * FROM materia WHERE idmateria = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idmateria);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    materia = new Materia(
                            rs.getInt("idmateria"),
                            rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materia;
    }

    public boolean actualizar(Materia materia) {
        String sql = "UPDATE materia SET nombre = ? WHERE idmateria = ?";
        boolean exito = false;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, materia.getNombre());
            pstmt.setInt(2, materia.getIdmateria());
            exito = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }

    public boolean eliminar(int idmateria) {
        String sql = "DELETE FROM materia WHERE idmateria = ?";
        boolean exito = false;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idmateria);
            exito = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }
}
