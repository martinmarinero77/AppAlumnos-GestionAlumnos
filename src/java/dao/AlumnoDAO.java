package dao;


import modelo.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    /**
     * Registra un nuevo alumno en la base de datos.
     * @param alumno El objeto Alumno a registrar.
     * @return true si el registro fue exitoso, false en caso contrario.
     */
    public boolean registrar(Alumno alumno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean exito = false;
        String sql = "INSERT INTO alumno (nombre, registro, carrera_idcarrera) VALUES (?, ?, ?)";

        try {
            conn = Conexion.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, alumno.getNombre());
            pstmt.setString(2, alumno.getRegistro());
            pstmt.setInt(3, alumno.getCarrera_idcarrera());

            exito = pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar el alumno: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exito;
    }

    /**
     * Consulta y devuelve todos los alumnos de la base de datos.
     * @return Una lista de objetos Alumno.
     */
    public List<Alumno> consultar() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumno ORDER BY idalumnos";

        try {
            conn = Conexion.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idalumno = rs.getInt("idalumnos");
                String nombre = rs.getString("nombre");
                String registro = rs.getString("registro");
                int carrera_idcarrera = rs.getInt("carrera_idcarrera");

                Alumno alumno = new Alumno(idalumno, nombre, registro, carrera_idcarrera);
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar los alumnos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return alumnos;
    }

    public boolean eliminar(int idalumno) {
        String sql = "DELETE FROM alumno WHERE idalumnos = ?";
        boolean exito = false;
        try (Connection conn = Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idalumno);
            exito = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exito;
    }
}