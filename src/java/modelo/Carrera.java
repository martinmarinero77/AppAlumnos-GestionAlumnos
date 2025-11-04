package modelo;

public class Carrera {
    private int idcarrera;
    private String nombre;
    private int facultad_idfacultad;

    public Carrera() {
    }

    public Carrera(int idcarrera, String nombre, int facultad_idfacultad) {
        this.idcarrera = idcarrera;
        this.nombre = nombre;
        this.facultad_idfacultad = facultad_idfacultad;
    }

    public int getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFacultad_idfacultad() {
        return facultad_idfacultad;
    }

    public void setFacultad_idfacultad(int facultad_idfacultad) {
        this.facultad_idfacultad = facultad_idfacultad;
    }

    @Override
    public String toString() {
        return "Carrera{" + "idcarrera=" + idcarrera + ", nombre='" + nombre + "'" + ", facultad_idfacultad=" + facultad_idfacultad + '}';
    }
}