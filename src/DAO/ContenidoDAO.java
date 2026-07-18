package DAO;

import Database.ConexionSQLite;
import Modelo.*;

import java.sql.*;
import java.util.ArrayList;

public class ContenidoDAO implements IContenidoDAO {

    @Override
    public void guardarContenido(Contenido contenido) {

        String sql = "INSERT INTO Contenido(id_contenido,titulo,genero,calidad,tipo) VALUES(?,?,?,?,?)";

        try (
                Connection conexion = ConexionSQLite.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setInt(1, contenido.getID());
            ps.setString(2, contenido.getTitulo());
            ps.setString(3, contenido.getGenero());
            ps.setString(4, contenido.getCalidad().toString());

            if (contenido instanceof Pelicula) {
                ps.setString(5, "Pelicula");
            } else if (contenido instanceof Serie) {
                ps.setString(5, "Serie");
            } else {
                ps.setString(5, "Documental");
            }

            ps.executeUpdate();

            System.out.println("Contenido guardado correctamente.");

        } catch (SQLException e) {

            System.out.println("Error al guardar contenido: " + e.getMessage());

        }

    }

    @Override
    public ArrayList<Contenido> listarContenidos() {

        ArrayList<Contenido> lista = new ArrayList<>();

        String sql = "SELECT * FROM Contenido";

        try (
                Connection conexion = ConexionSQLite.conectar();
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(sql)
        ) {

            while (rs.next()) {

                int id = rs.getInt("id_contenido");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");

                Calidad calidad = Calidad.valueOf(
                        rs.getString("calidad").replace("4K", "CUATRO_K")
                );

                String tipo = rs.getString("tipo");

                if (tipo.equalsIgnoreCase("Pelicula")) {

                    lista.add(new Pelicula(
                            id,
                            titulo,
                            genero,
                            calidad,
                            0,
                            "",
                            0
                    ));

                } else if (tipo.equalsIgnoreCase("Serie")) {

                    lista.add(new Serie(
                            id,
                            titulo,
                            genero,
                            calidad,
                            0,
                            0,
                            0
                    ));

                } else {

                    lista.add(new Documental(
                            id,
                            titulo,
                            genero,
                            calidad,
                            "",
                            0
                    ));

                }

            }

        } catch (SQLException e) {

            System.out.println("Error al listar contenidos: " + e.getMessage());

        }

        return lista;

    }

    @Override
    public Contenido buscarPorID(int id) {

        String sql = "SELECT * FROM Contenido WHERE id_contenido=?";

        try (
                Connection conexion = ConexionSQLite.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");

                Calidad calidad = Calidad.valueOf(
                        rs.getString("calidad").replace("4K", "CUATRO_K")
                );

                String tipo = rs.getString("tipo");

                if (tipo.equalsIgnoreCase("Pelicula")) {

                    return new Pelicula(
                            id,
                            titulo,
                            genero,
                            calidad,
                            0,
                            "",
                            0
                    );

                } else if (tipo.equalsIgnoreCase("Serie")) {

                    return new Serie(
                            id,
                            titulo,
                            genero,
                            calidad,
                            0,
                            0,
                            0
                    );

                } else {

                    return new Documental(
                            id,
                            titulo,
                            genero,
                            calidad,
                            "",
                            0
                    );

                }

            }

        } catch (SQLException e) {

            System.out.println("Error al buscar contenido: " + e.getMessage());

        }

        return null;

    }

    @Override
    public void actualizarContenido(Contenido contenido) {

        String sql = "UPDATE Contenido SET titulo=?, genero=?, calidad=?, tipo=? WHERE id_contenido=?";

        try (
                Connection conexion = ConexionSQLite.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setString(1, contenido.getTitulo());
            ps.setString(2, contenido.getGenero());
            ps.setString(3, contenido.getCalidad().toString());

            if (contenido instanceof Pelicula) {

                ps.setString(4, "Pelicula");

            } else if (contenido instanceof Serie) {

                ps.setString(4, "Serie");

            } else {

                ps.setString(4, "Documental");

            }

            ps.setInt(5, contenido.getID());

            ps.executeUpdate();

            System.out.println("Contenido actualizado correctamente.");

        } catch (SQLException e) {

            System.out.println("Error al actualizar contenido: " + e.getMessage());

        }

    }

    @Override
    public void eliminarContenido(int id) {

        String sql = "DELETE FROM Contenido WHERE id_contenido=?";

        try (
                Connection conexion = ConexionSQLite.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Contenido eliminado correctamente.");

        } catch (SQLException e) {

            System.out.println("Error al eliminar contenido: " + e.getMessage());

        }

    }

}