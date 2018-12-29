package com.example.yoshknight.androidavanzado_educacionit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yoshknight.androidavanzado_educacionit.services.Temporizador;

/**
 * Clase de servicios
 *
 * 1. Crear la clase que extiende de Service.
 * 2. Implementar los callbacks
 * 3. Declararlo en el manifest
 * 4. llamar a startService(intent) y stopService(intent).
 *
 * fragments
 * Rest
 * list
 * asyncTask
 *
 */
public class Clase3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clase3);
        // Creamos 2 botones. uno para iniciar el servicio y otro para detenerlo

        Button btnIniciar  = findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clase3Activity.this,Temporizador.class);
                startService(intent);
            }
        });

        Button btnDetener = findViewById(R.id.btnDetener);
        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clase3Activity.this, Temporizador.class);
                stopService(intent);
            }
        });
    }
}
