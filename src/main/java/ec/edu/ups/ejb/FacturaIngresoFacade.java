package ec.edu.ups.ejb;


import ec.edu.ups.entidad_ingre_egre_rep.FacturaIngreso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class FacturaIngresoFacade extends AbstractFacade<FacturaIngreso>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public FacturaIngresoFacade() {
        super(FacturaIngreso.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}