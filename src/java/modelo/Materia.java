package modelo;

public class Materia {
    private int idmateria;
    private String nombre;

    public Materia() {
    }

    public Materia(int idmateria, String nombre) {
        this.idmateria = idmateria;
        this.nombre = nombre;
    }

    public int getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(int idmateria) {
        this.idmateria = idmateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Materia{" + "idmateria=" + idmateria + ", nombre='" + nombre + "'" + '}';
    }
}

