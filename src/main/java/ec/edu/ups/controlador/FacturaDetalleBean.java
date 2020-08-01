package ec.edu.ups.controlador;

import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaDetalle;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaIngreso;
import ec.edu.ups.entidad_ingre_egre_rep.Medicamento;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class FacturaDetalleBean implements Serializable {

    private int cantidad;
    private Medicamento medicamento;
    private double total;
    @EJB
    private FacturaDetalleFacade ejbFacturaDetalle;
    private List<FacturaDetalle> list;

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
