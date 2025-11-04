/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author martinLocal
 */
@Entity
@Table(name = "alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findByIdalumnos", query = "SELECT a FROM Alumno a WHERE a.idalumnos = :idalumnos"),
    @NamedQuery(name = "Alumno.findByNombre", query = "SELECT a FROM Alumno a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Alumno.findByRegistro", query = "SELECT a FROM Alumno a WHERE a.registro = :registro")})
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idalumnos")
    private Integer idalumnos;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 10)
    @Column(name = "registro")
    private String registro;
    @JoinColumn(name = "carrera_idcarrera", referencedColumnName = "idcarrera")
    @ManyToOne(optional = false)
    private Carrera carreraIdcarrera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alumno")
    private Collection<Examen> examenCollection;

    public Alumno() {
    }

    public Alumno(Integer idalumnos) {
        this.idalumnos = idalumnos;
    }

    public Integer getIdalumnos() {
        return idalumnos;
    }

    public void setIdalumnos(Integer idalumnos) {
        this.idalumnos = idalumnos;
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

    public Carrera getCarreraIdcarrera() {
        return carreraIdcarrera;
    }

    public void setCarreraIdcarrera(Carrera carreraIdcarrera) {
        this.carreraIdcarrera = carreraIdcarrera;
    }

    @XmlTransient
    public Collection<Examen> getExamenCollection() {
        return examenCollection;
    }

    public void setExamenCollection(Collection<Examen> examenCollection) {
        this.examenCollection = examenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalumnos != null ? idalumnos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.idalumnos == null && other.idalumnos != null) || (this.idalumnos != null && !this.idalumnos.equals(other.idalumnos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Alumno[ idalumnos=" + idalumnos + " ]";
    }
    
}
