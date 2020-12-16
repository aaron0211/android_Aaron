package com.example.actividaddeaprendizaje.characters.lstCharacters.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.actividaddeaprendizaje.R;
import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.characters.adapter.CharacterAdapter;
import com.example.actividaddeaprendizaje.characters.filterCharacters.view.FilterCharacterActivity;
import com.example.actividaddeaprendizaje.characters.lstCharacters.contract.LstCharactersContract;
import com.example.actividaddeaprendizaje.characters.lstCharacters.presenter.LstCharactersPresenter;
import com.example.actividaddeaprendizaje.characters.searchCharacters.view.SearchCharactersActivity;

import java.util.ArrayList;

public class LstCharactersActivity extends AppCompatActivity  implements LstCharactersContract.View {

    private RecyclerView recycler;
    private LstCharactersPresenter lstCharactersPresenter;
    private RecyclerView.LayoutManager lManager;
    private EditText buscar;
    private Button buscador;
    private Spinner comboFiltro;

    private String[] listaSpinner = {" ","2016","2017","2018","2019","2020"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_characters);

        buscador = findViewById(R.id.btBuscar);
        buscar = findViewById(R.id.etBuscador);

        cargarCombo();

        buscador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = buscar.getText().toString();
                Intent intent = new Intent(getBaseContext(), SearchCharactersActivity.class);
                intent.putExtra("nombre", nombre);
                startActivity(intent);
            }
        });

        lstCharactersPresenter = new LstCharactersPresenter(this);
        lstCharactersPresenter.getCharacters();
    }

    @Override
    public void success(ArrayList<Character> characters) {
        //Obtener el Recycler
        recycler = findViewById(R.id.recyclerCharacters);
        recycler.setHasFixedSize(true);

        //Usar un administrador para LinearLayout
        // 1º) Tipo Lista
        // 2º) Tipo Grid
        lManager = new LinearLayoutManager(this);

        recycler.setLayoutManager(lManager);
        //Crear un nuevo adaptador
        CharacterAdapter adapter = new CharacterAdapter(characters);

        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
    }

    public void cargarCombo(){
        comboFiltro = findViewById(R.id.spFiltro);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listaSpinner);
        comboFiltro.setAdapter(arrayAdapter);
        comboFiltro.setSelected(false);
        comboFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String fecha = parent.getItemAtPosition(position).toString();
                if (fecha == " ")
                    return;
                Intent intent = new Intent(getBaseContext(),FilterCharacterActivity.class);
                intent.putExtra("fecha",fecha);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}