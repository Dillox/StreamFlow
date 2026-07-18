package DAO;

import Modelo.Contenido;
import java.util.ArrayList;

public interface IContenidoDAO {

    void guardarContenido(Contenido contenido);

    ArrayList<Contenido> listarContenidos();

    Contenido buscarPorID(int id);

    void actualizarContenido(Contenido contenido);

    void eliminarContenido(int id);

}