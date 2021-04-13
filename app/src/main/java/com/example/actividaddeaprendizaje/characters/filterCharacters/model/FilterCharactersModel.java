package com.example.actividaddeaprendizaje.characters.filterCharacters.model;

import android.os.AsyncTask;

import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.beans.ResponseAPI;
import com.example.actividaddeaprendizaje.characters.filterCharacters.contract.FilterCharactersContract;
import com.example.actividaddeaprendizaje.service.CharactersApiAdapter;
import com.example.actividaddeaprendizaje.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterCharactersModel implements FilterCharactersContract.Model, Callback<ResponseAPI> {
    OnCharacterListener onCharacterListener;

    @Override
    public void getCharacterWS(final OnCharacterListener onCharacterListener, String fecha) {
        this.onCharacterListener = onCharacterListener;

        Call<ResponseAPI> responseAPICall = CharactersApiAdapter.getApiService().getCharactersFilter(fecha+"-01-01");
        responseAPICall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
        if (response.isSuccessful()){
            onCharacterListener.resolve(response.body().getData().getResults());
        }
    }

    @Override
    public void onFailure(Call<ResponseAPI> call, Throwable t) {
        onCharacterListener.reject("Fallo al traer los datos");
    }
}
