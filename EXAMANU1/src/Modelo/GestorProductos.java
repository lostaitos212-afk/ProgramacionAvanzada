package Modelo;

import java.util.ArrayList;
import java.util.Iterator;
import persistencia.ArchivoCSV; 

public class GestorProductos {
    private ArrayList<Producto> lista;

    public GestorProductos() {
        this.lista = new ArrayList<>();
    }
    
    public void guardarEnCSV() {
        ArchivoCSV.exportarCSV(this.lista);
    }

    public boolean surtirInventario(String id, int cantidadAdicional) {
        Producto p = buscar(id);
        if (p != null) {
            int nuevoStock = p.getStock() + cantidadAdicional;
            p.setStock(nuevoStock);
            guardarEnCSV(); 
            return true;
        }
        return false; 
    }

    public boolean insertar(Producto nuevo) {
        if (existe(nuevo.getId())) return false;
        lista.add(nuevo);
        guardarEnCSV(); 
        return true;
    }

    public Producto buscar(String id) {
        for (Producto p : lista) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    public boolean actualizar(Producto pModificado) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId().equals(pModificado.getId())) {
                lista.set(i, pModificado);
                guardarEnCSV();
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String id) {
        Iterator<Producto> it = lista.iterator();
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                it.remove();
                guardarEnCSV();
                return true;
            }
        }
        return false;
    }

    public boolean existe(String id) {
        return buscar(id) != null;
    }

    public ArrayList<Producto> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Producto> nuevaLista) {
        this.lista = nuevaLista;
    }
}