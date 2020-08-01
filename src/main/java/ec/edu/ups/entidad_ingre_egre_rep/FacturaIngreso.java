package ec.edu.ups.entidad_ingre_egre_rep;

import ec.edu.ups.entidad_cit_cons_cert.Cita;
import ec.edu.ups.entidad_cit_cons_cert.Paciente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

@Entity
public class FacturaIngreso  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar fecha;
    private double total_iva;
    private double subtotal;
    private double total;
    @ManyToOne
    private Caja caja;

    @ManyToOne
    private Paciente paciente;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "facturaingreso")
    private List<FacturaDetalle> facturadetalles;
    @OneToOne
    private Cita cita;
    public void facturarATercero(Paciente paciente){this.paciente=paciente;}

    public FacturaIngreso() {
        facturadetalles= new ArrayList<FacturaDetalle>();
    }

    public FacturaIngreso(int codigo, GregorianCalendar fecha, double total_iva, double subtotal, double total, Caja caja, Paciente paciente, List<FacturaDetalle> facturadetalles, Cita cita) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.total_iva = total_iva;
        this.subtotal = subtotal;
        this.total = total;
        this.caja = caja;
        this.paciente = paciente;
        this.facturadetalles = facturadetalles;
        this.cita = cita;

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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<FacturaDetalle> getFacturadetalles() {
        return facturadetalles;
    }

    public void setFacturadetalles(List<FacturaDetalle> facturadetalles) {
        this.facturadetalles = facturadetalles;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FacturaIngreso that = (FacturaIngreso) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), codigo);
    }
}
