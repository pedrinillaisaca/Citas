package ec.edu.ups.entidad_cit_cons_cert;

import ec.edu.ups.entidad_ingre_egre_rep.FacturaIngreso;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
public class Cita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private Timestamp fec_hora;
    private char disponibilidad;

    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "cita")
    private FacturaIngreso facturaingreso;
    private String fecha,hora;

    public Cita() {
    }

    public Cita(Timestamp fec_hora, char disponibilidad, Medico medico, Paciente paciente, FacturaIngreso facturaingreso) {
        this.fec_hora = fec_hora;
        this.disponibilidad = disponibilidad;
        this.medico = medico;
        this.paciente = paciente;
        this.facturaingreso = facturaingreso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Timestamp getFec_hora() {
        return fec_hora;
    }

    public void setFec_hora(Timestamp fec_hora) {
        this.fec_hora = fec_hora;
    }

    public char getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(char disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public FacturaIngreso getFacturaingreso() {
        return facturaingreso;
    }

    public void setFacturaingreso(FacturaIngreso facturaingreso) {
        this.facturaingreso = facturaingreso;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return codigo == cita.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Cita{" +
                "codigo=" + codigo +
                ", fec_hora=" + fec_hora +
                ", disponibilidad=" + disponibilidad +
                ", medico=" + medico +
                ", paciente=" + paciente +
                ", facturaingreso=" + facturaingreso +
                '}';
    }
}
