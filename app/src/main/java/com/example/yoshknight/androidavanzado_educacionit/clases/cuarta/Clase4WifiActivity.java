package com.example.yoshknight.androidavanzado_educacionit.clases.cuarta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yoshknight.androidavanzado_educacionit.R;

public class Clase4WifiActivity extends AppCompatActivity {

    IntentFilter intentFilter;
    WifiReciver receiver;
    private TextView tvWifiState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clase4_wifi);

        tvWifiState = findViewById(R.id.tvWifiState);

        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);

        receiver = new WifiReciver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    private class WifiReciver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if ( intent.getExtras() == null ){
                return;
            }

            NetworkInfo info = intent.getExtras().getParcelable("networkInfo");

            switch (info.getState()){
                case CONNECTED:
                        tvWifiState.setText("WIFI CONECTADO");
                    break;
                case DISCONNECTED:
                        tvWifiState.setText("WIFI DESCONECTADO");
                    break;
            }
        }
    }
}
