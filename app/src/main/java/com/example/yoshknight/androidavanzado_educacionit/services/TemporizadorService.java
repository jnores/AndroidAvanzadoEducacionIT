package com.example.yoshknight.androidavanzado_educacionit.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.example.yoshknight.androidavanzado_educacionit.clases.cuarta.Clase4BRActivity;

public class TemporizadorService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new TemporizadorTask(startId).execute();

        return super.onStartCommand(intent, flags, startId);

    }

    private class TemporizadorTask extends AsyncTask<Void,Void, Integer> {

        private int serviceId;

        public TemporizadorTask(int serviceId) {
            this.serviceId = serviceId;
        }

        @Override
        protected Integer doInBackground(Void... voids) {

            long tiempoFinal = System.currentTimeMillis()+5000;

            while( System.currentTimeMillis() < tiempoFinal)

            {
                try {
                    Thread.sleep(tiempoFinal-System.currentTimeMillis() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {

            Intent intent = new Intent(Clase4BRActivity.ACCION);

            LocalBroadcastManager.getInstance(TemporizadorService.this)
                    .sendBroadcast(intent);

            stopSelf(serviceId);
        }
    }

}
