package ec.edu.ups.ejb;



import ec.edu.ups.entidad_ingre_egre_rep.Caja;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaEgreso;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaEgreso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class FacturaEgresoFacade extends AbstractFacade<FacturaEgreso>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public FacturaEgresoFacade() {
        super(FacturaEgreso.class);
    }


    public List<FacturaEgreso> buscarPorFacturaEgresoCodigo(Caja caja){
        System.out.println("Bodega buscada"+ caja);
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<FacturaEgreso> criteriaQuery= criteriaBuilder.createQuery(FacturaEgreso.class);
        Root<FacturaEgreso> categoriaRoot= criteriaQuery.from(FacturaEgreso.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("caja"),caja);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}