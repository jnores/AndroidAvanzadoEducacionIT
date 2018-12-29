package com.example.yoshknight.androidavanzado_educacionit.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class TemporizadorIntentService extends IntentService {
    public TemporizadorIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        long fin = System.currentTimeMillis() + (1000*5);
        while ( System.currentTimeMillis() < fin){
            try {
                Thread.sleep(fin - System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
