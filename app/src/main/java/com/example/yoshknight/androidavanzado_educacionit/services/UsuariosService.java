package com.example.yoshknight.androidavanzado_educacionit.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.yoshknight.androidavanzado_educacionit.RetrofitSingleton;
import com.example.yoshknight.androidavanzado_educacionit.modelos.Usuario;
import com.example.yoshknight.androidavanzado_educacionit.room.UsuarioDao;
import com.example.yoshknight.androidavanzado_educacionit.room.UsuariosDatabase;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuariosService extends Service {

    private boolean isFreeMutex;

    @Override
    public void onCreate() {
        super.onCreate();
        isFreeMutex = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        if (isFreeMutex)
        {
            isFreeMutex=false;
            new RequestUsuarios().execute(startId);

        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class RequestUsuarios extends AsyncTask<Integer,Void,Void>{

        @Override
        protected Void doInBackground(Integer... integers) {

            int serviceId=integers[0];


            Response<List<Usuario>> response = null;
            try {
                response = RetrofitSingleton.getInstance().getService().getUserList().execute();

                if (response.isSuccessful()) {
                    UsuarioDao dao = UsuariosDatabase
                            .getDatabaseReference(getApplicationContext())
                            .getDao();

                    for (Usuario usuario : response.body())
                        dao.crearUsuarios(usuario);
                }
            }catch (IOException e){
                e.printStackTrace();
            }

            stopSelf(serviceId);

            isFreeMutex=true;
            return null;
        }
    }

}
