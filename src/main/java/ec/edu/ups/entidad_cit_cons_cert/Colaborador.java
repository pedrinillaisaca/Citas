package ec.edu.ups.entidad_cit_cons_cert;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import javax.persistence.Transient;

@Entity
public class Colaborador extends Persona implements Serializable {

    @ManyToOne
    private Rol rol;
    @Transient
    private boolean editable;

    public Colaborador() {

    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    

    @Override
    public String toString() {
        return "Colaborador{"
                + "rol=" + rol
                + '}';
    }
}
