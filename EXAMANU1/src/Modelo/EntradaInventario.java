package Modelo;

import java.io.Serializable;

public class EntradaInventario {
    private String fecha;
    private String idProducto;
    private int cantidadSurtida;
    private String usuarioResponsable;

    public EntradaInventario(String idProducto, int cantidad, String usuario) {
        this.idProducto = idProducto;
        this.cantidadSurtida = cantidad;
        this.usuarioResponsable = usuario;
        this.fecha = new java.util.Date().toString(); 
    }

    @Override
    public String toString() {
        return fecha + " | Producto: " + idProducto + " | Cant: " + cantidadSurtida + " | Recibió: " + usuarioResponsable;
    }
}