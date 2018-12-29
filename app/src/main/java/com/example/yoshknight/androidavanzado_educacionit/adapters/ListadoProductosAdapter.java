package com.example.yoshknight.androidavanzado_educacionit.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yoshknight.androidavanzado_educacionit.R;
import com.example.yoshknight.androidavanzado_educacionit.modelos.Producto;

import java.util.List;

/**
 * Created by EducaciónIT on 15/12/2018.
 */

public class ListadoProductosAdapter extends BaseAdapter {

    private static final String TAG = "prodAdapter";
    private final List<Producto> productoList;

    public ListadoProductosAdapter(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int getCount() {
        return productoList.size();
    }

    @Override
    public Object getItem(int i) {
        return productoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return productoList.get(i).getId();
    }

    @Override
    public View getView(int i, View contentView, ViewGroup parent) {
        Log.i(TAG, "getView: " + i);
        View view;
        if (contentView == null)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_lista_producto,null);
        else
            view = contentView;

        TextView name = view.findViewById(R.id.tv_nombre_producto);
        name.setText(productoList.get(i).getNombre());

        return view;
    }
}
