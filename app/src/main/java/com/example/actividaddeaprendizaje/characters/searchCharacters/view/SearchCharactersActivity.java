package com.example.actividaddeaprendizaje.characters.searchCharacters.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.actividaddeaprendizaje.R;
import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.characters.adapter.CharacterAdapter;
import com.example.actividaddeaprendizaje.characters.filterCharacters.contract.FilterCharactersContract;
import com.example.actividaddeaprendizaje.characters.filterCharacters.presenter.FilterCharactersPresenter;
import com.example.actividaddeaprendizaje.characters.filterCharacters.view.FilterCharacterActivity;
import com.example.actividaddeaprendizaje.characters.searchCharacters.contract.SearchCharactersContract;
import com.example.actividaddeaprendizaje.characters.searchCharacters.presenter.SearchCharactersPresenter;

import java.util.ArrayList;

public class SearchCharactersActivity extends AppCompatActivity implements SearchCharactersContract.View {
    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private SearchCharactersPresenter searchCharactersPresenter;
    private Spinner comboFiltro;
    private String[] listaSpinner = {" ", "2016", "2017", "2018", "2019", "2020"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_characters);

        Intent i = this.getIntent();
        Bundle extra = i.getExtras();
        String nombre = extra.getString("nombre");

        searchCharactersPresenter = new SearchCharactersPresenter(this);
        searchCharactersPresenter.getCharacter(nombre);

        cargarCombo();
    }

    @Override
    public void success(ArrayList<Character> character) {
        recycler = findViewById(R.id.recyclerSearchCharacters);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        CharacterAdapter adapter = new CharacterAdapter(character);
        recycler.setAdapter(adapter);

    }

    @Override
    public void error(String mensaje) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    public void cargarCombo() {
        comboFiltro = findViewById(R.id.spSearchFiltro);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaSpinner);
        comboFiltro.setAdapter(arrayAdapter);
        comboFiltro.setSelected(false);
        comboFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String fecha = parent.getItemAtPosition(position).toString();
                if (fecha == " ")
                    return;
                Intent intent = new Intent(getBaseContext(), FilterCharacterActivity.class);
                intent.putExtra("fecha", fecha);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}