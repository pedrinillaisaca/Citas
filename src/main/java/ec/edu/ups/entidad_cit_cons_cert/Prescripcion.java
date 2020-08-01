package ec.edu.ups.entidad_cit_cons_cert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Prescripcion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    //private String medicamento;
    private String observacion;
    @OneToOne
    private HistoriaClinica historiaclinica;

    public Prescripcion() {
    }

    public Prescripcion(int codigo, String observacion, HistoriaClinica historiaclinica) {
        this.codigo = codigo;
        this.observacion = observacion;
        this.historiaclinica = historiaclinica;
    }
    
     public Prescripcion( String observacion) {      
        this.observacion = observacion;        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        Prescripcion that = (Prescripcion) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Prescripcion{" +
                "codigo=" + codigo +
                ", observacion='" + observacion + '\'' +
                ", historiaclinica=" + historiaclinica +
                '}';
    }
}
