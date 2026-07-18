package Modelo;

public class Usuario {

    private int id;
    private String nombre;
    private String plan;

    public Usuario(int id, String nombre, String plan) {
        this.id = id;
        this.nombre = nombre;
        this.plan = plan;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPlan() {
        return plan;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}