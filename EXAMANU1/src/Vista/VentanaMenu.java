package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu extends JFrame {
    public JDesktopPane desktopPane; 
    public JMenu menuPuntoVenta; 
    public JMenuItem itemSalir;

    public VentanaMenu() {
        super("Sistema de Abarrotes 'La Pequeña' - MDI");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(64, 64, 64)); 
        add(desktopPane, BorderLayout.CENTER);

        // Barra de Menú
        JMenuBar menuBar = new JMenuBar();

        // Pestaña Sistema
        JMenu menuSistema = new JMenu("Sistema");
        itemSalir = new JMenuItem("Salir del Sistema");
        menuSistema.add(itemSalir);

        menuPuntoVenta = new JMenu("Punto de Venta");
        
        menuBar.add(menuSistema);
        menuBar.add(menuPuntoVenta);

        setJMenuBar(menuBar);
    }
}