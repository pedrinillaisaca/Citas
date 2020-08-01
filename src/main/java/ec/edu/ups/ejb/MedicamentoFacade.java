package ec.edu.ups.ejb;


import ec.edu.ups.entidad_ingre_egre_rep.Medicamento;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class MedicamentoFacade extends AbstractFacade<Medicamento>{

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public MedicamentoFacade() {
        super(Medicamento.class);
    }

    public Medicamento buscarPorNombre(String nombre){
        System.out.println("Bodega buscada"+nombre);
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Medicamento> criteriaQuery= criteriaBuilder.createQuery(Medicamento.class);
        Root<Medicamento> categoriaRoot= criteriaQuery.from(Medicamento.class);
        Predicate predicate= criteriaBuilder.equal(categoriaRoot.get("nombre"),nombre);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}