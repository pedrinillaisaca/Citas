package ec.edu.ups.entidad_ingre_egre_rep;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class AscientoContable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String descripcion;
    private String cuenta;
    private String tipo;
    @ManyToOne
    private LibroDiario librodiario;

    public AscientoContable() {
    }

    public AscientoContable(int codigo, String descripcion, String cuenta, String tipo, LibroDiario librodiario) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cuenta = cuenta;
        this.tipo = tipo;
        this.librodiario = librodiario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LibroDiario getLibrodiario() {
        return librodiario;
    }

    public void setLibrodiario(LibroDiario librodiario) {
        this.librodiario = librodiario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AscientoContable that = (AscientoContable) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "AscientoContable{" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", cuenta='" + cuenta + '\'' +
                ", tipo='" + tipo + '\'' +
                ", librodiario=" + librodiario +
                '}';
    }

    public void registrarOperacion(){}

}
