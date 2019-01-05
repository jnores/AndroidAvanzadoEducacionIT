package com.example.yoshknight.androidavanzado_educacionit.clases.cuarta;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yoshknight.androidavanzado_educacionit.R;

import java.util.Calendar;

/**
 * REALTIME =
 * REALTIMEWAKEUP = Si el celular esta bloqueado, lo incia igual.
 */
public class Clase4AlarmaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clase4_alarma);


        Button btnAlarmaTiempoTranscurrido = findViewById(R.id.btn_alatrma_tiempo_transcurrido);

        btnAlarmaTiempoTranscurrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tipo = AlarmManager.ELAPSED_REALTIME_WAKEUP;

                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                Clase4AlarmaActivity.this,
                                0,
                                new Intent(Clase4AlarmaActivity.this,
                                        Clase4AlarmaActivity.class),
                                PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(tipo, System.currentTimeMillis()+60000, pendingIntent);
            }
        });

        Button btnTiempoReal= findViewById(R.id.btn_alatrma_tiempo_real);

        btnAlarmaTiempoTranscurrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tipo = AlarmManager.RTC_WAKEUP;

                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                Clase4AlarmaActivity.this,
                                0,
                                new Intent(Clase4AlarmaActivity.this,
                                        Clase4AlarmaActivity.class),
                                PendingIntent.FLAG_UPDATE_CURRENT);

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MINUTE,5);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(tipo, calendar.getTimeInMillis(),pendingIntent);
            }
        });
    }
}
