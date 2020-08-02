package ec.edu.ups.ejb;


import ec.edu.ups.entidad_ingre_egre_rep.Caja;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaIngreso;
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
public class FacturaIngresoFacade extends AbstractFacade<FacturaIngreso>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public FacturaIngresoFacade() {
        super(FacturaIngreso.class);
    }
    
    public List<FacturaIngreso> buscarPorFacturaIngresoCodigo(Caja codigo){
        System.out.println("Bodega buscada"+codigo);
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<FacturaIngreso> criteriaQuery= criteriaBuilder.createQuery(FacturaIngreso.class);
        Root<FacturaIngreso> categoriaRoot= criteriaQuery.from(FacturaIngreso.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("caja"),codigo);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}