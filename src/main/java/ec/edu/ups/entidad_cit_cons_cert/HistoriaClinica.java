package ec.edu.ups.entidad_cit_cons_cert;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;
/*POR SI ACASO HAY PROBLEMAS CON LA FECHA
ORIGINALMENTE ESTABA COMO TIPO DE FECHA DATE*/

@Entity
public class HistoriaClinica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar fecha;
    private String observacion;
    @ManyToOne
    private HistorialClinico historialclinico;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "historiaclinica")
    private OrdenMedica ordenesmedicas;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "historiaclinica")
    private Prescripcion prescripcion;

    public HistoriaClinica() {
    }

    public HistoriaClinica(GregorianCalendar fecha, String observacion, HistorialClinico historialclinico, OrdenMedica ordenesmedicas, Prescripcion prescripcion) {
        this.fecha = fecha;
        this.observacion = observacion;
        this.historialclinico = historialclinico;
        this.ordenesmedicas = ordenesmedicas;
        this.prescripcion = prescripcion;
    }
    
     public HistoriaClinica(GregorianCalendar fecha, String observacion, Prescripcion prescripcion) {
        this.fecha = fecha;
        this.observacion = observacion;
        this.prescripcion = prescripcion;
    }
     public HistoriaClinica(GregorianCalendar fecha, String observacion, OrdenMedica ordenesmedica) {
        this.fecha = fecha;
        this.observacion = observacion;
        this.ordenesmedicas=ordenesmedica;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {        
        this.codigo = codigo;
    }

    public String getFecha() {
        SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyy");        
        return formato.format(fecha.getTime());
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }
  

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public HistorialClinico getHistorialclinico() {
        return historialclinico;
    }

    public void setHistorialclinico(HistorialClinico historialclinico) {
        this.historialclinico = historialclinico;
    }

    public OrdenMedica getOrdenesmedicas() {
        return ordenesmedicas;
    }

    public void setOrdenesmedicas(OrdenMedica ordenesmedicas) {
        this.ordenesmedicas = ordenesmedicas;
    }

    public Prescripcion getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(Prescripcion prescripcion) {
        this.prescripcion = prescripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoriaClinica that = (HistoriaClinica) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
