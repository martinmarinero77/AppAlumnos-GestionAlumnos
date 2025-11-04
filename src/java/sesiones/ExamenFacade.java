/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.Alumno;
import entidades.Examen;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author martinLocal
 */
@Stateless
public class ExamenFacade extends AbstractFacade<Examen> {

    @PersistenceContext(unitName = "AppAlumnoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamenFacade() {
        super(Examen.class);
    }
    
    public List<Object[]> findExamenesJulioConNotas() {
    
    // 1. Obtenemos el boilerplate de la API Criteria 
    EntityManager em = getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();
    
    // 2. Definimos que nuestra consulta devolverá un Object[] (nombre, nota)
    CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
    
    // 3. Empezamos la consulta desde Examen (FROM Examen e)
    Root<Examen> examen = cq.from(Examen.class);
    
    // 4. Hacemos el JOIN con Alumno (JOIN e.alumnoIdalumnos a)
    //    !! Revisa este nombre de campo: "alumnoIdalumnos"
    //    !! Debe coincidir con el nombre de la variable de tipo Alumno en tu Examen.java
    Join<Examen, Alumno> alumno = examen.join("alumno");
    
    // 5. Seleccionamos lo que queremos (SELECT a.nombre, e.nota)
    //    !! Revisa que el campo de la nota se llame "nota" en tu Examen.java
    cq.multiselect(alumno.get("nombre"), examen.get("nota"));
    
    // 6. Accedemos a "fecha" a través de "examenPK" y extraemos el mes (7 para Julio)
    cq.where(cb.equal(
            cb.function("MONTH", Integer.class, examen.get("examenPK").get("fecha")), 
            7
        ));
    
    // 7. Creamos y ejecutamos la consulta 
    Query q = em.createQuery(cq);
    return q.getResultList();
    }
    
}
