package Servicio;

import Modelo.Contenido;
import java.util.ArrayList;
import java.util.List;


public class RecomendacionService {


    public List<Contenido> recomendarPorGenero(
            List<Contenido> contenidos,
            String genero){


        List<Contenido> recomendaciones = new ArrayList<>();


        for(Contenido contenido : contenidos){


            if(contenido.getGenero()
                    .equalsIgnoreCase(genero)){


                recomendaciones.add(contenido);

            }

        }


        return recomendaciones;

    }


}