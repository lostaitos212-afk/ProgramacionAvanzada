package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FormularioInventario extends JInternalFrame {
    public JTextField txtBuscaID, txtBuscaNombre, txtCantidadSurtir, txtUsuarioRecibe;
    public JComboBox<String> comboTipo;
    public JRadioButton rbTodos, rbDisponible, rbAgotado;
    public JTable tablaInventario;
    public JButton btnBuscar, btnLimpiar, btnSurtir, btnModificar, btnEliminar;

    public FormularioInventario() {
        super("Control de Inventario - Entradas", true, true, true, true);
        setSize(850, 500);
        setLayout(new BorderLayout());

        JPanel pnlFiltros = new JPanel(new GridBagLayout());
        pnlFiltros.setBorder(BorderFactory.createTitledBorder("Filtros y Búsqueda"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; pnlFiltros.add(new JLabel("ID:"), gbc);
        txtBuscaID = new JTextField(10); gbc.gridx = 1; pnlFiltros.add(txtBuscaID, gbc);

        gbc.gridx = 0; gbc.gridy = 1; pnlFiltros.add(new JLabel("Nombre:"), gbc);
        txtBuscaNombre = new JTextField(10); gbc.gridx = 1; pnlFiltros.add(txtBuscaNombre, gbc);

        JPanel pnlBotonesFiltro = new JPanel();
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar Filtros");
        pnlBotonesFiltro.add(btnBuscar); pnlBotonesFiltro.add(btnLimpiar);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        pnlFiltros.add(pnlBotonesFiltro, gbc);

        JPanel pnlDerecho = new JPanel(new BorderLayout());
        pnlDerecho.setBorder(BorderFactory.createTitledBorder("Vista de Inventario"));
        
        String[] columnas = {"ID", "Nombre", "Tipo", "Cantidad", "Precio", "Estado"};
        tablaInventario = new JTable(new DefaultTableModel(columnas, 0));
        pnlDerecho.add(new JScrollPane(tablaInventario), BorderLayout.CENTER);

        JPanel pnlAcciones = new JPanel();
        pnlAcciones.setBorder(BorderFactory.createTitledBorder("Acciones de Selección"));
        btnSurtir = new JButton("Crear Nuevo (Surtir)");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        pnlAcciones.add(btnSurtir); pnlAcciones.add(btnModificar); pnlAcciones.add(btnEliminar);
        
        pnlDerecho.add(pnlAcciones, BorderLayout.SOUTH);

        add(pnlFiltros, BorderLayout.WEST);
        add(pnlDerecho, BorderLayout.CENTER);
    }
}