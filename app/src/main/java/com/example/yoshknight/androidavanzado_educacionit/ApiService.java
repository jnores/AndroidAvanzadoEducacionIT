package com.example.yoshknight.androidavanzado_educacionit;

import com.example.yoshknight.androidavanzado_educacionit.modelos.Post;
import com.example.yoshknight.androidavanzado_educacionit.modelos.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("users")
    Call<List<Usuario>> getUserList();

    @GET("posts")
    Call<List<Post>> getPostsList( @Query("userId") int userId);

    @GET("posts/{id}")
    Call<Post> getUserPost(@Path("id") int postId);

    @POST("users")
    Call<Usuario> crearUsuario(@Body Usuario user);

}
