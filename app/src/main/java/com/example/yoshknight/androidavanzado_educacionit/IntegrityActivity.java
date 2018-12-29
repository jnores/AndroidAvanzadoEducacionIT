package com.example.yoshknight.androidavanzado_educacionit;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
 */
public class IntegrityActivity extends AppCompatActivity {

    private List<Usuario> listUsuarios;
    private ListView lstUsuarios;
    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrity);
        listUsuarios = new ArrayList<>();

        lstUsuarios = findViewById(R.id.lstUsuarios);
        btnLoad = findViewById(R.id.btnCargarDatos);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadListaUsuarios().execute();
            }
        });

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
            UsuariosAdapter adapter = new UsuariosAdapter(listUsuarios);
            lstUsuarios.setAdapter(adapter);

            btnLoad.setVisibility(View.GONE);
            lstUsuarios.setVisibility(View.VISIBLE);
        }
        else
        {
            iniciarServicio();
        }

    }

    private void iniciarServicio() {
        Intent intent = new Intent(this, UsuariosService.class);
        startService(intent);

    }

}
