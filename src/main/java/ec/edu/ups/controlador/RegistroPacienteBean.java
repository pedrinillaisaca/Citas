package ec.edu.ups.controlador;


import ec.edu.ups.ejb.*;
import ec.edu.ups.entidad_cit_cons_cert.Paciente;
import ec.edu.ups.entidad_cit_cons_cert.Rol;
import java.io.IOException;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import javax.faces.context.FacesContext;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class RegistroPacienteBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /*varibles de usuarios*/
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    /*EJB'S PARA LA PERSISTENCIA*/
    @EJB
    private PacienteFacade pacienteFacade;
    @EJB
    private RolFacade rolFacade;


    public RegistroPacienteBean(){

    }


    public void addPaciente() throws IOException{
        Paciente p=new Paciente();
        p.setNombre(this.nombre.toUpperCase());
        p.setApellido(this.apellido.toUpperCase());
        p.setCedula(this.cedula);
        p.setCorreo(this.correo);
        p.setPassword(this.password);
        /*PILAS AQUI VA EL ROL DE PACIENTE*/
        Rol r=rolFacade.find(2);//SE ESTABLECE EL RESPECTIVO ROL DE PACIENTE AL NUEVO REGISTRO
        p.setRol(r);
        pacienteFacade.create(p);        
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Ing_Software/index.xhtml");
    }

    /* GET Y SET*/

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
