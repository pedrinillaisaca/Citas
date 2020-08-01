package ec.edu.ups.entidad_cit_cons_cert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrdenMedica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String descripcion;
    private String servicio;
    @OneToOne
    private HistoriaClinica historiaclinica;

    public OrdenMedica() {
    }

    public OrdenMedica(int codigo, String descripcion, String servicio, HistoriaClinica historiaclinica) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.servicio = servicio;
        this.historiaclinica = historiaclinica;
    }

    public OrdenMedica(String descripcion, String servicio) {       
        this.descripcion = descripcion;
        this.servicio = servicio;        
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

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public HistoriaClinica getHistoriaclinica() {
        return historiaclinica;
    }

    public void setHistoriaclinica(HistoriaClinica historiaclinica) {
        this.historiaclinica = historiaclinica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdenMedica that = (OrdenMedica) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "OrdenMedica{" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", servicio='" + servicio + '\'' +
                ", historiaclinica=" + historiaclinica +
                '}';
    }
}
