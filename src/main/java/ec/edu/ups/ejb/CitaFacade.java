package ec.edu.ups.ejb;

import ec.edu.ups.entidad_cit_cons_cert.Cita;
import ec.edu.ups.entidad_cit_cons_cert.Paciente;
import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
@Stateless
public class CitaFacade extends AbstractFacade<Cita>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public CitaFacade() {
        super(Cita.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    public Cita buscarCitaPorFechaHora(Timestamp fec_hora){
        System.out.println("Fec_ hora buscado"+fec_hora);
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Cita> criteriaQuery= criteriaBuilder.createQuery(Cita.class);
        Root<Cita> categoriaRoot= criteriaQuery.from(Cita.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("fec_hora"),fec_hora);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
     public List<Cita> buscarCitaPorPaciente(Paciente cedula){
        System.out.println("Cedula del Cita buscado"+cedula);
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Cita> criteriaQuery= criteriaBuilder.createQuery(Cita.class);
        Root<Cita> categoriaRoot= criteriaQuery.from(Cita.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("paciente"),cedula);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}