package Modelo;

import java.util.ArrayList;

public class ticket {
    private ArrayList<Venta> listaArticulos;
    private final double IVA_VALOR = 0.16;

    public ticket() {
        this.listaArticulos = new ArrayList<>();
    }

    public void agregarArticulo(Venta v) {
        listaArticulos.add(v);
    }

    public double calcularSubtotal() {
        double sub = 0;
        for (Venta v : listaArticulos) {
            sub += v.getTotal();
        }
        return sub;
    }

    public double calcularIVA() {
        return calcularSubtotal() * IVA_VALOR;
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    public ArrayList<Venta> getListaArticulos() {
        return listaArticulos;
    }

    public void limpiar() {
        listaArticulos.clear();
    }
}