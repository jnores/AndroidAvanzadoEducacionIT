package com.example.yoshknight.androidavanzado_educacionit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.yoshknight.androidavanzado_educacionit.fragments.ListadoRootUsuariosFragment;
import com.example.yoshknight.androidavanzado_educacionit.modelos.Usuario;
import com.example.yoshknight.androidavanzado_educacionit.room.UsuariosDatabase;
import com.example.yoshknight.androidavanzado_educacionit.services.UsuariosService;

import java.util.ArrayList;
import java.util.List;

/**
 * proyecto integrador que junta fragments,service, asyncTast y retrofit.
 *
 * listado de usuarios con fragments
 * un servicio con retrofit para cargar los usuarios en la DB local
 * DB local con Room
 * un asyncTask para actualizar cargar los usuarios de la DB en el listado.
 *
 *
 *
 * Ver BroadcastReceiver para actualizar la lista de usuarios sin el boton.
 * generar un evento desde mi servicio y escucharlo con el BroadcastReceiver.
 */
public class IntegrityActivity extends AppCompatActivity implements ListadoRootUsuariosFragment.AdapterSource {

    private static final String TAG = "IntegrityActivity";
    private List<Usuario> listUsuarios;
    UsuariosAdapter adapter;
    //private ListView lstUsuarios;
    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrity);
        listUsuarios = new ArrayList<>();
        adapter = new UsuariosAdapter(listUsuarios);

        //lstUsuarios = findViewById(R.id.lvUsuarios);
        btnLoad = findViewById(R.id.btnCargarDatos);
        btnLoad.setVisibility(View.GONE);

//        btnLoad.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        new LoadListaUsuarios().execute();
    }

    @Override
    public ListAdapter getListAdapter() {
        return adapter;
    }

    private class LoadListaUsuarios extends AsyncTask<Void,Void,List<Usuario>>{

        @Override
        protected List<Usuario> doInBackground(Void... voids) {
            return UsuariosDatabase.getDatabaseReference(getApplicationContext()).getDao().obtenerUsuarios();
        }

        @Override
        protected void onPostExecute(List<Usuario> usuarios) {
            super.onPostExecute(usuarios);
            listUsuarios.clear();
            listUsuarios.addAll( usuarios );
            loadList();
        }
    }

    private void loadList() {
        if ( ! listUsuarios.isEmpty() )
        {
            //lstUsuarios.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            btnLoad.setVisibility(View.GONE);
            //lstUsuarios.setVisibility(View.VISIBLE);
        }
        else
        {
            iniciarServicio();
        }

    }

    private void iniciarServicio() {
        Intent intent = new Intent(this, UsuariosService.class);
        startService(intent);

        BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(TAG, "onReceiveBroadcast: ");
                new LoadListaUsuarios().execute();
            }
        };

        registerReceiver(br,new IntentFilter(UsuariosService.ACTION_USUARIOS_CARGADOS));
    }

}
