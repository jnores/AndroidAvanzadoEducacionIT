package com.example.yoshknight.androidavanzado_educacionit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static RetrofitSingleton sInstance;

    private ApiService mService;

    public static RetrofitSingleton getInstance() {
        if (sInstance == null)
            sInstance = new RetrofitSingleton();
        return sInstance;
    }

    private RetrofitSingleton() {
        Retrofit retrofit = new Retrofit.Builder()
                // Direccion base de la API rest
                .baseUrl(BASE_URL)
                // Asocia un Factory que conviertra la respuesta en objetos
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Se implemento una interfaz de los servicios ofrecidos por la api
        // y se implementa la interfaz con la instancia retrofit.
        mService = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return mService;
    }
}
