package ec.edu.ups.ejb;

import ec.edu.ups.entidad_ingre_egre_rep.LibroDiario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class LibroDiarioFacade extends AbstractFacade<LibroDiario>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public LibroDiarioFacade() {
        super(LibroDiario.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}