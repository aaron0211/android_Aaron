package com.example.actividaddeaprendizaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.actividaddeaprendizaje.characters.lstCharacters.view.LstCharactersActivity;

public class MainActivity extends AppCompatActivity {
    private static final int SCREEN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent navegar = new Intent(getBaseContext(), LstCharactersActivity.class);
            startActivity(navegar);
            this.finish();
        }, 2500);
    }
}