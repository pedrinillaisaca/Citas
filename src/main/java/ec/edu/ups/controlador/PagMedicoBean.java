package ec.edu.ups.controlador;

import ec.edu.ups.ejb.CertificadoFacade;
import ec.edu.ups.ejb.CitaFacade;
import ec.edu.ups.ejb.HistoriaClinicaFacade;
import ec.edu.ups.ejb.HistorialClinicoFacade;
import ec.edu.ups.ejb.OrdenMedicaFacade;
import ec.edu.ups.ejb.PacienteFacade;
import ec.edu.ups.ejb.PrescripcionFacade;
import ec.edu.ups.entidad_cit_cons_cert.Certificado;
import ec.edu.ups.entidad_cit_cons_cert.Cita;
import ec.edu.ups.entidad_cit_cons_cert.HistoriaClinica;
import ec.edu.ups.entidad_cit_cons_cert.HistorialClinico;
import ec.edu.ups.entidad_cit_cons_cert.Medico;
import ec.edu.ups.entidad_cit_cons_cert.OrdenMedica;
import ec.edu.ups.entidad_cit_cons_cert.Paciente;
import ec.edu.ups.entidad_cit_cons_cert.Prescripcion;
import ec.edu.ups.utilidades.Session;



import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class PagMedicoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    /*VARIABLES PARA LA PAGINA MEDICO*/
    private String prescrpDetalle;
    private String certDetalle;
    private List<Cita> listCitas;
    private List<Cita> listCitasHoy;
    private Medico medico;
    private Paciente paciente;
    private Cita cita;
    /*EJB´S*/
    @EJB
    private PacienteFacade pacienteFacade;
    @EJB
    private CitaFacade citaFacade;
    @EJB
    private HistorialClinicoFacade historialCliFac;
    @EJB
    private OrdenMedicaFacade ordenMedFac;
