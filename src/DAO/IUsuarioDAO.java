package DAO;

import Modelo.Usuario;
import java.util.ArrayList;

public interface IUsuarioDAO {

    void guardarUsuario(Usuario usuario);

    ArrayList<Usuario> listarUsuarios();

    Usuario buscarPorID(int id);

    void actualizarUsuario(Usuario usuario);

    void eliminarUsuario(int id);

}