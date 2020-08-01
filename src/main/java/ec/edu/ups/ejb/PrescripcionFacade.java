package ec.edu.ups.ejb;


import ec.edu.ups.entidad_cit_cons_cert.Persona;
import ec.edu.ups.entidad_cit_cons_cert.Prescripcion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class PrescripcionFacade extends AbstractFacade<Prescripcion>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public PrescripcionFacade() {
        super(Prescripcion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

