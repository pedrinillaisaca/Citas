package ec.edu.ups.ejb;


import ec.edu.ups.entidad_ingre_egre_rep.AscientoContable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class AscientoContableFacade extends AbstractFacade<AscientoContable>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public AscientoContableFacade() {
        super(AscientoContable.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}