package ec.edu.ups.ejb;



import ec.edu.ups.entidad_ingre_egre_rep.FacturaEgreso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class FacturaEgresoFacade extends AbstractFacade<FacturaEgreso>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public FacturaEgresoFacade() {
        super(FacturaEgreso.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}