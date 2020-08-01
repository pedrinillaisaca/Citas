package ec.edu.ups.controlador;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;

public class UtilsSesion implements Serializable {
    private ExternalContext contexto;
    public void add(String key, Object value) {
        contexto =  FacesContext.getCurrentInstance().getExternalContext();
        contexto.getSessionMap().put("key", value);
    }

    public Object get(String key) {
        return contexto.getSessionMap().get("key");
    }
}
