package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FormularioVentas extends JInternalFrame {
    public JComboBox<String> comboProductos;
    public JTextField txtCantidad, txtSubtotal, txtIVA, txtTotal;
    public JButton btnAgregar, btnProcesar, btnLimpiar, btnExportar, btnQuitar; 
    public JTable tablaVenta;
    public DefaultTableModel modeloTabla;

    public FormularioVentas() {
        super("Punto de Venta", true, true, true, true);
        setSize(800, 550);
        setLayout(new BorderLayout(10, 10));

        JPanel pNorte = new JPanel(new FlowLayout());
        comboProductos = new JComboBox<>();
        txtCantidad = new JTextField("1", 5);
        btnAgregar = new JButton("Añadir a Carrito");
        
        pNorte.add(new JLabel("Producto:"));
        pNorte.add(comboProductos);
        pNorte.add(new JLabel("Cantidad:"));
        pNorte.add(txtCantidad);
        pNorte.add(btnAgregar);

        modeloTabla = new DefaultTableModel(new Object[]{"Cód", "Descrip", "Cant", "P.Unit", "Total"}, 0);
        tablaVenta = new JTable(modeloTabla);
        
        JPanel pSur = new JPanel(new GridLayout(1, 2));
        
        JPanel pTotales = new JPanel(new GridLayout(3, 2, 5, 5));
        txtSubtotal = new JTextField("0.00"); txtSubtotal.setEditable(false);
        txtIVA = new JTextField("0.00"); txtIVA.setEditable(false);
        txtTotal = new JTextField("0.00"); txtTotal.setEditable(false);
        
        pTotales.add(new JLabel("Subtotal:")); pTotales.add(txtSubtotal);
        pTotales.add(new JLabel("IVA (16%):")); pTotales.add(txtIVA);
        pTotales.add(new JLabel("Total:")); pTotales.add(txtTotal);

        JPanel pBotones = new JPanel(new FlowLayout());
        btnLimpiar = new JButton("Limpiar Carrito");
        btnQuitar = new JButton("Quitar Producto");
        btnProcesar = new JButton("Procesar Pago"); 
        btnExportar = new JButton("Exportar Ticket (TXT)");
        
        pBotones.add(btnQuitar); 
        pBotones.add(btnLimpiar);
        pBotones.add(btnProcesar);
        pBotones.add(btnExportar);

        pSur.add(pTotales);
        pSur.add(pBotones);

        add(pNorte, BorderLayout.NORTH);
        add(new JScrollPane(tablaVenta), BorderLayout.CENTER);
        add(pSur, BorderLayout.SOUTH);
    }
}