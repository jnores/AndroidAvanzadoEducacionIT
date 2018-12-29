package com.example.yoshknight.androidavanzado_educacionit.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class CronometroBinded extends Service {

    private long inicio=-1;
    private IBinder iBinder;


    @Override
    public void onCreate() {
        super.onCreate();
        iBinder = new CronometroBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    public void iniciarCronometro() {
        inicio = System.currentTimeMillis();
    }

    public int obtenerTiempoTranscurrido() {
        if (inicio < 0)
            return 0;
        long tiempo = System.currentTimeMillis()-inicio;

        return (int) (tiempo/1000);
    }



    public class CronometroBinder extends Binder {
        public CronometroBinded getReference() {
            return CronometroBinded.this;
        }
    }
}
