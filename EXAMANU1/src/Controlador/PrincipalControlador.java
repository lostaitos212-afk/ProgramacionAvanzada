package Controlador;

import Vista.*;
import Modelo.GestorProductos;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.Dimension;

public class PrincipalControlador {
    private VentanaMenu vistaPrincipal;
    private GestorProductos gestor;

    public PrincipalControlador(VentanaMenu vistaPrincipal, GestorProductos gestor) {
        this.vistaPrincipal = vistaPrincipal;
        this.gestor = gestor;

        this.vistaPrincipal.menuPuntoVenta.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                abrirDashboard();
            }
            @Override public void menuDeselected(MenuEvent e) {}
            @Override public void menuCanceled(MenuEvent e) {}
        });
        
        // Listener para salir
        this.vistaPrincipal.itemSalir.addActionListener(e -> System.exit(0));
    }

    private void abrirDashboard() {
        FormularioDashboard db = new FormularioDashboard();
        this.vistaPrincipal.desktopPane.add(db);
        
        
        db.btnProductos.addActionListener(e -> {
            db.dispose(); 
            FormularioArticulos fArt = new FormularioArticulos();
            this.vistaPrincipal.desktopPane.add(fArt);
            
            new ProductoControlador(fArt, gestor);
            
            fArt.setVisible(true);
            centrarVentanaInterna(fArt);
        });

        db.btnInventario.addActionListener(e -> {
            db.dispose();
        
            FormularioInventario fInv = new FormularioInventario(); 
            this.vistaPrincipal.desktopPane.add(fInv);
            new InventarioControlador(fInv, gestor); 
            fInv.setVisible(true);
      
            centrarVentanaInterna(fInv);
        });

        db.btnVentas.addActionListener(e -> {
            db.dispose();
            FormularioVentas fVen = new FormularioVentas();
            this.vistaPrincipal.desktopPane.add(fVen);
            
            new VentaControlador(fVen, gestor);
            
            fVen.setVisible(true);
            centrarVentanaInterna(fVen);
        });

        db.setVisible(true);
        centrarVentanaInterna(db);
    }

    private void centrarVentanaInterna(javax.swing.JInternalFrame frame) {
        Dimension desktopSize = vistaPrincipal.desktopPane.getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                          (desktopSize.height - jInternalFrameSize.height) / 2);
    }
}