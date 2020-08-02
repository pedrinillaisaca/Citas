package ec.edu.ups.entidad_ingre_egre_rep;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Entity

public class FacturaEgreso extends FacturaCabecera implements Serializable {
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "facturaEgreso")
    private List<FacturaDetalle> facturadetalles = new ArrayList<>();;
    @ManyToOne
    private Caja caja;

    public FacturaEgreso() {
    }

    public FacturaEgreso(GregorianCalendar fecha, double total_iva, double subtotal, double total, List<FacturaDetalle> facturadetalles, Caja caja) {
        super(fecha, total_iva, subtotal, total);
        this.facturadetalles = facturadetalles;
        this.caja = caja;
    }

    public FacturaEgreso(List<FacturaDetalle> facturadetalles, Caja caja) {
        this.facturadetalles = facturadetalles;
        this.caja = caja;
    }

    public List<FacturaDetalle> getFacturadetalles() {
        return facturadetalles;
    }

    public void setFacturadetalles(List<FacturaDetalle> facturadetalles) {
        this.facturadetalles = facturadetalles;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "FacturaEgreso{" +
                "facturadetalles=" + facturadetalles +
                ", caja=" + caja +
                '}';
    }
}