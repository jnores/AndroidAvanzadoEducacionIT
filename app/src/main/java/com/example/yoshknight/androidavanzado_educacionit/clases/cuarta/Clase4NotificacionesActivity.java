package com.example.yoshknight.androidavanzado_educacionit.clases.cuarta;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yoshknight.androidavanzado_educacionit.R;

/**
 * A partir de android 8 se tiene el concepro de Notifications Channel
 * para poder deshabilitar tipos puntueles de notificaciones.
 */
public class Clase4NotificacionesActivity extends AppCompatActivity {

    private static final String NOTIF_CHANNEL_ID = "channelId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clase4_notificaciones);

        Button btn = findViewById(R.id.btn_mostrar_notificacion);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingIntent intent = PendingIntent.getActivity( getInstance(),0,
                        new Intent(getInstance(),Clase4WifiActivity.class),
                        PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder( getInstance(), NOTIF_CHANNEL_ID)
                        .setContentTitle("Titulo")
                        .setContentText("Descripcion de la notificacion")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(intent);

                NotificationManager manager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel =
                            new NotificationChannel(NOTIF_CHANNEL_ID, "nombre canal",
                            NotificationManager.IMPORTANCE_HIGH);
                    notificationChannel.setDescription("Descripcion canal");

                    manager.createNotificationChannel(notificationChannel);
                }
                manager.notify(0, builder.build());
            }
        });

    }

    private Clase4NotificacionesActivity getInstance(){
        return this;
    }
}
