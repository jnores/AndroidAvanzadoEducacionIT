package com.example.yoshknight.androidavanzado_educacionit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yoshknight.androidavanzado_educacionit.modelos.Usuario;
import com.example.yoshknight.androidavanzado_educacionit.utils.AsyncTaskExampleInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.*;

/**
 * Agregar 2 activitys. una liastar usuarios y otra para editar usuarios.
 * [ ] Activity con listView con los usuarios
 * [ ] Activity con listView de los posts de un usuario particular..
 *
 * unificar con la estrucura de fragmentes implementado la semana pasada.
 */
public class MainActivity extends AppCompatActivity implements AsyncTaskExampleInterface, AdapterView.OnItemClickListener {
    private ProgressBar progressBar;
    private ListView lstUsers;

    private ApiService service;

    private List<Usuario> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();

        progressBar = findViewById(R.id.progress);

        UsuariosAdapter usuariosAdapter = new UsuariosAdapter(users);
        lstUsers = findViewById(R.id.lstUsers);
        lstUsers.setAdapter(usuariosAdapter);
        lstUsers.setOnItemClickListener(this);

//        AsyncTaskExample task = new AsyncTaskExample(this);
//        task.execute("asdasdas");


        previoEjecucion();
        service = RetrofitSingleton.getInstance().getService();
        service.getUserList().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    users.clear();
                    users.addAll(response.body());
                    makeText(MainActivity.this,"cantidad de usuarios: "+users.size(),LENGTH_LONG).show();
                    posteriorEjecucion();
                } else if (response.code()>= 400 && response.code() < 500) {
                    // El error es del cliente
                } else if (response.code()>= 500) {
                    // El error es del Server
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                // Errores de conexion

            }
        });
    }


    @Override
    public void previoEjecucion() {
        progressBar.setVisibility(View.VISIBLE);
        lstUsers.setVisibility(View.GONE);
    }

    @Override
    public void posteriorEjecucion() {
        progressBar.setVisibility(View.GONE);
        lstUsers.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, PostActivity.class);
        i.putExtra(PostActivity.ARG_USER_ID, users.get(position).getId());
        startActivity(i);
    }

    public void verImagen(View view) {
        Intent i = new Intent(this, ImageActivity.class);
        startActivity(i);
    }
}
