package com.example.actividaddeaprendizaje.characters.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividaddeaprendizaje.R;
import com.example.actividaddeaprendizaje.beans.Character;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private ArrayList<Character> lstCharacters;

    public static class CharacterViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView nombre;
        public TextView descripcion;

        public CharacterViewHolder(View v){
            super(v);
            img = v.findViewById(R.id.imgCharacter);
            nombre = v.findViewById(R.id.txtNombre);
            descripcion = v.findViewById(R.id.txtDescripcion);
        }
    }

    public CharacterAdapter(ArrayList<Character> lstCharacters){
        this.lstCharacters = lstCharacters;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_character,parent,false);

        return new CharacterViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = lstCharacters.get(position);

        holder.nombre.setText(character.getNombre());
        holder.descripcion.setText(character.getDescripcion());

    }

    @Override
    public int getItemCount() {
        return lstCharacters.size();
    }



}
