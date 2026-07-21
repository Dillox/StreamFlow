package DAO;

import Database.ConexionSQLite;
import Modelo.*;

import java.sql.*;
import java.util.ArrayList;

public class ContenidoDAO implements IContenidoDAO {


    private String calidadABD(Calidad c) {
        return c.toString();          
    }

    private Calidad bdACalidad(String texto) {
        return Calidad.valueOf(texto.replace("4K", "CUATRO_K"));
    }


    @Override
    public void guardarContenido(Contenido contenido) {
        String sql = "INSERT INTO Contenido(id_contenido, titulo, genero, calidad, tipo) "
                   + "VALUES(?, ?, ?, ?, ?)";

        try (Connection con = ConexionSQLite.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt   (1, contenido.getID());
            ps.setString(2, contenido.getTitulo());
            ps.setString(3, contenido.getGenero());
            ps.setString(4, calidadABD(contenido.getCalidad()));
            ps.setString(5, contenido.getTipo());   
            ps.executeUpdate();
            System.out.println("Contenido guardado.");

        } catch (SQLException e) {
            System.out.println("Error al guardar contenido: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Contenido> listarContenidos() {
        ArrayList<Contenido> lista = new ArrayList<>();
        String sql = "SELECT * FROM Contenido";

        try (Connection con = ConexionSQLite.conectar();
             Statement st   = con.createStatement();
             ResultSet rs   = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar contenidos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Contenido buscarPorID(int id) {
        String sql = "SELECT * FROM Contenido WHERE id_contenido = ?";

        try (Connection con = ConexionSQLite.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapearFila(rs);

        } catch (SQLException e) {
            System.out.println("Error al buscar contenido: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizarContenido(Contenido contenido) {
        String sql = "UPDATE Contenido SET titulo=?, genero=?, calidad=?, tipo=? "
                   + "WHERE id_contenido=?";

        try (Connection con = ConexionSQLite.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, contenido.getTitulo());
            ps.setString(2, contenido.getGenero());
            ps.setString(3, calidadABD(contenido.getCalidad()));
            ps.setString(4, contenido.getTipo());   // ← sin instanceof
            ps.setInt   (5, contenido.getID());
            ps.executeUpdate();
            System.out.println("Contenido actualizado.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar contenido: " + e.getMessage());
        }
    }

    @Override
    public void eliminarContenido(int id) {
        String sql = "DELETE FROM Contenido WHERE id_contenido = ?";

        try (Connection con = ConexionSQLite.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Contenido eliminado.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar contenido: " + e.getMessage());
        }
    }


    private Contenido mapearFila(ResultSet rs) throws SQLException {
        int    id     = rs.getInt   ("id_contenido");
        String titulo = rs.getString("titulo");
        String genero = rs.getString("genero");
        String tipo   = rs.getString("tipo");
        Calidad calidad = bdACalidad(rs.getString("calidad"));

        return ContenidoFactory.crear(tipo, id, titulo, genero, calidad);
    }
}
