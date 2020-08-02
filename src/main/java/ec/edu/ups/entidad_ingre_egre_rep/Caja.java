package ec.edu.ups.entidad_ingre_egre_rep;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

@Entity
public class Caja implements Serializable {
    @Id
    private int codigo;
    private Date fecha;
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "caja")
    private List<FacturaIngreso> facturasIngreso;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "caja")
    private List<FacturaEgreso> facturaEgresos;

    public Caja() {
        facturasIngreso= new ArrayList<FacturaIngreso>();
    }


    public Caja(int codigo, Date fecha, String descripcion, List<FacturaIngreso> facturasIngreso, List<FacturaEgreso> facturaEgresos) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.facturasIngreso = facturasIngreso;
        this.facturaEgresos = facturaEgresos;
    }
    public Caja(int codigo, Date fecha, String descripcion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<FacturaIngreso> getFacturasIngreso() {
        return facturasIngreso;
    }

    public void setFacturasIngreso(List<FacturaIngreso> facturasIngreso) {
        this.facturasIngreso = facturasIngreso;
    }

    public List<FacturaEgreso> getFacturaEgresos() {
        return facturaEgresos;
    }

    public void setFacturaEgresos(List<FacturaEgreso> facturaEgresos) {
        this.facturaEgresos = facturaEgresos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caja caja = (Caja) o;
        return codigo == caja.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
