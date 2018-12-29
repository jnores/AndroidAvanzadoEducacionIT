package com.example.yoshknight.androidavanzado_educacionit.factories;

import com.example.yoshknight.androidavanzado_educacionit.modelos.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EducaciónIT on 15/12/2018.
 */

public class ProductosFactory {
    private static List<Producto> listado;

    public static List<Producto> crearProductos() {
        if (listado == null ) {
            listado = new ArrayList<Producto>();
            listado.add(new Producto(0, "Teclado", 200, "Descripcion 1"));
            listado.add(new Producto(1, "Monitor", 200, "Descripcion 2"));
            listado.add(new Producto(2, "Placa de video", 200, "Descripcion 3"));
            listado.add(new Producto(3, "Mouse", 200, "Descripcion 4"));
        }
        return listado;
    }

    public static Producto getItem(int id) {
        if (id < 0 )
            return new Producto(id,"unknown", 0, "unknown");
        else
            return crearProductos().get(id);
    }
}
