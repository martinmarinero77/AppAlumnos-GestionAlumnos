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
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import java.time.Year;
import java.util.List;

/**
 *
 * @author martinLocal
 */
@Stateless
public class AlumnoFacade extends AbstractFacade<Alumno> {

    @PersistenceContext(unitName = "AppAlumnoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnoFacade() {
        super(Alumno.class);
    }
    
    // --- INICIO DEL NUEVO MÉTODO ---
    /**
     * Nuevo método para eliminar un Alumno usando solo su ID.El servidor EJB se encargará de crear la transacción para esta operación.
     * @param idAlumno
     */
    public void eliminarPorId(int idAlumno) {
        // 1. Buscamos el alumno usando el método "find" de esta misma clase
        Alumno alumnoParaEliminar = this.find(idAlumno);
        
        // 2. Si lo encontramos, lo eliminamos
        if (alumnoParaEliminar != null) {
            this.remove(alumnoParaEliminar); // "remove" es el método de AbstractFacade
        }
    }
    
   public List<Alumno> findAlumnosSinExamenesAnioActual() {

    EntityManager em = getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();

    // --- Obtenemos el año actual ---
    int anioActual = Year.now().getValue();
    // ---

    // Consulta principal: SELECT a FROM Alumno a ...
    CriteriaQuery<Alumno> cq = cb.createQuery(Alumno.class);
    Root<Alumno> alumno = cq.from(Alumno.class); // "a"
    cq.select(alumno);

    // --- Inicio de la Subconsulta ---
    // (SELECT e FROM Examen e ...
    Subquery<Examen> subquery = cq.subquery(Examen.class);
    Root<Examen> examen = subquery.from(Examen.class); // "e"
    subquery.select(examen); 

    // ... WHERE e.alumno = a)
    Predicate relacion = cb.equal(examen.get("alumno"), alumno);

    // ... AND YEAR(e.examenPK.fecha) = anioActual)
    Predicate anio = cb.equal(
        cb.function("YEAR", Integer.class, examen.get("examenPK").get("fecha")), 
        anioActual 
    );

    // Juntamos el WHERE de la subconsulta
    subquery.where(cb.and(relacion, anio));
    // --- Fin de la Subconsulta ---


    // Añadimos el WHERE a la consulta principal:
    // ... WHERE NOT EXISTS (la subconsulta)
    cq.where(cb.not(cb.exists(subquery)));

    // Creamos y ejecutamos la consulta
    Query q = em.createQuery(cq);
    return q.getResultList();
}
   
}