package com.example.actividaddeaprendizaje.characters.lstCharacters.model;

import android.os.AsyncTask;

import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.characters.lstCharacters.contract.LstCharactersContract;
import com.example.actividaddeaprendizaje.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LstCharactersModel implements LstCharactersContract.Model {

    private static final String URL = "https://gateway.marvel.com:443/v1/public/characters?ts=1&apikey=570ab40c6c10c47c081acdd50dd09db4&hash=1745346a3030e01cda6393ae8e0b0db5";

    private ArrayList<Character> lstArrayCharacters;
    OnLstCharactersListener onLstCharactersListener;

    @Override
    public void getCharactersWS(final OnLstCharactersListener onLstCharactersListener) {
        this.onLstCharactersListener = onLstCharactersListener;
        DatosAPI dapi = new DatosAPI();
        dapi.execute();
    }

    class DatosAPI extends AsyncTask<String, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(String... strings) {
            Post post = new Post();
            HashMap<String, String> datos = new HashMap();
            //CLAVE-VALOR
            //datos.put("ts","md5");
            //datos.put("privateKey","1481b9edf75910328b9b96c4ba7640251f3c630a");
            datos.put("apiKey","570ab40c6c10c47c081acdd50dd09db4");
            try {
                JSONObject objectCharacters = post.getServerDataGetObject(URL);
                JSONObject lstCharacters = objectCharacters.getJSONObject("data");
                JSONArray lstCharacters2 = lstCharacters.getJSONArray("results");
                lstArrayCharacters = Character.getArrayListFromJSON(lstCharacters2);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            if (resp){
                if (lstArrayCharacters!=null && lstArrayCharacters.size()>0){
                    onLstCharactersListener.resolve(lstArrayCharacters);
                }
            }else {
                onLstCharactersListener.reject("Error al traer los datos del Servidor.");
            }
        }
    }
}
