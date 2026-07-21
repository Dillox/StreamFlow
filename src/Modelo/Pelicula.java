package Modelo;

public class Pelicula extends Contenido {

    private int duracion;
    private String director;
    private int anioEstreno;

    public Pelicula(int ID, String Titulo, String Genero, Calidad calidad,
                    int duracion, String director, int anioEstreno) {
        super(ID, Titulo, Genero, calidad);
        this.duracion    = duracion;
        this.director    = director;
        this.anioEstreno = anioEstreno;
    }

    @Override public String getTipo() { return "Pelicula"; }

    @Override
    public String reproducir() {
        return "Reproduciendo película: " + Titulo;
    }

    @Override
    public String obtenerDetalles() {
        return " Película  : " + Titulo
             + "\n   Género    : " + Genero
             + "\n   Calidad   : " + calidad
             + "\n   Duración  : " + duracion + " min"
             + "\n   Director  : " + director
             + "\n   Año       : " + anioEstreno;
    }

    public int getDuracion()     { return duracion; }
    public String getDirector()  { return director; }
    public int getAnioEstreno()  { return anioEstreno; }
}
