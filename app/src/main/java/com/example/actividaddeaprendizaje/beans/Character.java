package com.example.actividaddeaprendizaje.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Character {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String  THUMBNAIL = "thumbnail";

    private int id;
    private String nombre;
    private String descripcion;
    private String image;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public static ArrayList<Character> getArrayListFromJSON(JSONArray lstCharacters){
        ArrayList<Character> lista = null;
        try {
            if (lstCharacters!=null && lstCharacters.length()>0){
                lista = new ArrayList<Character>();
            }
            for (int i=0;i<lstCharacters.length();i++){
                JSONObject json_data = lstCharacters.getJSONObject(i);
                Character character = new Character();

                character.setId(json_data.getInt(ID));
                character.setNombre(json_data.getString(NAME));
                character.setDescripcion(json_data.getString(DESCRIPTION));
                character.setImage(json_data.getString(THUMBNAIL));

                lista.add(character);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return lista;
    }
}
