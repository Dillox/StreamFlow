package Modelo;

public enum Calidad {

    SD("SD", 5.0),
    HD("HD", 8.0),
    CUATRO_K("4K", 12.0);

    private final String nombre;
    private final double precio;

    Calidad(String nombre, double precio){
        this.nombre = nombre;
        this.precio = precio;
    }

    public double getPrecio(){
        return precio;
    }

    @Override
    public String toString() {
        return nombre;
    }
}