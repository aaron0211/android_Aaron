package com.example.actividaddeaprendizaje.characters.lstCharacters.presenter;

import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.characters.lstCharacters.contract.LstCharactersContract;
import com.example.actividaddeaprendizaje.characters.lstCharacters.model.LstCharactersModel;

import java.util.ArrayList;

public class LstCharactersPresenter implements LstCharactersContract.Presenter {

    private LstCharactersModel lstCharactersModel;
    private LstCharactersContract.View vista;

    public LstCharactersPresenter(LstCharactersContract.View vista){
        this.vista = vista;
        this.lstCharactersModel = new LstCharactersModel();
    }

    @Override
    public void getCharacters() {
        lstCharactersModel.getCharactersWS(new LstCharactersContract.Model.OnLstCharactersListener() {
            @Override
            public void resolve(ArrayList<Character> characters) {
                vista.success(characters);
            }

            @Override
            public void reject(String error) {
                vista.error("Problemas al traer los datos.");
            }
        });

    }
}
