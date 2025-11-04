/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author martinLocal
 */
@Embeddable
public class CatedraPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "docente_idDocente")
    private int docenteidDocente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "materia_idmateria")
    private int materiaIdmateria;

    public CatedraPK() {
    }

    public CatedraPK(int docenteidDocente, int materiaIdmateria) {
        this.docenteidDocente = docenteidDocente;
        this.materiaIdmateria = materiaIdmateria;
    }

    public int getDocenteidDocente() {
        return docenteidDocente;
    }

    public void setDocenteidDocente(int docenteidDocente) {
        this.docenteidDocente = docenteidDocente;
    }

    public int getMateriaIdmateria() {
        return materiaIdmateria;
    }

    public void setMateriaIdmateria(int materiaIdmateria) {
        this.materiaIdmateria = materiaIdmateria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) docenteidDocente;
        hash += (int) materiaIdmateria;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatedraPK)) {
            return false;
        }
        CatedraPK other = (CatedraPK) object;
        if (this.docenteidDocente != other.docenteidDocente) {
            return false;
        }
        if (this.materiaIdmateria != other.materiaIdmateria) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.CatedraPK[ docenteidDocente=" + docenteidDocente + ", materiaIdmateria=" + materiaIdmateria + " ]";
    }
    
}
