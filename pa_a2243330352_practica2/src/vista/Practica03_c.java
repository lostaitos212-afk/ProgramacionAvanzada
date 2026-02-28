package vista;

import controlador.AppService;
import controlador.CategoriaController;

public class Practica03_c extends CategoriaInternalFrame {
    public Practica03_c(AppService service) {
        super();
        new CategoriaController(this, service);
    }
}