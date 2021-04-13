package com.example.actividaddeaprendizaje.service;

import com.example.actividaddeaprendizaje.beans.ResponseAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CharactersApiService {
    @GET("characters?ts=1&apikey=570ab40c6c10c47c081acdd50dd09db4&hash=1745346a3030e01cda6393ae8e0b0db5")
    Call<ResponseAPI> getCharacters();

    @GET("characters?ts=1&apikey=570ab40c6c10c47c081acdd50dd09db4&hash=1745346a3030e01cda6393ae8e0b0db5")
    Call<ResponseAPI> getCharactersByName(@Query("nameStartsWith") String nameStartsWith);

    @GET("characters?ts=1&apikey=570ab40c6c10c47c081acdd50dd09db4&hash=1745346a3030e01cda6393ae8e0b0db5")
    Call<ResponseAPI> getCharactersFilter(@Query("modifiedSince") String modifiedSince);
}
