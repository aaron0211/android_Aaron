package com.example.actividaddeaprendizaje.characters.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividaddeaprendizaje.R;
import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.FdCharactersActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private ArrayList<Character> lstCharacters;

    public static class CharacterViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public TextView nombre;

        public CharacterViewHolder(View v){
            super(v);
            img = v.findViewById(R.id.imgCharacter);
            nombre = v.findViewById(R.id.txtNombre);
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
        //Picasso.with(context).load(character.getImage()).into(holder.img);
        Picasso.get().load(character.getImage()).
                into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FdCharactersActivity.class);
                intent.putExtra("nombre", character.getNombre());
                intent.putExtra("descripcion",character.getDescripcion());
                intent.putExtra("imagen",character.getImage());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lstCharacters.size();
    }



}
