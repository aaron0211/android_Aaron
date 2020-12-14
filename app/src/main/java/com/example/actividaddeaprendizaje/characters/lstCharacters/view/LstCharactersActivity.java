package com.example.actividaddeaprendizaje.characters.lstCharacters.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.actividaddeaprendizaje.R;
import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.characters.adapter.CharacterAdapter;
import com.example.actividaddeaprendizaje.characters.lstCharacters.contract.LstCharactersContract;
import com.example.actividaddeaprendizaje.characters.lstCharacters.presenter.LstCharactersPresenter;

import java.util.ArrayList;

public class LstCharactersActivity extends AppCompatActivity  implements LstCharactersContract.View {

    private RecyclerView recycler;
    private LstCharactersPresenter lstCharactersPresenter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_characters);

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
        CharacterAdapter adapter = new CharacterAdapter(characters,this);

        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this,"",Toast.LENGTH_SHORT).show();
    }
}