package com.example.yoshknight.androidavanzado_educacionit.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.yoshknight.androidavanzado_educacionit.R;

public class ListadoRootUsuariosFragment extends Fragment {

    private static final String TAG = "ListRootUsuarios";
    AdapterSource listener;
    private ListView lvUsuarios;

    public interface AdapterSource {
        ListAdapter getListAdapter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null && context instanceof AdapterSource)
            listener = (AdapterSource)context;
        else
            throw new ClassCastException("AdapterSource no fue implementado");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View rootView = inflater.inflate(R.layout.fragment_listado_usuarios,container, false);
        lvUsuarios = rootView.findViewById(R.id.lvUsuarios);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lvUsuarios.setAdapter(listener.getListAdapter());
    }
}
