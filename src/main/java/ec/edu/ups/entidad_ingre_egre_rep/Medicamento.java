package ec.edu.ups.entidad_ingre_egre_rep;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.Objects;

@Entity
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String nombre;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar fecha_caducidad;
    private double precio;
    private int registro_sanitario;
    @OneToOne
    private FacturaDetalle facturadetalle;

    public Medicamento() {
    }

    public Medicamento(String nombre, GregorianCalendar fecha_caducidad, double precio, int registro_sanitario, FacturaDetalle facturadetalle) {
        this.nombre = nombre;
        this.fecha_caducidad = fecha_caducidad;
        this.precio = precio;
        this.registro_sanitario = registro_sanitario;
        this.facturadetalle = facturadetalle;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GregorianCalendar getFecha_caducidad() {
        return fecha_caducidad;
    }

    public void setFecha_caducidad(GregorianCalendar fecha_caducidad) {
        this.fecha_caducidad = fecha_caducidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getRegistro_sanitario() {
        return registro_sanitario;
    }

    public void setRegistro_sanitario(int registro_sanitario) {
        this.registro_sanitario = registro_sanitario;
    }

    public FacturaDetalle getFacturadetalle() {
        return facturadetalle;
    }

    public void setFacturadetalle(FacturaDetalle facturadetalle) {
        this.facturadetalle = facturadetalle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicamento that = (Medicamento) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
