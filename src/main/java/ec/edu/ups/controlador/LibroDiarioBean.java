package ec.edu.ups.controlador;

import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.ejb.FacturaEgresoFacade;
import ec.edu.ups.ejb.FacturaIngresoFacade;
import ec.edu.ups.ejb.LibroDiarioFacade;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaDetalle;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaEgreso;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaIngreso;
import ec.edu.ups.entidad_ingre_egre_rep.LibroDiario;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

@Named
@SessionScoped

public class LibroDiarioBean  implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<FacturaEgreso> listEgreso;
    private List<FacturaIngreso> listIngreso;
    private List<FacturaDetalle> listDetalles;
    private List listDetallesIngresos = new ArrayList();;
    private List listDetallesEgresos = new ArrayList();

    private double sumEgresos;
    private double sumIngresos;
    private double total;
    private String mensaje;

    @EJB
    FacturaEgresoFacade ejbFacturaEgresoFacade;

    @EJB
    FacturaIngresoFacade ejbFacturaIngresoFacade;

    @EJB
    FacturaDetalleFacade ejbFacturaDetalleFacade;

    @EJB
    LibroDiarioFacade ejbLibroDiarioFacade;

    public LibroDiarioBean() {
    }

    @PostConstruct
    public void init(){
        listEgreso = ejbFacturaEgresoFacade.findAll();
        listIngreso = ejbFacturaIngresoFacade.findAll();
        listDetalles = ejbFacturaDetalleFacade.findAll();

    }

    public List<FacturaEgreso> getListEgreso() {
        return listEgreso;
    }

    public void setListEgreso(List<FacturaEgreso> listEgreso) {

        this.listEgreso = listEgreso;

    }

    public List<FacturaIngreso> getListIngreso() {
        return listIngreso;
    }

    public void setListIngreso(List<FacturaIngreso> listIngreso) {

        this.listIngreso = listIngreso;
    }

    public List<FacturaDetalle> getListDetalles() {
        return listDetalles;
    }

    public void setListDetalles(List<FacturaDetalle> listDetalles) {
        this.listDetalles = listDetalles;

    }

    public double getSumEgresos() {
        return sumEgresos;
    }

    public void setSumEgresos(double sumEgresos) {
        this.sumEgresos = sumEgresos;
    }

    public double getSumIngresos() {
        return sumIngresos;
    }

    public void setSumIngresos(double sumIngresos) {
        this.sumIngresos = sumIngresos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List getListDetallesIngresos() {
        return listDetallesIngresos;
    }

    public void setListDetallesIngresos(List listDetallesIngresos) {
        this.listDetallesIngresos = listDetallesIngresos;
    }

    public List getListDetallesEgresos() {
        return listDetallesEgresos;
    }

    public void setListDetallesEgresos(List listDetallesEgresos) {
        this.listDetallesEgresos = listDetallesEgresos;
    }

    public void detallesIngresos(){
    }
    public void detallesEgresos(){
    }

    public void generarLibroDiario(){
        sumIngresos = 0.0;
        for (FacturaIngreso p: listIngreso
        ) {
            sumIngresos = sumIngresos + p.getTotal();
            {
            }
        }
        sumEgresos = 0.0;
        for (FacturaEgreso p: listEgreso
        ) {
            sumEgresos = sumEgresos + p.getTotal();
            {
            }
        }
        total = sumIngresos - sumEgresos;
        GregorianCalendar c1 = (GregorianCalendar) GregorianCalendar.getInstance();
        ejbLibroDiarioFacade.create(new LibroDiario(c1,null));
        this.mensaje = "Se genero exitosamente el libro diario con ganancias --->> $" + total;


    }
}
