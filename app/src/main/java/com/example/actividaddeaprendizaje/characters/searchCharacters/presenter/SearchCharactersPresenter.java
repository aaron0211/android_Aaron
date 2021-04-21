package com.example.actividaddeaprendizaje.characters.searchCharacters.presenter;

import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.characters.filterCharacters.contract.FilterCharactersContract;
import com.example.actividaddeaprendizaje.characters.searchCharacters.contract.SearchCharactersContract;
import com.example.actividaddeaprendizaje.characters.searchCharacters.model.SearchCharactersModel;

import java.util.ArrayList;

public class SearchCharactersPresenter implements SearchCharactersContract.Presenter {

    private SearchCharactersModel searchCharactersModel;
    private SearchCharactersContract.View view;

    public SearchCharactersPresenter(SearchCharactersContract.View view){
        this.view = view;
        searchCharactersModel = new SearchCharactersModel();
    }

    @Override
    public void getCharacter(String nombre) {
        searchCharactersModel.getCharacterWS(new SearchCharactersContract.Model.OnCharacterListener() {
            @Override
            public void resolve(ArrayList<Character> characters) {
                view.success(characters);
            }

            @Override
            public void resolveVoid(String notFound) {
                view.successVoid(notFound);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        },nombre);
    }
}
