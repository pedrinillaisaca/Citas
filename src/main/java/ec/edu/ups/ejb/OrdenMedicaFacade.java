package ec.edu.ups.ejb;



import ec.edu.ups.entidad_cit_cons_cert.OrdenMedica;
import ec.edu.ups.entidad_cit_cons_cert.Persona;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class OrdenMedicaFacade extends AbstractFacade<OrdenMedica>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public OrdenMedicaFacade() {
        super(OrdenMedica.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}