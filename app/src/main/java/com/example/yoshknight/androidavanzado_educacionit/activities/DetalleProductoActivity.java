package com.example.yoshknight.androidavanzado_educacionit.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.yoshknight.androidavanzado_educacionit.fragments.DetalleProductoFragment;
import com.example.yoshknight.androidavanzado_educacionit.fragments.ProductoCompradoFragment;
import com.example.yoshknight.androidavanzado_educacionit.R;

/**
 * Mostrar mensaje de compra exitosa:
 * - Agregar el boton comprar:
 * - Agregar un fragment que diga: "Tu compra ha sido exitosa"
 * - Agregar addToBackStack: para que cuando aprete (atras) vuelva a mostrar el detalle del producto.
 *   (Esto se debe poner en los fragments que se pueden cerrar.)
 */
public class DetalleProductoActivity extends AppCompatActivity implements DetalleProductoFragment.ProductoComprado {
    private int productoId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        setupToolbar();

        productoId = getIntent().getIntExtra("PRODUCTO_ID",-1);

        DetalleProductoFragment fragment = DetalleProductoFragment.newInstance(productoId);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.contenedor, fragment);
        // Para tener una pila de fragments y poder hacer (atras)
        // transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setupToolbar(){
        // Agregar toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Detalle de producto");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ( item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void OnProductoComprado() {
        ProductoCompradoFragment fragment = ProductoCompradoFragment.newInstance();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.contenedor, fragment);
        // Para tener una pila de fragments y poder hacer (atras)
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
