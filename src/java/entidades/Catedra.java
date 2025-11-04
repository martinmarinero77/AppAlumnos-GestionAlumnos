/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author martinLocal
 */
@Entity
@Table(name = "catedra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catedra.findAll", query = "SELECT c FROM Catedra c"),
    @NamedQuery(name = "Catedra.findByDocenteidDocente", query = "SELECT c FROM Catedra c WHERE c.catedraPK.docenteidDocente = :docenteidDocente"),
    @NamedQuery(name = "Catedra.findByMateriaIdmateria", query = "SELECT c FROM Catedra c WHERE c.catedraPK.materiaIdmateria = :materiaIdmateria"),
    @NamedQuery(name = "Catedra.findByCargo", query = "SELECT c FROM Catedra c WHERE c.cargo = :cargo")})
public class Catedra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CatedraPK catedraPK;
    @Size(max = 7)
    @Column(name = "cargo")
    private String cargo;
    @JoinColumn(name = "docente_idDocente", referencedColumnName = "idDocente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Docente docente;
    @JoinColumn(name = "materia_idmateria", referencedColumnName = "idmateria", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Materia materia;

    public Catedra() {
    }

    public Catedra(CatedraPK catedraPK) {
        this.catedraPK = catedraPK;
    }

    public Catedra(int docenteidDocente, int materiaIdmateria) {
        this.catedraPK = new CatedraPK(docenteidDocente, materiaIdmateria);
    }

    public CatedraPK getCatedraPK() {
        return catedraPK;
    }

    public void setCatedraPK(CatedraPK catedraPK) {
        this.catedraPK = catedraPK;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catedraPK != null ? catedraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catedra)) {
            return false;
        }
        Catedra other = (Catedra) object;
        if ((this.catedraPK == null && other.catedraPK != null) || (this.catedraPK != null && !this.catedraPK.equals(other.catedraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Catedra[ catedraPK=" + catedraPK + " ]";
    }
    
}
