package ec.edu.ups.ejb;



import ec.edu.ups.entidad_cit_cons_cert.Paciente;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
@Stateless
public class PacienteFacade extends AbstractFacade<Paciente>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public PacienteFacade() {
        super(Paciente.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
      public Paciente buscarPorCedula(String cedula){
        System.out.println("Cedula del paciente buscado"+cedula);
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Paciente> criteriaQuery= criteriaBuilder.createQuery(Paciente.class);
        Root<Paciente> categoriaRoot= criteriaQuery.from(Paciente.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("cedula"),cedula);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
