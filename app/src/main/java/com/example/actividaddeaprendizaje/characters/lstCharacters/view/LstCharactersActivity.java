package com.example.actividaddeaprendizaje.characters.lstCharacters.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.actividaddeaprendizaje.R;
import com.example.actividaddeaprendizaje.characters.filterCharacters.view.FilterCharacterFragment;
import com.example.actividaddeaprendizaje.characters.lstCharacters.fragment.LstFragment;
import com.example.actividaddeaprendizaje.characters.searchCharacters.view.SearchCharacterFragment;

public class LstCharactersActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editTextSearch;
    private Button buttonSearch;

    private String[] listaSpinner = {" ","2016","2017","2018","2019","2020","2021"};

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_characters);

        initComponents();
        cargarCombo();
        setListener();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LstFragment fragment = LstFragment.newInstance();
        fragmentTransaction.add(R.id.activity_lst_characters_linearLayout_center,fragment);
        fragmentTransaction.commit();
    }

    private void initComponents(){
        spinner = findViewById(R.id.activity_lst_characters_spinner);
        editTextSearch = findViewById(R.id.activity_lst_characters_textinput_search);
        buttonSearch = findViewById(R.id.activity_lst_characters_button_search);
    }

    private void cargarCombo(){
        ArrayAdapter arrayAdapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_item,listaSpinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelected(false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String fecha = parent.getItemAtPosition(position).toString();
                if (fecha == " ") return;

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FilterCharacterFragment fragment = FilterCharacterFragment.newInstance(fecha);
                fragmentTransaction.replace(R.id.activity_lst_characters_linearLayout_center,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setListener(){
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextSearch.getText().toString();
                if (nombre.isEmpty()) return;

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SearchCharacterFragment fragment = SearchCharacterFragment.newInstance(nombre);
                fragmentTransaction.replace(R.id.activity_lst_characters_linearLayout_center,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                editTextSearch.setText("");
            }
        });
    }
}