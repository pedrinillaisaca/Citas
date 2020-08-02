package ec.edu.ups.controlador;

import ec.edu.ups.ejb.ColaboradorFacade;
import ec.edu.ups.ejb.MedicoFacade;
import ec.edu.ups.ejb.PacienteFacade;
import ec.edu.ups.entidad_cit_cons_cert.Colaborador;
import ec.edu.ups.entidad_cit_cons_cert.Medico;
import ec.edu.ups.entidad_cit_cons_cert.Paciente;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import ec.edu.ups.utilidades.Session;
import java.io.IOException;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class InicioSesionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    /*VARIABLE DE LA VISTA*/
    private String correo, password;
    private boolean login;/*determina si se inicio la sesion*/
 /*facades*/
    @EJB
    private ColaboradorFacade colaboradorFacade;
    @EJB
    private MedicoFacade medicoFacade;
    @EJB
    private PacienteFacade pacienteFacade;

    /*METODOS*/
    public InicioSesionBean() {

    }

    /*SET Y GET*/
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

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public List<Paciente> findPacientes() {
        return pacienteFacade.findAll();
    }

    public List<Colaborador> findColaboradores() {
        return colaboradorFacade.findAll();
    }

    public List<Medico> findMedicos() {
        return medicoFacade.findAll();
    }

    public void validar() {
        try {
            if (!correo.equals("") && !password.equals("")) {
                List<Paciente> listPaciente = findPacientes();
                boolean band = false;
                for (Paciente p : listPaciente) {
                    if (p.getCorreo().equals(this.correo) && p.getPassword().equals(this.password)) {
                        System.out.println("===================ENCONTRADO PACIENTE====================");
                        HttpSession session = Session.getSession();
                        session.setAttribute("paciente", p);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Ing_Software/private/paginaPrincipalPaciente.xhtml");
                        login = true;
                        band = true;
                        break;
                    }
                }
                if (band == false) {
                    validarMedicos();
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Todos los campos son obligatorios"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Inicio", " de Sesion"));
        }

    }

    private void validarMedicos() throws IOException {
        List<Medico> listMedicos = findMedicos();
        boolean band = false;
        for (Medico m : listMedicos) {
            if (m.getCorreo().equals(this.correo) && m.getPassword().equals(this.password)) {
                System.out.println("=======================================");
                System.out.println("===================ENCONTRADO mEDICO====================");
                HttpSession session = Session.getSession();
                session.setAttribute("medico", m);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Ing_Software/private/paginaPrincipalMedico.xhtml");
                login = true;
                band = true;
                break;
            }
        }
        if (band == false) {
        }
        validarColaboradores();
    }

    private void validarColaboradores() throws IOException {
        List<Colaborador> listColaboradores = findColaboradores();
        for (Colaborador c : listColaboradores) {
            if (c.getCorreo().equals(this.correo) && c.getPassword().equals(this.password)) {
                System.out.println("===================ENCONTRADO COLABORADOR====================");
                HttpSession session = Session.getSession();
                session.setAttribute("colaborador", c);
                login = true;
                switch (c.getRol().getNombre()) {
                    case "CONTADOR": 
                        System.out.println("$$$$$$$$$$$$$$$$CONTADOR");
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Ing_Software/private/index.xhtml");
                    case "SECRETARIA":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Ing_Software/private/paginaPrincipalSecre.xhtml");
                    case "ADMINISTRADOR":
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/Ing_Software/private/paginaAdministrador.xhtml");
                }
                break;
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuario No Registrado", "Usuario No Registrado"));
    }

    public void logout() throws IOException {
        HttpSession session = Session.getSession();
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
    }

    public void register() throws IOException { 
        System.out.println("####$$$$$$$$$$32");
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Ing_Software/registro.xhtml");
    }
    
     public void registerToLoig() throws IOException {      
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Ing_Software/index.xhtml");
    }


    public String isMedico() {
        FacesContext context = FacesContext.getCurrentInstance();
//        Usuario usuarioLogin = (Usuario) Session.getSessionMap().get("usuario");
        Medico medicoLogin = (Medico) Session.getSessionMap().get("medico");
        try {
            if (medicoLogin == null) {
                context.getExternalContext().redirect("../index.xhtml");
            }
        } catch (Exception e) {
        }
        return medicoLogin.getNombre() + " " + medicoLogin.getApellido();
    }
    
    public String isPaciente() {
        FacesContext context = FacesContext.getCurrentInstance();
        Paciente paciente = (Paciente) Session.getSessionMap().get("paciente");
        try {
            if (paciente == null) {
                context.getExternalContext().redirect("../index.xhtml");
            }
        } catch (Exception e) {
        }
        return paciente.getNombre() + " " + paciente.getApellido();
    }

    public String isAdmin() {
        FacesContext context = FacesContext.getCurrentInstance();
        Colaborador colabLog = (Colaborador) Session.getSessionMap().get("colaborador");
        try {
            if (colabLog == null || !colabLog.getRol().getNombre().equals("ADMINISTRADOR")) {// 
                System.out.println("NO ERES ADMINISTRADOR FUERA!!");
                context.getExternalContext().redirect("../index.xhtml");
            }
        } catch (Exception e) {
        }

        return colabLog.getNombre() + " " + colabLog.getApellido();
    }
    
    public String isContador() {
        FacesContext context = FacesContext.getCurrentInstance();
        Colaborador colabLog = (Colaborador) Session.getSessionMap().get("colaborador");
        System.out.println("El rol es: "+colabLog.getRol().getNombre().equals("CONTADOR"));
        try {
            if (colabLog == null || !colabLog.getRol().getNombre().equals("CONTADOR")) {// 
                System.out.println("NO ERES CONTADOR FUERA!!!");
                context.getExternalContext().redirect("../index.xhtml");
            }
        } catch (Exception e) {
        }

        return colabLog.getNombre() + " " + colabLog.getApellido();
    }
}
