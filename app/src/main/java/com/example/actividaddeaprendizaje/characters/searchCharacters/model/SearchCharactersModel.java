package com.example.actividaddeaprendizaje.characters.searchCharacters.model;

import android.os.AsyncTask;

import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.characters.searchCharacters.contract.SearchCharactersContract;
import com.example.actividaddeaprendizaje.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchCharactersModel implements SearchCharactersContract.Model {

    private ArrayList<Character> characters;
    private String nombre;
    OnCharacterListener onCharacterListener;

    @Override
    public void getCharacterWS(OnCharacterListener onCharacterLister, String nombre) {
        this.onCharacterListener = onCharacterLister;
        this.nombre = nombre;
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
            datos.put("nombre","nameStartsWith="+nombre+"&");
            datos.put("apikey","apikey=570ab40c6c10c47c081acdd50dd09db4&");
            datos.put("hash","hash=1745346a3030e01cda6393ae8e0b0db5");

            try {
                JSONObject objectCharacters = post.getServerDataGetObject(datos.get("url")+datos.get("nombre")+datos.get("ts")+datos.get("apikey")+datos.get("hash"));
                JSONObject lstCharacters = objectCharacters.getJSONObject("data");
                JSONArray lstCharacters2 = lstCharacters.getJSONArray("results");
                characters = Character.getArrayListFromJSON(lstCharacters2);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if (resp){
                if (characters!=null && characters.size()>0){
                    onCharacterListener.resolve(characters);
                }else {
                    onCharacterListener.reject("Error");
                }
            }
        }
    }
}
