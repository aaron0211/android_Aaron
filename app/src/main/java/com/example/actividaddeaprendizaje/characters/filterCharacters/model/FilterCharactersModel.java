package com.example.actividaddeaprendizaje.characters.filterCharacters.model;

import android.os.AsyncTask;

import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.characters.filterCharacters.contract.FilterCharactersContract;
import com.example.actividaddeaprendizaje.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterCharactersModel implements FilterCharactersContract.Model {
    private static final String url = "https://gateway.marvel.com/v1/public/characters?ts=1&modifiedSince=";
    String url2 = "-01-01&apikey=570ab40c6c10c47c081acdd50dd09db4&hash=1745346a3030e01cda6393ae8e0b0db5";

    private ArrayList<Character> character;
    private String fecha;
    OnCharacterListener onCharacterListener;

    @Override
    public void getCharacterWS(final OnCharacterListener onCharacterListener, String fecha) {
        this.onCharacterListener = onCharacterListener;
        this.fecha = fecha;
        DatosAPI dapi = new DatosAPI();
        dapi.execute();
    }

    class DatosAPI extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            Post post = new Post();

            HashMap<String, String> datos = new HashMap();

            datos.put("url","https://gateway.marvel.com:443/v1/public/characters?");
            datos.put("ts","ts=1&");
            datos.put("fecha","modifiedSince="+fecha+"-01-01&");
            datos.put("apikey","apikey=570ab40c6c10c47c081acdd50dd09db4&");
            datos.put("hash","hash=1745346a3030e01cda6393ae8e0b0db5");

            try {
                JSONObject objectCharacters = post.getServerDataGetObject(datos.get("url")+datos.get("ts")+datos.get("fecha")+datos.get("apikey")+datos.get("hash"));
                JSONObject lstCharacters = objectCharacters.getJSONObject("data");
                JSONArray lstCharacters2 = lstCharacters.getJSONArray("results");
                character = Character.getArrayListFromJSON(lstCharacters2);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if (resp){
                if (character!=null && character.size()>0){
                    onCharacterListener.resolve(character);
                }else {
                    onCharacterListener.reject("Error");
                }
            }
        }
    }
}
