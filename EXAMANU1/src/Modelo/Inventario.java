package Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Inventario {
    private String idProducto;
    private String nombreProducto;
    private int cantidadSurtida;
    private String usuarioRecibio;
    private String fecha;

    public Inventario(String idProducto, String nombreProducto, int cantidadSurtida, String usuarioRecibio) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadSurtida = cantidadSurtida;
        this.usuarioRecibio = usuarioRecibio;
        this.fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Getters y toString para el CSV
    @Override
    public String toString() {
        return idProducto + "," + nombreProducto + "," + cantidadSurtida + "," + usuarioRecibio + "," + fecha;
    }
    
}