package com.example.yoshknight.androidavanzado_educacionit.clases.cuarta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yoshknight.androidavanzado_educacionit.R;
import com.example.yoshknight.androidavanzado_educacionit.services.TemporizadorService;

/**
 * 1. Defino el ID del EVENTO que escucha
 * 2. Creo un intentFilter que filtre este ID de EVENTO
 * 3. Extiendo la clase BroadcastReceiver Para no tener clases anonimas dando vueltas.
 * 4. Estiando onStart y onStop para registrar  y desregistrar el receiver.
 *
 */
public class Clase4BRActivity extends AppCompatActivity {

    public static final String ACCION = Clase4BRActivity.class.getPackage().getName()+"Temporizador";

    /**
     * Filtra las acciones que se estan recibiendo
     */
    IntentFilter intentFilter;
    /**
     * Escuchar eventos. Procesa los eventos que deja pasar el intentFilter.
     */
    TemporizadorReceiver receiver;

    Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clase4_br);

        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setEnabled(false);
                Intent intent = new Intent(Clase4BRActivity.this, TemporizadorService.class);
                startService(intent);
                Toast.makeText(Clase4BRActivity.this, "Temporizador Iniciado",Toast.LENGTH_LONG).show();
            }
        });

        receiver = new TemporizadorReceiver();

        intentFilter = new IntentFilter();
        intentFilter.addAction(ACCION);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private class TemporizadorReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            btnStart.setEnabled (true);
            Toast.makeText(Clase4BRActivity.this, "Temporizador Terminado",Toast.LENGTH_LONG).show();
        }
    }
}
