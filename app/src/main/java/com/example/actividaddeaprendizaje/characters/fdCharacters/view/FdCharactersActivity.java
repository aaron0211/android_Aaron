package com.example.actividaddeaprendizaje.characters.fdCharacters.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.actividaddeaprendizaje.R;
import com.squareup.picasso.Picasso;

public class FdCharactersActivity extends AppCompatActivity {
    private TextView nombre;
    private TextView descripcion;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd_characters);

        nombre = findViewById(R.id.fdnombre);
        descripcion = findViewById(R.id.fdDescripcion);
        imagen = findViewById(R.id.fdimagen);

        Intent i = getIntent();
        String name = i.getStringExtra("nombre");
        String description = i.getStringExtra("descripcion");
        String image = i.getStringExtra("imagen");
        nombre.setText(name);
        descripcion.setText(description);
        Picasso.get().load(image).
                into(imagen);
    }
}