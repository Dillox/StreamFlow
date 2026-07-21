package Controlador;

import DAO.ContenidoDAO;
import DAO.IContenidoDAO;       
import Modelo.Contenido;
import java.util.List;

public class ContenidoController {

    private final IContenidoDAO contenidoDAO;

    public ContenidoController() {
        this.contenidoDAO = new ContenidoDAO();   
    }

    public ContenidoController(IContenidoDAO dao) {
        this.contenidoDAO = dao;
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
