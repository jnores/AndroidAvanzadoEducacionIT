package com.example.yoshknight.androidavanzado_educacionit;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yoshknight.androidavanzado_educacionit.services.CronometroBinded;

/**
 * VInculamos la activity al servicio bind en el inicio y la desvinculacion en el stop de la activity.
 *
 * extends Service
 * implemmentar el metodo onBind -> extender IBinder
 * bindService(intent, serviceConnection, context
 * ServiceCOnection
 * --> conexion
 * --> desconexion.
 */
public class ServiceBindedActivity extends AppCompatActivity {

    private CronometroBinded cronometroBinded;
    private boolean conectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_binded);

        Button btnIniciar  = findViewById(R.id.btnIniciarCronometro);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cronometroBinded.iniciarCronometro();
            }
        });

        Button btnDetener = findViewById(R.id.btnDetenerCronometro);
        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = cronometroBinded.obtenerTiempoTranscurrido();
                Toast.makeText(getApplicationContext(),"Tiempo transcurrido "+time+"s", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, CronometroBinded.class);
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CronometroBinded.CronometroBinder binder = (CronometroBinded.CronometroBinder) service;

            cronometroBinded = binder.getReference();
            conectado = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            conectado = false;
        }
    };

}
