package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbalumnos?serverTimezone=UTC&useSSL=false";
    // Credenciales de la base de datos
    private static final String USER = "root";
    private static final String PASS = "42081681";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: no se encontró el driver de la base de datos");
            throw new SQLException("Driver no encontrado", e);
        }
        // Devuelve una nueva conexión cada vez que se llama
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
