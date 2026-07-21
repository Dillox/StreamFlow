package Servicio;

import Modelo.Calidad;

public class SuscripcionService {


    public double calcularCosto(Calidad calidad){

        return calidad.getPrecio();

    }


    public String obtenerPlan(Calidad calidad){

        switch(calidad){

            case SD:
                return "Plan Básico";

            case HD:
                return "Plan Estándar";

            case CUATRO_K:
                return "Plan Premium";

            default:
                return "Sin plan";
        }

    }

}