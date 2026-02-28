package main;

import controlador.AppService;
import controlador.MainController;
import vista.MenuPrincipalFrame;

import javax.swing.SwingUtilities;

public class PracticaFinal2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppService service = new AppService();
            MenuPrincipalFrame frame = new MenuPrincipalFrame();
            new MainController(frame, service);
            frame.setVisible(true);
        });
    }
}