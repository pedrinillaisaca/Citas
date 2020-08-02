package ec.edu.ups.ejb;

import ec.edu.ups.entidad_cit_cons_cert.Cita;
import ec.edu.ups.entidad_cit_cons_cert.Paciente;
import ec.edu.ups.entidad_ingre_egre_rep.Caja;
import ec.edu.ups.entidad_ingre_egre_rep.Caja;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Stateless
public class CajaFacade extends AbstractFacade<Caja> {

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public CajaFacade() {
        super(Caja.class);
    }

    public Caja buscarCajaPorFecha(Date fecha) {
        System.out.println("Caja buscada " + fecha);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Caja> criteriaQuery = criteriaBuilder.createQuery(Caja.class);
        Root<Caja> categoriaRoot = criteriaQuery.from(Caja.class);
        Predicate predicate = criteriaBuilder.equal(categoriaRoot.get("fecha"), fecha);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();

    }

    public Caja buscarCajaPorFecha(String fecha) {

        System.out.println("String" + fecha);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Caja> criteriaQuery = criteriaBuilder.createQuery(Caja.class);
        Root<Caja> categoriaRoot = criteriaQuery.from(Caja.class);
        Predicate predicate = criteriaBuilder.equal(categoriaRoot.get("fecha"), fecha);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();

    }

    public List<Cita> buscarCitaPorPaciente(Paciente cedula) {
        System.out.println("Cedula del Cita buscado" + cedula);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cita> criteriaQuery = criteriaBuilder.createQuery(Cita.class);
        Root<Cita> categoriaRoot = criteriaQuery.from(Cita.class);
        Predicate predicate = criteriaBuilder.equal(categoriaRoot.get("paciente"), cedula);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Caja buscarCajaPorCodigo(String codigo) {

        System.out.println("String" + codigo);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Caja> criteriaQuery = criteriaBuilder.createQuery(Caja.class);
        Root<Caja> categoriaRoot = criteriaQuery.from(Caja.class);
        Predicate predicate = criteriaBuilder.equal(categoriaRoot.get("codigo"), codigo);
        criteriaQuery.select(categoriaRoot).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();

    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
