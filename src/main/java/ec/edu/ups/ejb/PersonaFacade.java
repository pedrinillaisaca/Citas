package ec.edu.ups.ejb;

import ec.edu.ups.entidad_cit_cons_cert.Persona;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonaFacade extends AbstractFacade<Persona>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public PersonaFacade() {
        super(Persona.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}