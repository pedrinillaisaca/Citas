package ec.edu.ups.entidad_ingre_egre_rep;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class FacturaDetalle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @ManyToOne
    private FacturaEgreso facturaEgreso;
    @ManyToOne
    private FacturaIngreso facturaingreso;
    private int cantidad;
    @OneToOne
    private Medicamento medicamento;
    private double total;

    public FacturaDetalle() {
    }

    public FacturaDetalle(FacturaIngreso facturaingreso, int cantidad, Medicamento medicamento, double total) {
        this.facturaingreso = facturaingreso;
        this.cantidad = cantidad;
        this.medicamento = medicamento;
        this.total = total;
    }
    public FacturaDetalle(FacturaEgreso facturaegreso, int cantidad, Medicamento medicamento, double total) {
        this.facturaEgreso = facturaegreso;
        this.cantidad = cantidad;
        this.medicamento = medicamento;
        this.total = total;
    }
    public FacturaDetalle(FacturaIngreso facturaingreso, FacturaEgreso facturaegreso, int cantidad, Medicamento medicamento, double total) {
        this.facturaingreso = facturaingreso;
        this.facturaEgreso = facturaegreso;
        this.cantidad = cantidad;
        this.medicamento = medicamento;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public FacturaIngreso getFacturaingreso() {
        return facturaingreso;
    }

    public FacturaEgreso getFacturaEgreso() {
        return facturaEgreso;
    }

    public void setFacturaEgreso(FacturaEgreso facturaEgreso) {
        this.facturaEgreso = facturaEgreso;
    }

    public void setFacturaingreso(FacturaIngreso facturaingreso) {
        this.facturaingreso = facturaingreso;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturaDetalle that = (FacturaDetalle) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public void calcularPrecioUnitarioCantidad(){}

    @Override
    public String toString() {
        return "FacturaDetalle{" +
                "codigo=" + codigo +
                ", facturaingreso=" + facturaingreso +
                ", cantidad=" + cantidad +
                ", medicamento=" + medicamento +
                '}';
    }
}
