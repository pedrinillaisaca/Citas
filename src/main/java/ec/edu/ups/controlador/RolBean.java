package ec.edu.ups.controlador;

import ec.edu.ups.ejb.RolFacade;
import ec.edu.ups.entidad_cit_cons_cert.Rol;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class RolBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private RolFacade ejbRolFacade;
    private List<Rol> list;

    public RolBean() {

    }

    @PostConstruct
    public void init() {
        try {
            list = ejbRolFacade.findAll();
        } catch (Exception e) {
        }

    }

    public void setList(List<Rol> list) {
        this.list = list;
    }

    public Rol[] getList() {
        return list.toArray(new Rol[0]);
    }

    public void prueba() {

        //ejbRolFacade.create(new Rol("Secretaria","Rol para la secretaria"));
    }

}
