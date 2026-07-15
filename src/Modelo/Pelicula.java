package Modelo;

public class Pelicula extends Contenido {
    
    private int duracion;
    private String director;
    private int anioEstreno;

     public Pelicula(int ID, String Titulo, String Genero, Calidad calidad,int duracion, String director, int anioEstreno) {
        super(ID, Titulo, Genero, calidad);
        this.duracion = duracion;
        this.director = director;
        this.anioEstreno = anioEstreno;
    }

   
    @Override
    public String reproducir() {
        return "Reproduciendo película: " + Titulo;    }

    @Override
    public String obtenerDetalles() {
        return "Película: " + Titulo +
                "\nGénero: " + Genero +
                "\nCalidad: " + calidad +
                "\nDuración: " + duracion + " min" +
                "\nDirector: " + director +
                "\nAño: " + anioEstreno;
    }
    
    public int getDuracion() {
        return duracion;
    }

    

    public String getDirector() {
        return director;
    }

    

    public int getAnioEstreno() {
        return anioEstreno;
    }

}
