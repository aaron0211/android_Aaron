package com.example.actividaddeaprendizaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
        nombre.setMovementMethod(new ScrollingMovementMethod());
        descripcion = findViewById(R.id.fdDescripcion);
        descripcion.setMovementMethod(new ScrollingMovementMethod());
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