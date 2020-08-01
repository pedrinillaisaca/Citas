package ec.edu.ups.ejb;



import ec.edu.ups.entidad_cit_cons_cert.Paciente;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
