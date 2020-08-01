package ec.edu.ups.entidad_cit_cons_cert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class HistorialClinico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "historialclinico")
    private List<HistoriaClinica> historiasclinicas;
    @OneToOne
    private Paciente paciente;

    public HistorialClinico() {
    }

    public HistorialClinico(int codigo, String descripcion, List<HistoriaClinica> historiasclinicas, Paciente paciente) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.historiasclinicas = historiasclinicas;
        this.paciente = paciente;
    }
    
    
    public void addHistoria(HistoriaClinica hc){
        this.historiasclinicas.add(hc);        
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

    public List<HistoriaClinica> getHistoriasclinicas() {
        return historiasclinicas;
    }

    public void setHistoriasclinicas(List<HistoriaClinica> historiasclinicas) {
        this.historiasclinicas = historiasclinicas;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialClinico that = (HistorialClinico) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "HistorialClinico{" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", historiasclinicas=" + historiasclinicas +
                ", paciente=" + paciente +
                '}';
    }
}
