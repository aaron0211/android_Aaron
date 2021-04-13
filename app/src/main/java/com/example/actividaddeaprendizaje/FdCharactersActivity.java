package com.example.actividaddeaprendizaje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.actividaddeaprendizaje.R;
import com.squareup.picasso.Picasso;

public class FdCharactersActivity extends AppCompatActivity {
//    private TextView nombre;
//    private TextView descripcion;
//    private ImageView imagen;

    public static String ARG_EXTRA_NAME = "ARG_EXTRA_NAME";
    public static String ARG_EXTRA_IMG = "ARG_EXTRA_IMG";
    public static String ARG_EXTRA_DESCRICPTION = "ARG_EXTRA_DESCRIPTION";

    private String name;
    private String img;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd_characters);

        getExtras();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FdCharacterFragment fragment = FdCharacterFragment.newInstance(name,img,description);
        fragmentTransaction.replace(R.id.activity_fdcharacters_layout,fragment);
        fragmentTransaction.commit();

//        nombre = findViewById(R.id.fdnombre);
//        nombre.setMovementMethod(new ScrollingMovementMethod());
//        descripcion = findViewById(R.id.fdDescripcion);
//        descripcion.setMovementMethod(new ScrollingMovementMethod());
//        imagen = findViewById(R.id.fdimagen);
//
//        Intent i = getIntent();
//        String name = i.getStringExtra("nombre");
//        String description = i.getStringExtra("descripcion");
//        String image = i.getStringExtra("imagen");
//        image = image.replace("http","https");
//        nombre.setText(name);
//        descripcion.setText(description);
//        Picasso.get().load(image).into(imagen);
    }

    private void getExtras(){
        name = getIntent().getExtras().getString(ARG_EXTRA_NAME);
        img = getIntent().getExtras().getString(ARG_EXTRA_IMG);
        description = getIntent().getExtras().getString(ARG_EXTRA_DESCRICPTION);
    }
}