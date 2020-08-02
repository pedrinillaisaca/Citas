package ec.edu.ups.ejb;

import ec.edu.ups.entidad_ingre_egre_rep.Caja;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaDetalle;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaEgreso;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaIngreso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class FacturaDetalleFacade extends AbstractFacade<FacturaDetalle>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public FacturaDetalleFacade() {
        super(FacturaDetalle.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public List<FacturaDetalle> buscarDetallesEgresos(FacturaEgreso egreso){
        System.out.println("Bodega buscada"+ egreso);
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<FacturaDetalle> criteriaQuery= criteriaBuilder.createQuery(FacturaDetalle.class);
        Root<FacturaDetalle> categoriaRoot= criteriaQuery.from(FacturaDetalle.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("facturaegreso"),egreso);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<FacturaDetalle> buscarDetallesIngresos(FacturaIngreso egreso){
        System.out.println("Bodega buscada"+ egreso);
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<FacturaDetalle> criteriaQuery= criteriaBuilder.createQuery(FacturaDetalle.class);
        Root<FacturaDetalle> categoriaRoot= criteriaQuery.from(FacturaDetalle.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("facturaingreso"),egreso);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    
    public List<FacturaDetalle> recuperarDetalles(FacturaIngreso facturaIngreso){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FacturaDetalle> criteriaQuery = criteriaBuilder.createQuery(FacturaDetalle.class);
        Root<FacturaDetalle> pedidoRoot = criteriaQuery.from(FacturaDetalle.class);
        Predicate predicate= criteriaBuilder.equal(pedidoRoot.get("facturaingreso"),facturaIngreso);
        Predicate[] predicates = new Predicate[]{predicate};
        criteriaQuery.select(pedidoRoot).where(predicates);
        List<FacturaDetalle> pedidoList = entityManager.createQuery(criteriaQuery).getResultList();
        return pedidoList;
    }
}