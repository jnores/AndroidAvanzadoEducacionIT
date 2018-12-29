package com.example.yoshknight.androidavanzado_educacionit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yoshknight.androidavanzado_educacionit.factories.ProductosFactory;
import com.example.yoshknight.androidavanzado_educacionit.R;
import com.example.yoshknight.androidavanzado_educacionit.modelos.Producto;

/**
 * Created by EducaciónIT on 15/12/2018.
 */

public class DetalleProductoFragment extends Fragment {
    private static final String TAG = "detPprodFragmet";
    private static final String ID = "PRODUCTO_ID";

    private TextView tvNombre;
    private TextView tvPrecio;
    private TextView tvDesc;
    private Button btnComprar;

    private ProductoComprado listener;
    public interface ProductoComprado {
        void OnProductoComprado();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null && context instanceof ProductoComprado)
            listener = (ProductoComprado)context;
        else
            throw new ClassCastException("ProductosComprado no fue implementado");
    }

    public static DetalleProductoFragment newInstance(int id){
        DetalleProductoFragment fragment = new DetalleProductoFragment();

        Bundle args = new Bundle();
        args.putInt(ID,id);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View rootView = inflater.inflate(R.layout.fragment_detalle_producto,container, false);
        tvNombre = rootView.findViewById(R.id.nombre);
        tvPrecio = rootView.findViewById(R.id.precio);
        tvDesc = rootView.findViewById(R.id.descripcion);
        btnComprar = rootView.findViewById(R.id.comprar);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnProductoComprado();
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int productoId = getArguments().getInt(ID,-1);

        Log.i(TAG, "onActivityCreated: ID " + productoId);
        Producto producto = ProductosFactory.getItem(productoId);
        Log.i(TAG, "onActivityCreated: ID " + producto.getId());
        Log.i(TAG, "onActivityCreated: ID " + producto.getNombre());
        Log.i(TAG, "onActivityCreated: ID " + producto.getPrecio());


        tvNombre.setText(producto.getNombre());
        tvPrecio.setText(""+producto.getPrecio());
        tvDesc.setText(producto.getDescripcion());

    }
}

