package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FormularioArticulos extends JInternalFrame {
  
    public JTextField txtId, txtNombre, txtPrecio, txtStock;
    public JRadioButton rbActivo, rbInactivo;
    public JButton btnGuardar, btnConsultar, btnModificar, btnEliminar, btnLimpiar;
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    public FormularioArticulos() {
        super("Gestión de Catálogo de Productos", true, true, true, true);
        setSize(800, 500);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("ID Producto:"), gbc);
        txtId = new JTextField(10);
        gbc.gridx = 1; add(txtId, gbc);

        // Nombre
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(15);
        gbc.gridx = 1; add(txtNombre, gbc);

        // Precio
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Precio:"), gbc);
        txtPrecio = new JTextField(10);
        gbc.gridx = 1; add(txtPrecio, gbc);

        // Stock
        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Stock Inicial:"), gbc);
        txtStock = new JTextField(10);
        gbc.gridx = 1; add(txtStock, gbc);

        gbc.gridx = 0; gbc.gridy = 4; add(new JLabel("Estado:"), gbc);
        rbActivo = new JRadioButton("Activo", true);
        rbInactivo = new JRadioButton("Inactivo");
        ButtonGroup grupoEstado = new ButtonGroup();
        grupoEstado.add(rbActivo);
        grupoEstado.add(rbInactivo);
        
        JPanel panelRadio = new JPanel();
        panelRadio.add(rbActivo); panelRadio.add(rbInactivo);
        gbc.gridx = 1; add(panelRadio, gbc);

        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnConsultar = new JButton("Consultar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        
        panelBotones.add(btnGuardar); panelBotones.add(btnConsultar);
        panelBotones.add(btnModificar); panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        add(panelBotones, gbc);

        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre", "Precio", "Stock"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        
        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 5;
        gbc.weightx = 1.0; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
    }
}