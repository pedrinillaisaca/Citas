package ec.edu.ups.ejb;


import ec.edu.ups.entidad_cit_cons_cert.HistoriaClinica;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class HistoriaClinicaFacade extends AbstractFacade<HistoriaClinica>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public HistoriaClinicaFacade() {
        super(HistoriaClinica.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}