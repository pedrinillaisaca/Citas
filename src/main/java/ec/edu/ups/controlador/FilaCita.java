package ec.edu.ups.controlador;



import java.io.Serializable;

import java.util.Objects;

public class FilaCita implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String fecha;
    private boolean editable;
    private String[] mapValue = {"Elegir","8:30","9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30","15:00","15:30","16:00","16:30","17:00","17:30" };
    private String seleccionado;


    public FilaCita(Integer id,String fecha){
       this.id=id;
       this.fecha=fecha;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String[] getMapValue() {
        return mapValue;
    }

    public void setMapValue(String[] mapValue) {
        this.mapValue = mapValue;
    }

    public String getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(String seleccionado) {
        this.seleccionado = seleccionado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilaCita filaCita = (FilaCita) o;
        return id == filaCita.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
