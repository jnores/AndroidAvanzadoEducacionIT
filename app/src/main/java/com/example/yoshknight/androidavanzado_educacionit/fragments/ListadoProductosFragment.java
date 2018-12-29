package com.example.yoshknight.androidavanzado_educacionit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yoshknight.androidavanzado_educacionit.factories.ProductosFactory;
import com.example.yoshknight.androidavanzado_educacionit.R;
import com.example.yoshknight.androidavanzado_educacionit.adapters.ListadoProductosAdapter;
import com.example.yoshknight.androidavanzado_educacionit.modelos.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EducaciónIT on 15/12/2018.
 */

public class ListadoProductosFragment extends Fragment {
    private static final String TAG = "prodFragmet";
    private ListView lvProductos;

    private ListadoProductosAdapter adapter;
    private List<Producto> listaProductos;
    private ProductoSeleccionado listener;

    public interface ProductoSeleccionado{
        void onProductoSeleccionado(Producto producto);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context != null && context instanceof ProductoSeleccionado){
            listener = (ProductoSeleccionado) context;
        } else {
            throw new ClassCastException("ProductosSeleccionado no fue implementado");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listaProductos = new ArrayList<Producto>();
        listaProductos.addAll(ProductosFactory.crearProductos());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View rootView = inflater.inflate(R.layout.fragment_listado_productos,container, false);
        lvProductos = rootView.findViewById(R.id.lv_productos);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: " + listaProductos.size());
        adapter = new ListadoProductosAdapter(listaProductos);
        lvProductos.setAdapter(adapter);
        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                listener.onProductoSeleccionado( listaProductos.get(position) );
            }
        });
    }
}
