package ec.edu.ups.controlador;

import ec.edu.ups.ejb.MedicoFacade;
import ec.edu.ups.ejb.RolFacade;
import ec.edu.ups.entidad_cit_cons_cert.Medico;
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
public class MedicoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /*VARIABLES PARA MEDICO*/
    private String cedula;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String especialidad;
    /*VARIABLES PARA PERSISTENCIA*/
    private List<Medico> listaMedicos;
    @EJB
    private MedicoFacade medicoFacade;
    @EJB
    private RolFacade rolFacade;

    public MedicoBean(){

    }

    @PostConstruct
    public void init(){
        try {
            this.listaMedicos=medicoFacade.findAll();
        } catch (Exception e) {
        }        
    }
    /*METODOS PERSISTENCIA*/
    
    public void addMedico(){
        Medico m=new Medico();
        m.setNombre(this.nombre.toUpperCase());
        m.setApellido(this.apellido.toUpperCase());
        m.setCedula(this.cedula);
        m.setCorreo(this.correo);
        m.setPassword(this.password);
        m.setEspecialidad(this.especialidad.toUpperCase());
        Rol r=rolFacade.find(5);//Para Relacionar al medico con su respectivo rol
        m.setRol(r);
        medicoFacade.create(m);
        limpiarCampos();
        this.listaMedicos=medicoFacade.findAll();/*para actualizar la lista de medicos*/
    }

    public void limpiarCampos(){
        this.nombre="";
        this.apellido="";
        this.cedula="";
        this.correo="";
        this.password="";
        this.especialidad="";
    }



    /*EDICION FILAS*/
    public String deleted(Medico m){
        medicoFacade.remove(m);
        listaMedicos=medicoFacade.findAll();
        return null;
    }

    public String edit (Medico m){
        m.setEditable(true);
        return null;
    }

    public String save(Medico m){
        this.medicoFacade.edit(m);
        m.setEditable(false);
        return null;
    }



    /*ZONA GET SET*/

    public Medico[] getListaMedicos() {
        return this.listaMedicos.toArray(new Medico[0]);
    }

    public void setListaMedicos(List<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }



    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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
