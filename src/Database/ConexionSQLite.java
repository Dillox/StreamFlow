package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionSQLite {

    private static final String URL = "jdbc:sqlite:streamflow.db";
    private static boolean tablasInicializadas = false;

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL);
            if (!tablasInicializadas) {
                inicializarTablas(conexion);
                tablasInicializadas = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    private static void inicializarTablas(Connection conexion) {
        String sqlContenido = "CREATE TABLE IF NOT EXISTS Contenido ("
                + "id_contenido INTEGER PRIMARY KEY, "
                + "titulo       TEXT NOT NULL, "
                + "genero       TEXT NOT NULL, "
                + "calidad      TEXT NOT NULL, "
                + "tipo         TEXT NOT NULL)";

        String sqlUsuario = "CREATE TABLE IF NOT EXISTS Usuario ("
                + "id_usuario INTEGER PRIMARY KEY, "
                + "nombre     TEXT NOT NULL, "
                + "plan       TEXT NOT NULL)";

        try (Statement st = conexion.createStatement()) {
            st.execute(sqlContenido);
            st.execute(sqlUsuario);
        } catch (SQLException e) {
            System.out.println("Error al inicializar tablas: " + e.getMessage());
        }
    }
}
