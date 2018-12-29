package com.example.yoshknight.androidavanzado_educacionit;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yoshknight.androidavanzado_educacionit.modelos.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class PostActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, PostClickListener {
    public static final String ARG_USER_ID = "USER_ID";

    private ProgressBar progressBar;
    private ListView lstPosts;

    private ApiService service;

    private List<Post> posts;

    private int userId;
    private View viewPosts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        userId = getIntent().getIntExtra(ARG_USER_ID,0);

        posts = new ArrayList<Post>();

        progressBar = findViewById(R.id.progress);

        //setUpList();
        setUpRecycler();

        previoEjecucion();
        service = RetrofitSingleton.getInstance().getService();
        service.getPostsList(userId).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    posts.clear();
                    posts.addAll(response.body());
                    makeText(PostActivity.this,"cantidad de post del usuario: "+posts.size(),LENGTH_LONG).show();
                    posteriorEjecucion();
                } else if (response.code()>= 400 && response.code() < 500) {
                    // El error es del cliente
                } else if (response.code()>= 500) {
                    // El error es del Server
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // Errores de conexion

            }
        });
    }


    public void previoEjecucion() {
        progressBar.setVisibility(View.VISIBLE);
        viewPosts.setVisibility(View.GONE);
    }

    public void posteriorEjecucion() {
        progressBar.setVisibility(View.GONE);
        viewPosts.setVisibility(View.VISIBLE);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        service.getUserPost(posts.get(position).getId()).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful())
                    Toast.makeText(PostActivity.this, response.body().getTitle(),Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(PostActivity.this, "Error consultando el POST",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(PostActivity.this, "Error de conexion",Toast.LENGTH_LONG).show();
            }
        });
    }

    void setUpList(){
        PostsAdapter postsAdapter = new PostsAdapter(posts);
        lstPosts = findViewById(R.id.lstPosts);
        lstPosts.setAdapter(postsAdapter);
        lstPosts.setOnItemClickListener(this);
        viewPosts = lstPosts;
    }

    void setUpRecycler() {
        RecyclerView recyclerView = findViewById(R.id.lstPosts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PostsRecyclerAdapter adapter = new PostsRecyclerAdapter(posts, this);
        recyclerView.setAdapter( adapter );
        viewPosts = recyclerView;
    }

    @Override
    public void onItemClicked(String title) {
        Toast.makeText(PostActivity.this, "Click en el item del Recycler: " + title,Toast.LENGTH_LONG).show();
    }
}
