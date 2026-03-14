package Controlador;

import Modelo.GestorProductos;
import Modelo.Producto;
import Vista.FormularioInventario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarioControlador {
    private FormularioInventario vista;
    private GestorProductos modelo;

    public InventarioControlador(FormularioInventario vista, GestorProductos modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnBuscar.addActionListener(e -> filtrarTabla());
        this.vista.btnLimpiar.addActionListener(e -> {
            limpiarFiltros();
            actualizarTabla();
        });
        
        this.vista.btnSurtir.addActionListener(e -> surtirStock());

        actualizarTabla();
    }

    private void actualizarTabla() {
        DefaultTableModel dt = (DefaultTableModel) vista.tablaInventario.getModel();
        dt.setRowCount(0);
        for (Producto p : modelo.getLista()) {
            String estado = (p.getStock() > 0) ? "Disponible" : "Agotado";
            dt.addRow(new Object[]{p.getId(), p.getNombre(), "General", p.getStock(), p.getPrecio(), estado});
        }
    }

    private void filtrarTabla() {
        String idBusqueda = vista.txtBuscaID.getText().trim();
        String nombreBusqueda = vista.txtBuscaNombre.getText().toLowerCase().trim();
        
        DefaultTableModel dt = (DefaultTableModel) vista.tablaInventario.getModel();
        dt.setRowCount(0);

        for (Producto p : modelo.getLista()) {
            boolean coincideID = idBusqueda.isEmpty() || p.getId().equals(idBusqueda);
            boolean coincideNombre = nombreBusqueda.isEmpty() || p.getNombre().toLowerCase().contains(nombreBusqueda);

            if (coincideID && coincideNombre) {
                String estado = (p.getStock() > 0) ? "Disponible" : "Agotado";
                dt.addRow(new Object[]{p.getId(), p.getNombre(), "General", p.getStock(), p.getPrecio(), estado});
            }
        }
    }

    private void surtirStock() {
        int fila = vista.tablaInventario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto de la tabla para surtir.");
            return;
        }

        String id = vista.tablaInventario.getValueAt(fila, 0).toString();
        Producto p = modelo.buscar(id);

        if (p != null) {
            try {
                String input = JOptionPane.showInputDialog(vista, "Cantidad recibida para " + p.getNombre() + ":");
                if (input != null) {
                    int cantidad = Integer.parseInt(input);
                    String usuario = JOptionPane.showInputDialog(vista, "¿Quién recibe la mercancía?");
                    
                    p.setStock(p.getStock() + cantidad);
                    modelo.guardarEnCSV();
                    
                    actualizarTabla();
                    JOptionPane.showMessageDialog(vista, "Inventario actualizado. Recibido por: " + usuario);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese una cantidad numérica válida.");
            }
        }
    }

    private void limpiarFiltros() {
        vista.txtBuscaID.setText("");
        vista.txtBuscaNombre.setText("");
    }
}