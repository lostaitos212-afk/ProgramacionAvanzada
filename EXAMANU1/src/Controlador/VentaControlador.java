package Controlador;

import Modelo.*;
import Vista.FormularioVentas;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VentaControlador implements ActionListener {
    private FormularioVentas vista;
    private GestorProductos gestor;
    private double acumuladoSubtotal = 0;

    public VentaControlador(FormularioVentas vista, GestorProductos gestor) {
        this.vista = vista;
        this.gestor = gestor;
        for (Producto p : gestor.getLista()) {
            vista.comboProductos.addItem(p.getNombre());
        }

        // Listeners de los botones
        this.vista.btnQuitar.addActionListener(e -> quitarProducto());
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnProcesar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) agregar1();
        if (e.getSource() == vista.btnLimpiar) limpiar();
        if (e.getSource() == vista.btnProcesar) finalizar1();
    }



    private void quitarProducto() {
        int filaSeleccionada = vista.tablaVenta.getSelectedRow();

        if (filaSeleccionada != -1) {
            double subtotalFila = (double) vista.modeloTabla.getValueAt(filaSeleccionada, 4);
            
            calcularTotales(-subtotalFila);

            vista.modeloTabla.removeRow(filaSeleccionada);
            
            JOptionPane.showMessageDialog(vista, "Producto quitado del carrito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Por favor, selecciona un producto de la tabla.");
        }
    }

    private void calcularTotales(double variacionSubtotal) {
        acumuladoSubtotal += variacionSubtotal;
        
        if (acumuladoSubtotal < 0) acumuladoSubtotal = 0;

        double iva = acumuladoSubtotal * 0.16;
        double total = acumuladoSubtotal + iva;

        vista.txtSubtotal.setText(String.format("%.2f", acumuladoSubtotal));
        vista.txtIVA.setText(String.format("%.2f", iva));
        vista.txtTotal.setText(String.format("%.2f", total));
    }

    private void limpiar() {
        vista.modeloTabla.setRowCount(0);
        acumuladoSubtotal = 0;
        vista.txtSubtotal.setText("0.00");
        vista.txtIVA.setText("0.00");
        vista.txtTotal.setText("0.00");
    }
    private void finalizar1() {
        if (vista.modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(vista, "El carrito está vacío.");
            return;
        }

        try (java.io.PrintWriter out = new java.io.PrintWriter(new java.io.FileWriter("Ticket_Venta.txt"))) {
            out.println("      ABARROTES 'LA PEQUEÑA'      ");
            out.println("----------------------------------");
            out.println("PRODUCTO          CANT    TOTAL");
            
            for (int i = 0; i < vista.modeloTabla.getRowCount(); i++) {
                String nombre = vista.modeloTabla.getValueAt(i, 1).toString();
                String cant = vista.modeloTabla.getValueAt(i, 2).toString();
                String total = vista.modeloTabla.getValueAt(i, 4).toString();
                out.printf("%-15s   %-5s   $%s%n", nombre, cant, total);
            }
            
            out.println("----------------------------------");
            out.println("SUBTOTAL: $" + vista.txtSubtotal.getText());
            out.println("IVA (16%): $" + vista.txtIVA.getText());
            out.println("TOTAL A PAGAR: $" + vista.txtTotal.getText());
            out.println("----------------------------------");
            out.println("     ¡GRACIAS POR SU COMPRA!      ");

            JOptionPane.showMessageDialog(vista, "Venta procesada con éxito. Ticket generado.");
            limpiar(); 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al guardar el ticket: " + ex.getMessage());
        }
        
    }
    private void agregar1() {
        try {
            String nombreSel = (String) vista.comboProductos.getSelectedItem();
            int cantSolicitada = Integer.parseInt(vista.txtCantidad.getText());
            
            for (Producto p : gestor.getLista()) {
                if (p.getNombre().equals(nombreSel)) {
                    
                    if (p.getStock() >= cantSolicitada) {
                        p.setStock(p.getStock() - cantSolicitada); 
                        
                        double sub = p.getPrecio() * cantSolicitada;
                        vista.modeloTabla.addRow(new Object[]{p.getId(), p.getNombre(), cantSolicitada, p.getPrecio(), sub});
                        calcularTotales(sub);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Solo quedan " + p.getStock() + " unidades.");
                    }
                    return;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error en cantidad.");
        }
    }
    private void finalizar() {
        if(vista.modeloTabla.getRowCount() == 0) return;
        JOptionPane.showMessageDialog(vista, "Venta procesada.\nTotal: $" + vista.txtTotal.getText() + "\nTicket generado.");
        limpiar();
    }
} 