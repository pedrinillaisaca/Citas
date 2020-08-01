package ec.edu.ups.entidad_cit_cons_cert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String nombre;
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "rol")
    private List<Paciente> pacientes;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "rol")
    private List<Medico> medicos;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "rol")
    private List<Colaborador>  listaColaboradores;

    public Rol() {

    }

    public Rol(String nombre, String descripcion, List<Paciente> pacientes, List<Medico> medicos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pacientes = pacientes;
        this.medicos = medicos;
    }

    public Rol(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Colaborador> getListaColaboradores() {
        return listaColaboradores;
    }

    public void setListaColaboradores(List<Colaborador> listaColaboradores) {
        this.listaColaboradores = listaColaboradores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return codigo == rol.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Rol{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", pacientes=" + pacientes +
                ", medicos=" + medicos +
                '}';
    }
}
