package Controlador;

import DAO.UsuarioDAO;
import Modelo.Usuario;
import java.util.List;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarioDAO.guardarUsuario(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public Usuario buscarUsuario(int id) {
        return usuarioDAO.buscarPorID(id);
    }

    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.actualizarUsuario(usuario);
    }

    public void eliminarUsuario(int id) {
        usuarioDAO.eliminarUsuario(id);
    }

}