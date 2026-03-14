package Modelo;

import javax.swing.JDesktopPane;

public class Venta {
    private String codigo;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;
    private double total;
	public JDesktopPane desktopPane;
	public Object menuPuntoVenta;

    public Venta(String codigo, String descripcion, int cantidad, double precioUnitario) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = cantidad * precioUnitario;
    }

    // Getters para que el Controlador pueda leer los datos
    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getTotal() { return total; }
}