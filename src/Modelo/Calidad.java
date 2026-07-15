
package Modelo;

public enum Calidad {
    SD("SD"),
    HD("HD"),
    CUATRO_K("4K");
    
    private final String nombre;
    
    Calidad(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
