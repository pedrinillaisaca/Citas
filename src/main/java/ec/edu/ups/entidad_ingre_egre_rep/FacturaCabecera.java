package ec.edu.ups.entidad_ingre_egre_rep;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class FacturaCabecera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar fecha;
    private double total_iva;
    private double subtotal;
    private double total;
    @ManyToOne
    private Caja caja;

    public FacturaCabecera() {
    }

    public FacturaCabecera(GregorianCalendar fecha, double total_iva, double subtotal, double total, Caja caja) {
        this.fecha = fecha;
        this.total_iva = total_iva;
        this.subtotal = subtotal;
        this.total = total;
        this.caja = caja;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public double getTotal_iva() {
        return total_iva;
    }

    public void setTotal_iva(double total_iva) {
        this.total_iva = total_iva;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturaCabecera that = (FacturaCabecera) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public void calcularTotal(){}
    public void calcularSubtotal(){}



}
