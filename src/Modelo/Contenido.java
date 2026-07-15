package Modelo;

public abstract class Contenido {
    
    protected int ID;
    protected String Titulo;
    protected String Genero;
    protected Calidad calidad;

    public Contenido(int ID, String Titulo, String Genero, Calidad calidad) {
        this.ID = ID;
        this.Titulo = Titulo;
        this.Genero = Genero;
        this.calidad = calidad;
    }
    
    public abstract String reproducir();
    public abstract String obtenerDetalles();

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public Calidad getCalidad() {
        return calidad;
    }

    public void setCalidad(Calidad calidad) {
        this.calidad = calidad;
    }
    
    
    
    
}
