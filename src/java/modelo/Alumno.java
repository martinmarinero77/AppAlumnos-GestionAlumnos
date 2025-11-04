package modelo;

public class Alumno {
    private int idalumno;
    private String nombre;
    private String registro;
    private int carrera_idcarrera;

    public Alumno() {
    }

    public Alumno(int idalumno, String nombre, String registro, int carrera_idcarrera) {
        this.idalumno = idalumno;
        this.nombre = nombre;
        this.registro = registro;
        this.carrera_idcarrera = carrera_idcarrera;
    }

    public int getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public int getCarrera_idcarrera() {
        return carrera_idcarrera;
    }

    public void setCarrera_idcarrera(int carrera_idcarrera) {
        this.carrera_idcarrera = carrera_idcarrera;
    }

    @Override
    public String toString() {
        return "Alumno{" + "idalumno=" + idalumno + ", nombre='" + nombre + "'" + ", registro=" + registro + ", carrera_idcarrera=" + carrera_idcarrera + '}' ;
    }
}