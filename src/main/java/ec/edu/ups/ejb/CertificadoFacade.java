package ec.edu.ups.ejb;

import ec.edu.ups.entidad_cit_cons_cert.Certificado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CertificadoFacade extends AbstractFacade<Certificado> {

    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public CertificadoFacade() {
        super(Certificado.class);
    }

    public CertificadoFacade(Class<Certificado> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}