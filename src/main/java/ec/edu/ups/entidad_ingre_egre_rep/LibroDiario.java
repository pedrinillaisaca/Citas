package ec.edu.ups.entidad_ingre_egre_rep;

import javax.persistence.*;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

@Entity
public class LibroDiario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar fecha;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "librodiario")
    private List<AscientoContable> ascientos;

    public LibroDiario() {
    }

    public LibroDiario(int codigo, GregorianCalendar fecha, List<AscientoContable> ascientos) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.ascientos = ascientos;
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

    public List<AscientoContable> getAscientos() {
        return ascientos;
    }

    public void setAscientos(List<AscientoContable> ascientos) {
        this.ascientos = ascientos;
    }


    public void calcularIngresos(){}
    public void calcularEgresos(){}
    public void calcularSaldo(){}
    public void visualizarResumenComprobantes(){}
    public void generarReporte(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibroDiario that = (LibroDiario) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "LibroDiario{" +
                "codigo=" + codigo +
                ", fecha=" + fecha +
                ", ascientos=" + ascientos +
                '}';
    }
}
