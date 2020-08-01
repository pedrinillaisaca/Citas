package ec.edu.ups.controlador;

import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.ejb.FacturaIngresoFacade;
import ec.edu.ups.ejb.MedicamentoFacade;
import ec.edu.ups.ejb.PacienteFacade;
import ec.edu.ups.entidad_cit_cons_cert.Cita;
import ec.edu.ups.entidad_cit_cons_cert.Paciente;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaDetalle;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaIngreso;
import ec.edu.ups.entidad_ingre_egre_rep.Medicamento;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class FacturaIngresoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @EJB
    private FacturaIngresoFacade ejbFacturaIngreso;
    private List<FacturaDetalle> list;
    @EJB
    private FacturaDetalleFacade ejbFacturaDetalle;
    @EJB
    private MedicamentoFacade ejbMedicamentoFacade;
    @EJB
    PacienteFacade ejbPacienteFacade;
    private GregorianCalendar fecha;
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

    public FacturaIngresoBean(){

    }
    @PostConstruct
    public void init(){

       list=ejbFacturaDetalle.findAll();
    }

    public List<FacturaDetalle> getList() {
        return list;
    }

    public void setList(List<FacturaDetalle> list) {
        this.list = list;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
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

    public String add() {
        Medicamento medicamento_encontrado=buscarMedicamento(medicamento);
        double precio_total= medicamento_encontrado.getPrecio()*Integer.parseInt(cantidad);
        Date date = null;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        //FacturaIngreso factIngreso= new FacturaIngreso(1,cal,total_iva,subtotal,total,null,null);
        //paciente, List<FacturaDetalle> facturadetalles, Cita cita
        //this.list.add(new FacturaDetalle(,Integer.parseInt(cantidad),medicamento_encontrado,precio_total));
        return null;
    }
    public Medicamento buscarMedicamento(String nombre){
        try{
            Medicamento medicamento_buscado=ejbMedicamentoFacade.buscarPorNombre(nombre);
            return medicamento_buscado;
        }catch (Exception e){
            System.out.println("Algo salio mal :( = Medicamento no encontrado");
            return null;
        }
    }
}
