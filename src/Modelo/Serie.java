package Modelo;

public class Serie extends Contenido {

    private int temporadas;
    private int capitulos;
    private int duracionCapitulo;

    public Serie(int id, String titulo, String genero, Calidad calidad,
                 int temporadas, int capitulos, int duracionCapitulo) {
        super(id, titulo, genero, calidad);
        this.temporadas       = temporadas;
        this.capitulos        = capitulos;
        this.duracionCapitulo = duracionCapitulo;
    }

    @Override public String getTipo() { return "Serie"; }

    @Override
    public String reproducir() {
        return " Reproduciendo serie: " + Titulo;
    }

    @Override
    public String obtenerDetalles() {
        return " Serie     : " + Titulo
             + "\n   Género    : " + Genero
             + "\n   Calidad   : " + calidad
             + "\n   Temporadas: " + temporadas
             + "\n   Capítulos : " + capitulos
             + "\n   Min/Cap   : " + duracionCapitulo + " min";
    }

    public int getTemporadas()       { return temporadas; }
    public int getCapitulos()        { return capitulos; }
    public int getDuracionCapitulo() { return duracionCapitulo; }
}
