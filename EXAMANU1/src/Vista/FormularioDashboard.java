package Vista;

import javax.swing.*;
import java.awt.*;

public class FormularioDashboard extends JInternalFrame {
    public JButton btnProductos, btnInventario, btnVentas;

    public FormularioDashboard() {
        super("Panel de Operaciones", false, true, false, false);
        setSize(600, 250);
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        btnProductos = new JButton("CATÁLOGO PRODUCTOS");
        btnInventario = new JButton("CONTROL INVENTARIO");
        btnVentas = new JButton("PUNTO DE VENTA");

        gbc.gridy = 0;
        gbc.gridx = 0; add(btnProductos, gbc);
        gbc.gridx = 1; add(btnInventario, gbc);
        gbc.gridx = 2; add(btnVentas, gbc);
    }
}