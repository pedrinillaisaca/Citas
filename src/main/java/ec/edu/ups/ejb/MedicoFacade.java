package ec.edu.ups.ejb;

import ec.edu.ups.entidad_cit_cons_cert.Medico;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MedicoFacade extends AbstractFacade<Medico> {

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public MedicoFacade() {
        super(Medico.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
