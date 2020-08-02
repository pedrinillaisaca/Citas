package ec.edu.ups.controlador;

import ec.edu.ups.ejb.*;
import ec.edu.ups.entidad_cit_cons_cert.Cita;
import ec.edu.ups.entidad_cit_cons_cert.Paciente;
import ec.edu.ups.entidad_ingre_egre_rep.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import javax.persistence.Transient;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class FacturaIngresoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    private FacturaIngresoFacade ejbFacturaIngreso;
    @EJB
    private FacturaDetalleFacade ejbFacturaDetalle;
    @EJB
    private MedicamentoFacade ejbMedicamentoFacade;
    @EJB
    private PacienteFacade ejbPacienteFacade;
    @EJB
    private CitaFacade ejbCitaFacade;
    @EJB
    private CajaFacade ejbCajaFacade;
    private String fecha;
    private String total_iva;
    private String subtotal;
    private String total;
    private String caja;
    private String paciente;
    private String cita;
    //Detalle de la factura
    private String cantidad;
    private String medicamento;
    private String det_total;
    //Lista para de las detalles de las facturas
    private List<FacturaDetalle> list;
    //Lista de citas segun el paciente;
    private List<Cita> citas_paciente;
    //Mensaje controlador
    private String mensaje;
    //cita seleccionada para facturar
    private String selected_cita;
    private Paciente pacienteAfacturar;
    private FacturaIngreso facturaIngreso;

   private double acumulado_subtotal;
    private  double acumulado_iva;
    private double acumulado_total;


    public FacturaIngresoBean(){
        list= new ArrayList<>();
    }
    @PostConstruct
    public void init(){
        Date d = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        //list=ejbFacturaDetalle.findAll();
        //ejbMedicamentoFacade.create(new Medicamento("Atromicina",cal,40.0,12));

    }

    public FacturaIngreso getFacturaIngreso() {
        return facturaIngreso;
    }

    public void setFacturaIngreso(FacturaIngreso facturaIngreso) {
        this.facturaIngreso = facturaIngreso;
    }

    public String getSelected_cita() {
        return selected_cita;
    }

    public void setSelected_cita(String selected_cita) {
        this.selected_cita = selected_cita;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<FacturaDetalle> getList() {
        return list;
    }

    public void setList(List<FacturaDetalle> list) {
        this.list = list;
    }

    public String getTotal_iva() {
        return total_iva;
    }

    public void setTotal_iva(String total_iva) {
        this.total_iva = total_iva;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getCita() {
        return cita;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDet_total() {
        return det_total;
    }

    public void setDet_total(String det_total) {
        this.det_total = det_total;
    }

    public List<Cita> getCitas_paciente() {
        return citas_paciente;
    }

    public void setCitas_paciente(List<Cita> citas_paciente) {
        this.citas_paciente = citas_paciente;
    }

    public void buscarPaciente() {
        //SE DEBE BUSCAR POR CEDULA;
        try {
            Paciente paciente_buscado = ejbPacienteFacade.buscarPorCedula(paciente);
            setMensaje("Paciente Encontrado");
            citas_paciente=ejbCitaFacade.buscarCitaPorPaciente(paciente_buscado);

        }catch (Exception e){
            setMensaje("PACIENTE NO ENCONTRADO");
            System.out.println("no encontro "+e);
        }
        System.out.println("encontro");
        //int codigo, GregorianCalendar fecha, double total_iva, double subtotal, double total, Caja caja, Paciente paciente, List<FacturaDetalle> facturadetalles, Cita cita

        }
        public void crearCabecera(){
            Date d = new Date();
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(d);
            Paciente paciente_buscado = ejbPacienteFacade.buscarPorCedula(paciente);
            pacienteAfacturar=paciente_buscado;
            Timestamp fecha_hora_cita=Timestamp.valueOf(selected_cita);
            System.out.println("fecha_hora"+ fecha_hora_cita);
            Cita cita_search= ejbCitaFacade.buscarCitaPorFechaHora(fecha_hora_cita);
            Caja caja_actual=ejbCajaFacade.buscarCajaPorCodigo(String.valueOf(ejbCajaFacade.count()));
            this.facturaIngreso= new FacturaIngreso(cal,0.0,0.0,0.0,caja_actual,paciente_buscado,list,cita_search);
            ejbFacturaIngreso.create(facturaIngreso);
        }

        public String addDetalle() {
                Medicamento medicamento_buscado = ejbMedicamentoFacade.buscarPorCodigo(medicamento);
                double total_detalle = medicamento_buscado.getPrecio() * Integer.parseInt(cantidad);
                //this.list.add(new FacturaDetalle(facturaIngreso, Integer.parseInt(cantidad), medicamento_buscado, total_detalle));
             ejbFacturaDetalle.create(new FacturaDetalle(facturaIngreso, Integer.parseInt(cantidad), medicamento_buscado, total_detalle));
             list= new ArrayList<>();  
             list=ejbFacturaDetalle.recuperarDetalles(facturaIngreso);

            for (FacturaDetalle det:list
                 ) {
                this.acumulado_subtotal=this.acumulado_subtotal+det.getTotal();
                this.acumulado_iva=this.acumulado_iva+acumulado_subtotal*0.12;
                this.acumulado_total=this.acumulado_total+ acumulado_subtotal+acumulado_iva;
                this.facturaIngreso.setSubtotal(acumulado_subtotal);
                this.facturaIngreso.setTotal_iva(acumulado_iva);
                this.facturaIngreso.setTotal(acumulado_total);
                this.subtotal=String.valueOf(acumulado_subtotal);
                this.total_iva=String.valueOf(acumulado_iva);
                this.total=String.valueOf(acumulado_total);
                ejbFacturaIngreso.edit(facturaIngreso);
            }
                return null;

        }

        public void agregarConsulta(){
            Timestamp fecha_hora_cita=Timestamp.valueOf(selected_cita);
            Cita cita_search= ejbCitaFacade.buscarCitaPorFechaHora(fecha_hora_cita);
            Medicamento consulta=ejbMedicamentoFacade.buscarPorCodigo("4");
            ejbFacturaDetalle.create(new FacturaDetalle(facturaIngreso, 1,consulta, 50.0));
            list=ejbFacturaDetalle.findAll();
            this.acumulado_total=this.acumulado_total+consulta.getPrecio();
            this.acumulado_subtotal=this.acumulado_subtotal+consulta.getPrecio();
            this.facturaIngreso.setSubtotal(acumulado_subtotal);
            this.facturaIngreso.setTotal(acumulado_total);
            this.total=String.valueOf(acumulado_total);
            this.subtotal=String.valueOf(acumulado_subtotal);
            ejbFacturaIngreso.edit(facturaIngreso);
        }


    }


