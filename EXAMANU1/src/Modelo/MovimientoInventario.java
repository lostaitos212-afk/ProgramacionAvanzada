package Modelo;

public class MovimientoInventario {
    private String fecha;
    private String idProducto;
    private int cantidadSurtida;
    private String quienRecibio;

    public MovimientoInventario(String idProducto, int cantidad, String quien) {
        this.idProducto = idProducto;
        this.cantidadSurtida = cantidad;
        this.quienRecibio = quien;
        this.fecha = new java.util.Date().toString();
    }

}