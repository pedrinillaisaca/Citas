package ec.edu.ups.controlador;

import ec.edu.ups.ejb.CajaFacade;
import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.ejb.FacturaEgresoFacade;
import ec.edu.ups.ejb.MedicamentoFacade;
import ec.edu.ups.entidad_ingre_egre_rep.Caja;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaDetalle;
import ec.edu.ups.entidad_ingre_egre_rep.FacturaEgreso;
import ec.edu.ups.entidad_ingre_egre_rep.Medicamento;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped


public class FacturaEgresoBean  implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<FacturaDetalle> list;
    private Set<Row> listrow = new HashSet<Row>();
    private int cantidad;
    private String nombre;
    private Medicamento medicamento;
    private  Double subtotal;
    private Date fechaCaducidad;
    private Double precioProducto;
    private int registroSanitario;
    private String mensaje;
    private String fecha;

    private Double descuento;
    private Double iva;
    private Double totalpagar;

    @EJB
    private MedicamentoFacade ejbMedicamentoFacade;

    @EJB
    private FacturaEgresoFacade ejbFacturaEgresoFacade;

    @EJB
    private FacturaDetalleFacade ejbFacturaDetalleFacade;

    @EJB
    private CajaFacade ejbCajaFacade;


    public List<FacturaDetalle> getList() {
        return list;
    }

    public void setList(List<FacturaDetalle> list) {
        this.list = list;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotalpagar() {
        return totalpagar;
    }

    public void setTotalpagar(Double totalpagar) {
        this.totalpagar = totalpagar;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Set<Row> getListrow() {
        return listrow;
    }

    public void setListrow(Set<Row> listrow) {
        this.listrow = listrow;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getRegistroSanitario() {
        return registroSanitario;
    }

    public void setRegistroSanitario(int registroSanitario) {
        this.registroSanitario = registroSanitario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void busqueda (){
        medicamento = ejbMedicamentoFacade.buscarPorNombre(this.nombre);
        System.out.println("encontrado -------->>" + medicamento);
        if (medicamento != null){
            this.mensaje=" medicamento encontrado ";
            this.nombre = medicamento.getNombre();
            this.fechaCaducidad = medicamento.getFecha_caducidad().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            fecha = sdf.format(fechaCaducidad);
            this.precioProducto = medicamento.getPrecio();
            this.registroSanitario = medicamento.getRegistro_sanitario();
        }else{
            this.mensaje =  "El medicamento no encontrado ingrese medicamento a crear";
            this.nombre = "";
            this.fechaCaducidad = null;
            this.precioProducto = null;
            this.registroSanitario = 0;
            fecha=null;

        }
    }

    public void creaMedicamento(){
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat formato = new SimpleDateFormat("yyy/MM/dd");
        try {
            fechaCaducidad = formato.parse(fecha);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        cal.setTime(fechaCaducidad);
        medicamento = new Medicamento(this.nombre, (GregorianCalendar) cal,this.precioProducto,this.registroSanitario,null );
        ejbMedicamentoFacade.create(medicamento);
        this.mensaje = "medicamento nuevo creado";
    }

    public String add() {
        medicamento = ejbMedicamentoFacade.buscarPorNombre(this.nombre);
        if (medicamento != null) {
            this.mensaje = " medicamento encontrado ";
            this.nombre = medicamento.getNombre();
            this.fechaCaducidad = medicamento.getFecha_caducidad().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            fecha = sdf.format(fechaCaducidad);
            this.precioProducto = medicamento.getPrecio();
            this.registroSanitario = medicamento.getRegistro_sanitario();
            double subtotaldetalle = this.cantidad * medicamento.getPrecio();
            int id = medicamento.getCodigo();
            this.listrow.add(new Row(id, nombre, cantidad, medicamento.getPrecio(), subtotaldetalle));
            this.subtotal = 0.0;
            for (Row p : listrow) {
                subtotal = subtotal + p.getSubtotal();
            }
            this.descuento = 0.00;
            this.iva = subtotal * 0.12;
            this.totalpagar = this.iva + subtotal;

        }else {
            this.mensaje = "medicamento no encontrado";
        }
        return null;
    }

    public void crearFactura(){
        GregorianCalendar fecha = (GregorianCalendar) GregorianCalendar.getInstance();
        int code= ejbCajaFacade.count();
        Caja caja = ejbCajaFacade.find(code);
        FacturaEgreso egreso = new  FacturaEgreso( fecha, this.iva, this.subtotal, this.totalpagar, null, caja);
        ejbFacturaEgresoFacade.create(egreso);
        for(Row p: listrow){
            FacturaDetalle detalle = new FacturaDetalle(egreso, p.getQuantity(), ejbMedicamentoFacade.find(p.getId()), p.getSubtotal());
            ejbFacturaDetalleFacade.create(detalle);
        }

    }




}
