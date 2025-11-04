/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesiones;

import entidades.Catedra;
import entidades.Docente;
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
public class DocenteFacade extends AbstractFacade<Docente> {

    @PersistenceContext(unitName = "AppAlumnoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteFacade() {
        super(Docente.class);
    }
    
    public List<String> findDocentesConMasDeDosMaterias() {
    
    EntityManager em = getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();
    
    // 1. La consulta devolverá una lista de Strings (nombres)
    CriteriaQuery<String> cq = cb.createQuery(String.class);
    
    // 2. Empezamos desde Docente (FROM Docente d)
    Root<Docente> docente = cq.from(Docente.class);
    
    // 3. Hacemos JOIN con Catedra (JOIN d.catedraCollection c)
    Join<Docente, Catedra> catedra = docente.join("catedraCollection");
    
    // 4. Seleccionamos el nombre (SELECT d.nombre)
    cq.select(docente.get("nombre")).distinct(true); // distinct por si un profe está 2 veces en la misma materia
    
    // 5. Agrupamos por docente (GROUP BY d.idDocente, d.nombre)
    //    Agrupamos por ID y nombre para contar las materias de CADA docente
    cq.groupBy(docente.get("idDocente"), docente.get("nombre"));
    
    // 6. Ponemos la condición (HAVING COUNT(c) > 2)
    //    Contamos las cátedras (materias) para cada grupo
    cq.having(cb.greaterThan(cb.count(catedra), 2L)); // 2L es para el número 2 en formato Long
    
    // 7. Creamos y ejecutamos
    Query q = em.createQuery(cq);
    return q.getResultList();
}
    
}
