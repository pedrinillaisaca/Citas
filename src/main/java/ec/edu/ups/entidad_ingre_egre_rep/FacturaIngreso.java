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
public class FacturaIngreso extends FacturaCabecera implements Serializable {

    @ManyToOne
    private Caja caja;
    @ManyToOne
    private Paciente paciente;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "facturaingreso")
    private List<FacturaDetalle> facturadetalles = new ArrayList<>();;
    @OneToOne
    private Cita cita;
    public void facturarATercero(Paciente paciente){this.paciente=paciente;}

    public FacturaIngreso() {
        facturadetalles= new ArrayList<FacturaDetalle>();
    }

    public FacturaIngreso(GregorianCalendar fecha, double total_iva, double subtotal, double total, Caja caja, Paciente paciente, List<FacturaDetalle> facturadetalles, Cita cita) {
        super(fecha, total_iva, subtotal, total);
        this.caja = caja;
        this.paciente = paciente;
        this.facturadetalles = facturadetalles;
        this.cita = cita;
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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}