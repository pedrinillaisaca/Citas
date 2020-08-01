package ec.edu.ups.entidad_cit_cons_cert;

import ec.edu.ups.entidad_ingre_egre_rep.FacturaCabecera;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaIngreso;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Paciente extends Persona implements Serializable {

    @ManyToOne
    private Rol rol;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "paciente")
    private List<Certificado> certificados;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "paciente")
    private HistorialClinico historialClinico;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "paciente")
    private List<Cita> citas;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "paciente")
    private List<FacturaIngreso> facturas;

    public Paciente() {
    }

    public Paciente(Rol rol, List<Certificado> certificados, HistorialClinico historialClinico, List<Cita> citas, List<FacturaIngreso> facturas) {

        this.rol = rol;
        this.certificados = certificados;
        this.historialClinico = historialClinico;
        this.citas = citas;
        this.facturas = facturas;
    }


    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Certificado> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<Certificado> certificados) {
        this.certificados = certificados;
    }

    public HistorialClinico getHistorialClinico() {
        return historialClinico;
    }

    public void setHistorialClinico(HistorialClinico historialClinico) {
        this.historialClinico = historialClinico;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<FacturaIngreso> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaIngreso> facturas) {
        this.facturas = facturas;
    }


}
