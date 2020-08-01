package ec.edu.ups.ejb;

import ec.edu.ups.entidad_cit_cons_cert.Colaborador;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ColaboradorFacade extends AbstractFacade<Colaborador> {
    @PersistenceContext(unitName = "Practica03.EJB.JSF.JPA")
    private EntityManager entityManager;

    public ColaboradorFacade(){
        super(Colaborador.class);
    }


    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }



}


