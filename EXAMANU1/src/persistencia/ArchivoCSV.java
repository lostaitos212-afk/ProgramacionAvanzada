package persistencia;

import Modelo.Producto;
import java.io.*;
import java.util.ArrayList;

public class ArchivoCSV {
    private static final String NOMBRE_ARCHIVO = "productos.csv";

    public static void exportarCSV(ArrayList<Producto> lista) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Producto p : lista) {
                writer.println(p.toString());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public static ArrayList<Producto> cargar() {
        ArrayList<Producto> lista = new ArrayList<>();
        File file = new File(NOMBRE_ARCHIVO);
        if (!file.exists()) return lista;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 4) {
                    lista.add(new Producto(
                        datos[0].trim(), 
                        datos[1].trim(), 
                        Double.parseDouble(datos[2].trim()), 
                        Integer.parseInt(datos[3].trim())
                    ));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar archivo: " + e.getMessage());
        }
        return lista;
    }
}