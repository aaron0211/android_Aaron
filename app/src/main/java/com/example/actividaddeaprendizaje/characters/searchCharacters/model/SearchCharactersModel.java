package com.example.actividaddeaprendizaje.characters.searchCharacters.model;

import com.example.actividaddeaprendizaje.beans.ResponseAPI;
import com.example.actividaddeaprendizaje.characters.searchCharacters.contract.SearchCharactersContract;
import com.example.actividaddeaprendizaje.service.CharactersApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCharactersModel implements SearchCharactersContract.Model, Callback<ResponseAPI> {

    OnCharacterListener onCharacterListener;

    @Override
    public void getCharacterWS(OnCharacterListener onCharacterLister, String nombre) {
        this.onCharacterListener = onCharacterLister;

        Call<ResponseAPI> responseAPICall = CharactersApiAdapter.getApiService().getCharactersByName(nombre);
        responseAPICall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
        if (response.isSuccessful() && !response.body().getData().getResults().isEmpty()){
            onCharacterListener.resolve(response.body().getData().getResults());
        }else {
            onCharacterListener.resolveVoid("Character Not Found");
        }
    }

    @Override
    public void onFailure(Call<ResponseAPI> call, Throwable t) {
        onCharacterListener.reject("Fallo al traer los datos");
    }
}
