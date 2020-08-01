package ec.edu.ups.ejb;

import ec.edu.ups.entidad_cit_cons_cert.Cita;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}