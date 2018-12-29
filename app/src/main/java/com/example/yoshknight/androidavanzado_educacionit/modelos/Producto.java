package com.example.yoshknight.androidavanzado_educacionit.modelos;

/**
 * Created by EducaciónIT on 15/12/2018.
 */

public class Producto {
    private int id;
    private String nombre;
    private int precio;
    private String descripcion;

    public Producto(int id, String nombre, int precio, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
