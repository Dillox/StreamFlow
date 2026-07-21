package Modelo;

public class Documental extends Contenido {

    private String tema;
    private int duracion;

    public Documental(int id, String titulo, String genero, Calidad calidad,
                      String tema, int duracion) {
        super(id, titulo, genero, calidad);
        this.tema     = tema;
        this.duracion = duracion;
    }

    @Override public String getTipo() { return "Documental"; }

    @Override
    public String reproducir() {
        return "▶ Reproduciendo documental: " + Titulo;
    }

    @Override
    public String obtenerDetalles() {
        return "🎥 Documental: " + Titulo
             + "\n   Género    : " + Genero
             + "\n   Calidad   : " + calidad
             + "\n   Tema      : " + tema
             + "\n   Duración  : " + duracion + " min";
    }

    public String getTema()  { return tema; }
    public int getDuracion() { return duracion; }
}
