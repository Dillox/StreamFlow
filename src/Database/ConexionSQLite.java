package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {

    private static final String URL = "jdbc:sqlite:streamflow.db";

    public static Connection conectar() {

        Connection conexion = null;

        try {

            conexion = DriverManager.getConnection(URL);
            System.out.println("Conexión establecida con SQLite.");

        } catch (SQLException e) {

            System.out.println("Error al conectar con la base de datos.");
            e.printStackTrace();

        }

        return conexion;
    }

}