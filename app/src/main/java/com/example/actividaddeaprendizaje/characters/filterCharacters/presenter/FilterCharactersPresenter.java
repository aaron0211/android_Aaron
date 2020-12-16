package com.example.actividaddeaprendizaje.characters.filterCharacters.presenter;

import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.characters.filterCharacters.contract.FilterCharactersContract;
import com.example.actividaddeaprendizaje.characters.filterCharacters.model.FilterCharactersModel;

import java.util.ArrayList;

public class FilterCharactersPresenter implements FilterCharactersContract.Presenter {

    private FilterCharactersModel filterCharactersModel;
    private FilterCharactersContract.View view;

    public FilterCharactersPresenter(FilterCharactersContract.View view){
        this.view = view;
        filterCharactersModel = new FilterCharactersModel();
    }

    @Override
    public void getCharacter(String fecha) {
        filterCharactersModel.getCharacterWS(new FilterCharactersContract.Model.OnCharacterListener() {
            @Override
            public void resolve(ArrayList<Character> character) {
                view.success(character);
            }

            @Override
            public void reject(String error) {
                view.error("Fallo al traer los datos. ");
            }
        },fecha);
    }
}
