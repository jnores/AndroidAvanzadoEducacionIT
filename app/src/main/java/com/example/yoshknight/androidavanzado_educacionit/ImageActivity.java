package com.example.yoshknight.androidavanzado_educacionit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    private static final String URL = "https://via.placeholder.com/600/b0f7cc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView iv = findViewById(R.id.image);

        Picasso.get()
                .load(URL)
//                .resize(50,50)
//                .centerCrop()
                .into(iv, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {

            }
        });;
    }
}
