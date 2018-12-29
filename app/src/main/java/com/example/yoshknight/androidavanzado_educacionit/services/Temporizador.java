package com.example.yoshknight.androidavanzado_educacionit.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class Temporizador extends Service {

    private static final String TAG = "Temporizador";

    @Override
    public void onCreate() {
        // inicia el servicio.
        // Si no esta creado, ejecuta este metodo
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    /**
     *
     * @param intent Se inicia con un intent como las activities. Puede recibir calores en el extraparams del intent.
     * @param flags
     * @param startId Id del servicio que se esta iniciando porque puede manejar varias tareas/servicios.
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Recibe todas las llamadas de inicio desde la activity.
        // Si ya esta creado, pasa a este metodo
        Log.e(TAG, "onStartCommand: Iniciando servicio con ID: " + startId);

        new TemporizadorBackground().execute(startId);

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

    private class TemporizadorBackground extends AsyncTask<Integer, Void, Integer>{

        @Override
        protected Integer doInBackground(Integer... integers) {
            int id = integers[0];
            long fin = System.currentTimeMillis() + (1000*5);
            while ( System.currentTimeMillis() < fin){
                try {
                    Thread.sleep(fin - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return id;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.e(TAG, "onPostExecute: Finalizo el servicio "+integer);

            // Para que se detenga a si mismo el servicio, se llama a stop self pasando el id de servicio.
            stopSelf(integer);
        }
    }
}
