package Controlador;

import Modelo.GestorProductos;
import Modelo.Producto;
import Vista.FormularioArticulos;
import persistencia.ArchivoCSV;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoControlador implements ActionListener {
    private FormularioArticulos vista;
    private GestorProductos modelo;

    public ProductoControlador(FormularioArticulos vista, GestorProductos modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnConsultar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);

        actualizarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            guardar();
        } else if (e.getSource() == vista.btnConsultar) {
            consultar();
        } else if (e.getSource() == vista.btnModificar) {
            modificar();
        } else if (e.getSource() == vista.btnEliminar) {
            eliminar();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }
    }

    private void guardar() {
        try {
            if (camposVacios()) {
                JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.");
                return;
            }

            Producto p = new Producto(
                vista.txtId.getText(),
                vista.txtNombre.getText(),
                Double.parseDouble(vista.txtPrecio.getText()),
                Integer.parseInt(vista.txtStock.getText())
            );

            if (modelo.insertar(p)) {
                modelo.guardarEnCSV(); 
                actualizarTabla();
                limpiarCampos();
                JOptionPane.showMessageDialog(vista, "Producto guardado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error: El ID ya existe.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Precio y Stock deben ser numéricos.");
        }
    }

    private void consultar() {
        String id = vista.txtId.getText();
        Producto p = modelo.buscar(id);
        if (p != null) {
            vista.txtNombre.setText(p.getNombre());
            vista.txtPrecio.setText(String.valueOf(p.getPrecio()));
            vista.txtStock.setText(String.valueOf(p.getStock()));
        } else {
            JOptionPane.showMessageDialog(vista, "Producto no encontrado.");
        }
    }

    private void modificar() {
        try {
            if (camposVacios()) {
                JOptionPane.showMessageDialog(vista, "Seleccione un producto para modificar.");
                return;
            }

            Producto p = new Producto(
                vista.txtId.getText(),
                vista.txtNombre.getText(),
                Double.parseDouble(vista.txtPrecio.getText()),
                Integer.parseInt(vista.txtStock.getText())
            );

            if (modelo.actualizar(p)) {
                modelo.guardarEnCSV(); 
                actualizarTabla();
                JOptionPane.showMessageDialog(vista, "Producto modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró el ID para modificar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al modificar: revise los datos.");
        }
    }

    private void eliminar() {
        String id = vista.txtId.getText();
        if(id.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese o busque un ID para eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(vista, "¿Desea eliminar el ID: " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (modelo.eliminar(id)) {
                modelo.guardarEnCSV(); 
                actualizarTabla();
                limpiarCampos();
                JOptionPane.showMessageDialog(vista, "Producto eliminado.");
            } else {
                JOptionPane.showMessageDialog(vista, "ID no encontrado.");
            }
        }
    }

    private void actualizarTabla() {
        DefaultTableModel dt = (DefaultTableModel) vista.tabla.getModel();
        dt.setRowCount(0); 
        for (Producto p : modelo.getLista()) {
            dt.addRow(new Object[]{p.getId(), p.getNombre(), p.getPrecio(), p.getStock()});
        }
    }

    private void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
        vista.txtStock.setText("");
        vista.txtId.requestFocus();
    }

    private boolean camposVacios() {
        return vista.txtId.getText().isEmpty() || vista.txtNombre.getText().isEmpty() ||
               vista.txtPrecio.getText().isEmpty() || vista.txtStock.getText().isEmpty();
    }
}