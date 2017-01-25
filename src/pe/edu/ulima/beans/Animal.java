package pe.edu.ulima.beans;

public class Animal {
    private int id;
    private String nombre;
    private String nombreCientifico;
    private int aniosVida;
    private float pesoKg;
    private String habitad;
    private String locomocion;
    private String alimentacion;

    public Animal(int id, String nombre, String nombreCientifico, int aniosVida, float pesoKg, String habitad, String locomocion, String alimentacion) {
        this.id = id;
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
        this.aniosVida = aniosVida;
        this.pesoKg = pesoKg;
        this.habitad = habitad;
        this.locomocion = locomocion;
        this.alimentacion = alimentacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public int getAniosVida() {
        return aniosVida;
    }

    public void setAniosVida(int aniosVida) {
        this.aniosVida = aniosVida;
    }

    public float getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(float pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getHabitad() {
        return habitad;
    }

    public void setHabitad(String habitad) {
        this.habitad = habitad;
    }

    public String getLocomocion() {
        return locomocion;
    }

    public void setLocomocion(String locomocion) {
        this.locomocion = locomocion;
    }

    public String getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(String alimentacion) {
        this.alimentacion = alimentacion;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nombre=" + nombre + ", nombreCientifico=" + nombreCientifico + ", aniosVida=" + aniosVida + ", pesoKg=" + pesoKg + ", habitad=" + habitad + ", locomocion=" + locomocion + ", alimentacion=" + alimentacion + '}';
    }
}
