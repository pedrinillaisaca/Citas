package ec.edu.ups.entidad_cit_cons_cert;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Medico extends Persona implements Serializable {


    @ManyToOne
    private Rol rol;
    private String especialidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medico")
    private List<Cita> citas;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "medico")
    private List<Certificado> certificados;
    @Transient
    private boolean editable;

    public Medico() {
    }

    public Medico(Rol rol, String especialidad, List<Cita> citas, List<Certificado> certificados) {

        this.rol = rol;
        this.especialidad = especialidad;
        this.citas = citas;
        this.certificados = certificados;
    }



    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<Certificado> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<Certificado> certificados) {
        this.certificados = certificados;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }




    @Override
    public String toString() {
        return "Medico{"+
                ", rol=" + rol +
                ", especialidad='" + especialidad + '\'' +
                ", citas=" + citas +
                ", certificados=" + certificados +
                '}';
    }
}
