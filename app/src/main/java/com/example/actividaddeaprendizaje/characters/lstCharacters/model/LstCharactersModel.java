package com.example.actividaddeaprendizaje.characters.lstCharacters.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.beans.DataAPI;
import com.example.actividaddeaprendizaje.beans.ResponseAPI;
import com.example.actividaddeaprendizaje.characters.lstCharacters.contract.LstCharactersContract;
import com.example.actividaddeaprendizaje.service.CharactersApiAdapter;
import com.example.actividaddeaprendizaje.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstCharactersModel implements LstCharactersContract.Model, Callback<ResponseAPI> {

    OnLstCharactersListener onLstCharactersListener;

    @Override
    public void getCharactersWS(final OnLstCharactersListener onLstCharactersListener) {
        this.onLstCharactersListener = onLstCharactersListener;

        Call<ResponseAPI> responseAPICall = CharactersApiAdapter.getApiService().getCharacters();
        responseAPICall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
        if (response.isSuccessful()){
            onLstCharactersListener.resolve(response.body().getData().getResults());
        }
    }

    @Override
    public void onFailure(Call<ResponseAPI> call, Throwable t) {
        onLstCharactersListener.reject("Fallo con el servidor");
    }
}