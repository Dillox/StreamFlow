package DAO;

import Database.ConexionSQLite;
import Modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public void guardarUsuario(Usuario usuario) {

        String sql = "INSERT INTO Usuario(id_usuario,nombre,plan) VALUES(?,?,?)";

        try(
                Connection conexion = ConexionSQLite.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ){

            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getPlan());

            ps.executeUpdate();

            System.out.println("Usuario guardado correctamente.");

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());

        }

    }

    @Override
    public ArrayList<Usuario> listarUsuarios() {

        ArrayList<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM Usuario";

        try(
                Connection conexion = ConexionSQLite.conectar();
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery(sql)
        ){

            while(rs.next()){

                lista.add(new Usuario(

                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("plan")

                ));

            }

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());

        }

        return lista;

    }

    @Override
    public Usuario buscarPorID(int id) {

        String sql = "SELECT * FROM Usuario WHERE id_usuario=?";

        try(
                Connection conexion = ConexionSQLite.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ){

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                return new Usuario(

                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("plan")

                );

            }

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());

        }

        return null;

    }

    @Override
    public void actualizarUsuario(Usuario usuario) {

        String sql = "UPDATE Usuario SET nombre=?, plan=? WHERE id_usuario=?";

        try(
                Connection conexion = ConexionSQLite.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ){

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPlan());
            ps.setInt(3, usuario.getId());

            ps.executeUpdate();

            System.out.println("Usuario actualizado correctamente.");

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());

        }

    }

    @Override
    public void eliminarUsuario(int id) {

        String sql = "DELETE FROM Usuario WHERE id_usuario=?";

        try(
                Connection conexion = ConexionSQLite.conectar();
                PreparedStatement ps = conexion.prepareStatement(sql)
        ){

            ps.setInt(1,id);

            ps.executeUpdate();

            System.out.println("Usuario eliminado correctamente.");

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());

        }

    }

}