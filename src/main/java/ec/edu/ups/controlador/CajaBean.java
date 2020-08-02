package ec.edu.ups.controlador;

import ec.edu.ups.ejb.CajaFacade;
import ec.edu.ups.ejb.FacturaEgresoFacade;
import ec.edu.ups.ejb.FacturaIngresoFacade;
import ec.edu.ups.entidad_ingre_egre_rep.Caja;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaEgreso;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaIngreso;
//import org.jboss.weld.context.ejb.Ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class CajaBean implements Serializable {
    @EJB
    private CajaFacade ejbCajaFacade;
    @EJB
    private FacturaIngresoFacade ejbFacturaIngresoFacade;
    @EJB
    private FacturaEgresoFacade ejbFacturaEgresoFacade;

    private List<FacturaIngreso> facturaIngresoList;
    private List<FacturaEgreso>facturaEgresoList;
    private String fechaCaja;
    private String mensaje;
    public CajaBean() {
        facturaIngresoList= new ArrayList<>();
        facturaEgresoList= new ArrayList<>();
    }
    @PostConstruct
    public void init(){

    }

    public String getFechaCaja() {
        return fechaCaja;
    }

    public void setFechaCaja(String fechaCaja) {
        this.fechaCaja = fechaCaja;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<FacturaIngreso> getFacturaIngresoList() {
        return facturaIngresoList;
    }

    public void setFacturaIngresoList(List<FacturaIngreso> facturaIngresoList) {
        this.facturaIngresoList = facturaIngresoList;
    }

    public List<FacturaEgreso> getFacturaEgresoList() {
        return facturaEgresoList;
    }

    public void setFacturaEgresoList(List<FacturaEgreso> facturaEgresoList) {
        this.facturaEgresoList = facturaEgresoList;
    }
    public void buscarCaja() throws ParseException {
        System.out.println("Fecha a buscar " + this.fechaCaja);
        Caja caja_buscada=ejbCajaFacade.buscarCajaPorFecha(this.fechaCaja);
        this.mensaje="SE ENCONTRO LA CAJA";
        facturaEgresoList=ejbFacturaEgresoFacade.buscarPorFacturaEgresoCodigo(caja_buscada);
        facturaIngresoList=ejbFacturaIngresoFacade.buscarPorFacturaIngresoCodigo(caja_buscada);

    }
    public void cerrarCaja(){
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        int code= ejbCajaFacade.count()+1;
        Caja nueva_caja= new Caja(code,date,"Cierre de caja");
        ejbCajaFacade.create(nueva_caja);
    }
}