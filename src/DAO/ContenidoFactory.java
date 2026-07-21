package DAO;

import Modelo.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class ContenidoFactory {

    private static final Map<String, Function<Object[], Contenido>> REGISTRY = new HashMap<>();

    static {
        REGISTRY.put("pelicula",   args -> new Pelicula(
                (int) args[0], (String) args[1], (String) args[2], (Calidad) args[3],
                0, "", 0));
        REGISTRY.put("serie",      args -> new Serie(
                (int) args[0], (String) args[1], (String) args[2], (Calidad) args[3],
                0, 0, 0));
        REGISTRY.put("documental", args -> new Documental(
                (int) args[0], (String) args[1], (String) args[2], (Calidad) args[3],
                "", 0));
    }

    public static Contenido crear(String tipo, int id, String titulo,
                                  String genero, Calidad calidad) {
        Function<Object[], Contenido> constructor = REGISTRY.get(tipo.toLowerCase());
        if (constructor == null) {
            throw new IllegalArgumentException("Tipo de contenido desconocido: " + tipo);
        }
        return constructor.apply(new Object[]{id, titulo, genero, calidad});
    }

    public static void registrar(String tipo, Function<Object[], Contenido> constructor) {
        REGISTRY.put(tipo.toLowerCase(), constructor);
    }
}
