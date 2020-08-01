package ec.edu.ups.controlador;

import ec.edu.ups.ejb.CertificadoFacade;
import ec.edu.ups.ejb.CitaFacade;
import ec.edu.ups.ejb.PacienteFacade;
import ec.edu.ups.entidad_cit_cons_cert.Certificado;
import ec.edu.ups.entidad_cit_cons_cert.Cita;
import ec.edu.ups.entidad_cit_cons_cert.HistoriaClinica;
import ec.edu.ups.entidad_cit_cons_cert.HistorialClinico;
import ec.edu.ups.entidad_cit_cons_cert.OrdenMedica;
import ec.edu.ups.entidad_cit_cons_cert.Paciente;
import ec.edu.ups.utilidades.Session;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class CitasBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Set<FilaCita> list;
    private char citaLunes, citaMartes, citaMiercoles, citaJueves, citaViernes;
    private String hora;
    private String dia;
    private int pun = 0;
    private Map<String, String> mapaFechas;
    private Paciente paciente;
    private List<Cita> listCitas;
    /*variables para listar ordenes y certificados*/
    private List<Certificado> listCertificados;
    private List<OrdenMedica> listOrdenesMed;
    private HistorialClinico historialClinico;
    private List<HistoriaClinica> listHistoral;
    /*variables para listar ordenes y certificados*/

 /*EJB´S*/
    @EJB
    private PacienteFacade pacienteFacade;
    @EJB
    private CitaFacade citaFacade;
    @EJB
    private CertificadoFacade certFacade;

    /*EJB´S*/

    public CitasBean() {

    }

    @PostConstruct
    public void init() {
        this.paciente = (Paciente) Session.getSessionMap().get("paciente");
        thisSemana();
        iniFilas();
        iniListCitEdit();
        try {
            this.historialClinico = this.paciente.getHistorialClinico();
            cargarHistPaciente();//CARGA EL HISTORIAL DEL PACIENTE
            cargaCertificados();//GARGA LOS CERTIFICADOS
            cargarOrdenesMed();//CARGA LAS ORDENES MEDICAS REALIZADAS
        } catch (Exception e) {
            System.out.println("Usuario nuevo");
        }

    }

    public void cargarHistPaciente() {
        List<HistoriaClinica> listhistTemp = this.historialClinico.getHistoriasclinicas();
        this.listHistoral = new ArrayList<>();
        for (HistoriaClinica t : listhistTemp) {
            if (t.getOrdenesmedicas() == null) {
                listHistoral.add(t);
            }
        }
    }

    public void cargaCertificados() {
        this.listCertificados = new ArrayList<>();
        List<Certificado> listCertT = certFacade.findAll();
        for (Certificado c : listCertT) {
            //Filtro de citas pertenecientes a los usuarios presentes en la session 
            if (c.getPaciente().getCodigo() == this.paciente.getCodigo()) {
                this.listCertificados.add(c);
            }
        }
    }

    public void cargarOrdenesMed() {
        this.listOrdenesMed = new ArrayList<>();
        List<HistoriaClinica> listhistTemp = this.historialClinico.getHistoriasclinicas();
        for (HistoriaClinica t : listhistTemp) {
            if (t.getOrdenesmedicas() != null) {
                this.listOrdenesMed.add(t.getOrdenesmedicas());//ESTA EN PLURAL PERO NO HAY DE QUE PREOCUPARCE SI VA A FUNCIONARF
            }
        }
    }

    public void iniFilas() {
        this.list = new HashSet<FilaCita>();
        this.list.add(new FilaCita(1, mapaFechas.get("0k")));
        this.list.add(new FilaCita(2, mapaFechas.get("1k")));
        this.list.add(new FilaCita(3, mapaFechas.get("2k")));
        this.list.add(new FilaCita(4, mapaFechas.get("3k")));
        this.list.add(new FilaCita(5, mapaFechas.get("4k")));
    }

    public void iniListCitEdit() {

        List<Cita> lisTemp = citaFacade.findAll();
        this.listCitas = new ArrayList<Cita>();
        for (Cita c : lisTemp) {
            if (c.getPaciente().getCodigo() == this.paciente.getCodigo()) {
                this.listCitas.add(c);
            }
        }
    }

    /*METODOS*/
    public void agendarCitar(FilaCita c) {

        if (verificarCita(c)) {
            Cita cita = new Cita();
            cita.setDisponibilidad('S');
            cita.setFecha(c.getFecha());
            cita.setHora(c.getSeleccionado());
            cita.setPaciente(this.paciente);
            citaFacade.create(cita);
            iniListCitEdit();//Para actualizar las citas del paciente
        } else {
            System.out.println("cita ya REgistrada");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error ", "Cita ya Registrada."));
        }
    }

    public boolean verificarCita(FilaCita fc) {
        List<Cita> listCitasT = citaFacade.findAll();
        boolean band = true;
        for (Cita c : listCitasT) {
            if (c.getFecha().equals(fc.getFecha()) && c.getHora().equals(fc.getSeleccionado())) {
                band = false;
                break;
            }
        }
        return band;
    }

    public void cambEstado(Cita cita) {
        if (cita.getDisponibilidad() == 'S') {
            cita.setDisponibilidad('N');
        } else {
            cita.setDisponibilidad('S');
        }
        citaFacade.edit(cita);
    }

    public void thisSemana() {
        String dia = obtenerDia();
        System.out.println(dia);
        cargeSemana(pun);
    }

    public void nextSemana() {
        pun += 7;
        String dia = obtenerDia();
        System.out.println(dia);
        cargeSemana(pun);
        iniFilas();
    }

    public void prevSemana() {
        pun -= 7;
        String dia = obtenerDia();
        System.out.println(dia);
        cargeSemana(pun);
        iniFilas();
    }

    public void cargeSemana(int punt) {
        mapaFechas = new HashMap<>();
        String dia = obtenerDia();
        switch (dia) {
            case "Mon":
                punt -= 0;
                break;
            case "Tue":
                punt -= 1;
                break;
            case "Wed":
                punt -= 2;
                break;
            case "Thu":
                punt -= 3;
                break;
            case "Fri":
                punt -= 4;
                break;
            case "Sat":
                punt -= 5;
                break;
            case "Sun":
                punt -= 6;
                break;
            default:

        }

        for (int i = 0; i < 5; i++) {
            Semana(i + punt, i);
        }
    }

    public String obtenerDia() {
        Date someDate = new Date();
        Date newDate = new Date(someDate.getTime() + TimeUnit.DAYS.toMillis(0));
        String[] infoFechas = newDate.toString().split(" ");
        return infoFechas[0];
    }

    public void Semana(int puntero, int index) {

        Date someDate = new Date();

        Date newDate = new Date(someDate.getTime() + TimeUnit.DAYS.toMillis(puntero));

        String[] infoFechas = newDate.toString().split(" ");

        /*System.out.println("DIa: " + infoFechas[0] + " Fecha: MEs:" + infoFechas[1] + " Dia: " + infoFechas[2] + " Año; " + infoFechas[infoFechas.length - 1]);*/
        String fecha = infoFechas[0] + " " + infoFechas[1] + " " + infoFechas[2] + " " + infoFechas[infoFechas.length - 1];
        mapaFechas.put(String.valueOf(index) + "k", fecha);
        /*return infoFechas[1];*/
    }

    public void verMapa() {
        for (Map.Entry<String, String> entry : mapaFechas.entrySet()) {
            System.out.println("clave=" + entry.getKey() + ", valor=" + entry.getValue());
        }
    }

    /*METODOS*/
 /*GET Y SET*/
    public Set<FilaCita> getList() {
        return list;
    }

    public char getCitaLunes() {
        return citaLunes;
    }

    public void setCitaLunes(char citaLunes) {
        this.citaLunes = citaLunes;
    }

    public char getCitaMartes() {
        return citaMartes;
    }

    public void setCitaMartes(char citaMartes) {
        this.citaMartes = citaMartes;
    }

    public char getCitaMiercoles() {
        return citaMiercoles;
    }

    public void setCitaMiercoles(char citaMiercoles) {
        this.citaMiercoles = citaMiercoles;
    }

    public char getCitaJueves() {
        return citaJueves;
    }

    public void setCitaJueves(char citaJueves) {
        this.citaJueves = citaJueves;
    }

    public char getCitaViernes() {
        return citaViernes;
    }

    public void setCitaViernes(char citaViernes) {
        this.citaViernes = citaViernes;
    }

    public void setList(Set<FilaCita> list) {
        this.list = list;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Map<String, String> getMapaFechas() {
        return mapaFechas;
    }

    public void setMapaFechas(Map<String, String> mapaFechas) {
        this.mapaFechas = mapaFechas;
    }

    public List<Cita> getListCitas() {
        return listCitas;
    }

    public void setListCitas(List<Cita> listCitas) {
        this.listCitas = listCitas;
    }

    public List<Certificado> getListCertificados() {
        return listCertificados;
    }

    public void setListCertificados(List<Certificado> listCertificados) {
        this.listCertificados = listCertificados;
    }

    public List<OrdenMedica> getListOrdenesMed() {
        return listOrdenesMed;
    }

    public void setListOrdenesMed(List<OrdenMedica> listOrdenesMed) {
        this.listOrdenesMed = listOrdenesMed;
    }

    public List<HistoriaClinica> getListHistoral() {
        return listHistoral;
    }

    public void setListHistoral(List<HistoriaClinica> listHistoral) {
        this.listHistoral = listHistoral;
    }

}