//    private PrescripcionFacade prescripFacade;
    @EJB
    private HistoriaClinicaFacade historiaClinicaFac;
    @EJB
    private CertificadoFacade certFacade;
    /*variables para la Reunion Cita*/
    private String nombrePaciente;
    private HistorialClinico historialClinico;
    private List<HistoriaClinica> listHistoral;
    private String areaPrescipcion;
    /**/
 /*variables para la generacion de certificados*/
    private String areaCertificado;
    private List<Certificado> listCertificados;

    /**/
 /*variables para ordenes Medcias*/
    private List<OrdenMedica> listOrdenesMed;
    private String areaOrdenMedica;
    private String servOrden;

    /*variables para ordenes Medcias*/
    public PagMedicoBean() {

    }

    public void verHistorial() {

    }

    @PostConstruct
    public void init() {
        try {
            listCitas = citaFacade.findAll();
            filtroCitasHoy();
            this.medico = (Medico) Session.getSessionMap().get("medico");
        } catch (Exception e) {
            System.out.println("Revisar contructor PagMedicoBean");
        }
    }

    public void filtroCitasHoy() {
        listCitasHoy = new ArrayList<Cita>();
        for (Cita c : listCitas) {
            if (c.getFecha().equals(fechaActual()) && c.getDisponibilidad() == 'S') {
                listCitasHoy.add(c);
            }
        }
    }

    public void listarCitmed() {
        listCitas = citaFacade.findAll();
        filtroCitasHoy();
    }

    public String fechaActual() {
        Date someDate = new Date();
        Date newDate = new Date(someDate.getTime() + TimeUnit.DAYS.toMillis(0));
        String[] infoFechas = newDate.toString().split(" ");
        String fecha = infoFechas[0] + " " + infoFechas[1] + " " + infoFechas[2] + " " + infoFechas[infoFechas.length - 1];
        return fecha;
    }

    public void ejecCita(Cita c) {/*EJECUCION DE LA CITA MEDICA*/
        this.cita = c;
        this.cita.setMedico(this.medico);
        this.cita.setDisponibilidad('N');
        this.paciente = this.cita.getPaciente();
        citaFacade.edit(this.cita);
        if (!verificarHistorial(this.cita.getPaciente())) {
            String nomApellTemp = this.cita.getPaciente().getNombre() + " " + this.cita.getPaciente().getApellido();
            this.nombrePaciente = nomApellTemp;
            this.historialClinico = this.cita.getPaciente().getHistorialClinico();
            cargarHistPaciente();//CARGA EL HISTORIAL DEL PACIENTE
            cargaCertificados();//GARGA LOS CERTIFICADOS
            cargarOrdenesMed();//CARGA LAS ORDENES MEDICAS REALIZADAS
        } else {
            System.out.println("$$$El paciente no tiene historial pero acabamos de crearlo uno...");
            actualizarUtilidades();
        }
    }

    public void actualizarUtilidades() {
        String nomApellTemp = this.cita.getPaciente().getNombre() + " " + this.cita.getPaciente().getApellido();
        this.nombrePaciente = nomApellTemp;
        List<HistorialClinico> listCliT=historialCliFac.findAll();
        
        System.out.println("TIENE QUE SER 3 "+listCliT.size());
        for (HistorialClinico ht : listCliT) {
            if(ht.getPaciente().getCodigo()==this.paciente.getCodigo()){
                System.out.println("PUTA HIS ENCONTRADA!!");
                this.historialClinico=ht;
                System.out.println("$$$$$$$$$$$ "+this.historialClinico.getDescripcion());
            }                
        }
        cargarHistPaciente();//CARGA EL HISTORIAL DEL PACIENTE
        cargaCertificados();//GARGA LOS CERTIFICADOS
        cargarOrdenesMed();//CARGA LAS ORDENES MEDICAS REALIZADAS 

    }
    
    public boolean verificarHistorial(Paciente p) {
        boolean band = false;
        if (this.paciente.getHistorialClinico() == null) {
            HistorialClinico h = new HistorialClinico();
            h.setDescripcion("historial de: " + paciente.getNombre() + " " + paciente.getApellido());
            h.setPaciente(this.paciente);
            historialCliFac.create(h);
            band = true;
        }
        return band;
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

    public void guardarCambios() {
        /*HAY QUE HACER ALGUNOS CRUZES PARA QUE SIRVA ESA HUEBADA DE JPA*/
        Prescripcion p = new Prescripcion(this.areaPrescipcion);
        HistoriaClinica h = new HistoriaClinica(new GregorianCalendar(), "Historia de: " + this.nombrePaciente, p);
        p.setHistoriaclinica(h);
        h.setPrescripcion(p);
        h.setHistorialclinico(this.historialClinico);
        this.historialClinico.addHistoria(h);
        this.paciente.setHistorialClinico(historialClinico);
        this.pacienteFacade.edit(this.paciente);
        this.areaPrescipcion = "";

        cargarHistPaciente();
    }

    /*AREA LOGICA CERTIFICADOS*/
    public void generarCert() {
        Certificado c = new Certificado();
        c.setFecha(new GregorianCalendar());
        c.setObservacion(this.areaCertificado);
        c.setPaciente(this.paciente);
        c.setMedico(this.medico);
        certFacade.create(c);
        this.areaCertificado = "";
        cargaCertificados();
    }

    public void cargaCertificados() {
        this.listCertificados = new ArrayList<>();
        List<Certificado> listCertT = certFacade.findAll();
        for (Certificado c : listCertT) {
            if (c.getMedico().getCodigo() == this.medico.getCodigo() && c.getPaciente().getCodigo() == this.paciente.getCodigo()) {
                this.listCertificados.add(c);
            }
        }
    }

    /*AREA  LOGICA CERTIFICADOS*/
 /*AREA LOGICA ORDENES MEDICAS*/
    public void generarOrden() {
        OrdenMedica om = new OrdenMedica(this.areaOrdenMedica, this.servOrden);
        HistoriaClinica h = new HistoriaClinica(new GregorianCalendar(), "Orden de: " + this.nombrePaciente, om);
//        System.out.println("$$$$$$$$$$ Descrip Orden "+this.areaOrdenMedica+"$$$$$$$$$$ Servicio "+this.servOrden);

        om.setHistoriaclinica(h);
        h.setOrdenesmedicas(om);

//        p.setHistoriaclinica(h);
//        h.setPrescripcion(p);
//        /*==============================*/        
//        om.setHistoriaclinica(h);
//        h.setOrdenesmedicas(om);
//        /*==============================*/
        h.setHistorialclinico(this.historialClinico);
        this.historialClinico.addHistoria(h);
        this.paciente.setHistorialClinico(historialClinico);
        this.pacienteFacade.edit(this.paciente);
        this.areaOrdenMedica = "";
        this.servOrden = "";
//        cargarHistPaciente();
        cargarOrdenesMed();
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

    /*AREA LOGICA ORDENES MEDICAS*/

 /*zona set/get*/
    public String getPrescrpDetalle() {
        return prescrpDetalle;
    }

    public void setPrescrpDetalle(String prescrpDetalle) {
        this.prescrpDetalle = prescrpDetalle;
    }

    public String getCertDetalle() {
        return certDetalle;
    }

    public void setCertDetalle(String certDetalle) {
        this.certDetalle = certDetalle;
    }

    public List<Cita> getListCitas() {
        return listCitas;
    }

    public void setListCitas(List<Cita> listCitas) {
        this.listCitas = listCitas;
    }

    public List<Cita> getListCitasHoy() {
        return listCitasHoy;
    }

    public void setListCitasHoy(List<Cita> listCitasHoy) {
        this.listCitasHoy = listCitasHoy;
    }

    /*set y get de los datos necesarios para reunion*/
    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public List<HistoriaClinica> getListHistoral() {
        return listHistoral;
    }

    public void setListHistoral(List<HistoriaClinica> listHistoral) {
        this.listHistoral = listHistoral;
    }

    public String getAreaPrescipcion() {
        return areaPrescipcion;
    }

    public void setAreaPrescipcion(String areaPrescipcion) {
        this.areaPrescipcion = areaPrescipcion;
    }

    public String getAreaCertificado() {
        return areaCertificado;
    }

    public void setAreaCertificado(String areaCertificado) {
        this.areaCertificado = areaCertificado;
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

    public String getAreaOrdenMedica() {
        return areaOrdenMedica;
    }

    public void setAreaOrdenMedica(String areaOrdenMedica) {
        this.areaOrdenMedica = areaOrdenMedica;
    }

    public String getServOrden() {
        return servOrden;
    }

    public void setServOrden(String servOrden) {
        this.servOrden = servOrden;
    }

}
