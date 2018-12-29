package com.example.yoshknight.androidavanzado_educacionit.clases;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.yoshknight.androidavanzado_educacionit.activities.DetalleProductoActivity;
import com.example.yoshknight.androidavanzado_educacionit.fragments.DetalleProductoFragment;
import com.example.yoshknight.androidavanzado_educacionit.fragments.ListadoProductosFragment;
import com.example.yoshknight.androidavanzado_educacionit.R;
import com.example.yoshknight.androidavanzado_educacionit.modelos.Producto;

/**
 * Implementacion para Tablets.
 * - Layout en el directorio layout-sw600dp
 * - En la activity, verificar si estamos en una tablet o en un celular.
 * - Si es tablet => crear fragment.
 * - Si no es tablet => Abrir activity.
 *
 */
public class Clase1Fragments extends AppCompatActivity implements ListadoProductosFragment.ProductoSeleccionado, DetalleProductoFragment.ProductoComprado {

    private boolean esPanelDoble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clase_1_fragments);

        setupToolbar();

        esPanelDoble = findViewById(R.id.contenedor_detalle_producto) != null;

    }

    private void setupToolbar(){
        // Agregar toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lista de productos");
        setSupportActionBar(toolbar);


    }


    @Override
    public void onProductoSeleccionado(Producto producto) {
//        Toast.makeText(this, "producto.name: " + producto.getNombre(),Toast.LENGTH_SHORT).show();
        if (esPanelDoble){
            DetalleProductoFragment fragment = DetalleProductoFragment.newInstance(producto.getId());

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.contenedor_detalle_producto, fragment);
            // Para tener una pila de fragments y poder hacer (atras)
            // transaction.addToBackStack(null);
            transaction.commit();
        } else {
            Intent i = new Intent(this, DetalleProductoActivity.class);
            i.putExtra("PRODUCTO_ID", producto.getId());
            startActivity(i);
        }
    }

    @Override
    public void OnProductoComprado() {
        Toast.makeText(this,"Producto comprado",Toast.LENGTH_SHORT).show();
    }
}
