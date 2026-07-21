package Controlador;

import DAO.ContenidoDAO;
import Modelo.Contenido;
import java.util.List;

public class ContenidoController {

    private ContenidoDAO contenidoDAO;

    public ContenidoController() {
        contenidoDAO = new ContenidoDAO();
    }

    public void agregarContenido(Contenido contenido) {
        contenidoDAO.guardarContenido(contenido);
    }

    public List<Contenido> listarContenido() {
        return contenidoDAO.listarContenidos();
    }

    public Contenido buscarContenido(int id) {
        return contenidoDAO.buscarPorID(id);
    }

    public void actualizarContenido(Contenido contenido) {
        contenidoDAO.actualizarContenido(contenido);
    }

    public void eliminarContenido(int id) {
        contenidoDAO.eliminarContenido(id);
    }
}