package ec.edu.ups.ejb;


import ec.edu.ups.entidad_cit_cons_cert.HistorialClinico;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class HistorialClinicoFacade extends AbstractFacade<HistorialClinico>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public HistorialClinicoFacade() {
        super(HistorialClinico.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}