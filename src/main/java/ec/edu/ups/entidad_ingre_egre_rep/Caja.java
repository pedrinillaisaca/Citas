package ec.edu.ups.entidad_ingre_egre_rep;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Caja implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "caja")
    private List<FacturaCabecera> facturas;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "caja")
    private List<FacturaIngreso> facturasIngreso;


    public Caja() {
        facturasIngreso= new ArrayList<FacturaIngreso>();
    }

    public Caja(int codigo, List<FacturaCabecera> facturas) {
        this.codigo = codigo;
        this.facturas = facturas;
    }

    public List<FacturaIngreso> getFacturasIngreso() {
        return facturasIngreso;
    }

    public void setFacturasIngreso(List<FacturaIngreso> facturasIngreso) {
        this.facturasIngreso = facturasIngreso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public List<FacturaCabecera> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaCabecera> facturas) {
        this.facturas = facturas;
    }

    public void verificarSaldo(){}
    public void enviarTransaccion(){}

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

    @Override
    public String toString() {
        return "Caja{" +
                "codigo=" + codigo +
                ", facturas=" + facturas +
                '}';
    }
}
