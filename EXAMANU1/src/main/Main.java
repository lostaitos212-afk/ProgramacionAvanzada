package main;

import Modelo.GestorProductos;
import Vista.VentanaMenu; 
import Controlador.PrincipalControlador;
import persistencia.ArchivoCSV;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { e.printStackTrace(); }

        SwingUtilities.invokeLater(() -> {
            GestorProductos gestor = new GestorProductos();
            gestor.setLista(ArchivoCSV.cargar());
            
            VentanaMenu principal = new VentanaMenu(); 
            
            new PrincipalControlador(principal, gestor);

            principal.setVisible(true);
            System.out.println("Sistema iniciado. Productos en memoria: " + gestor.getLista().size());
        });
    }
}