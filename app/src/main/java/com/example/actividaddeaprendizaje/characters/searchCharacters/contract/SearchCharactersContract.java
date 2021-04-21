package com.example.actividaddeaprendizaje.characters.searchCharacters.contract;

import com.example.actividaddeaprendizaje.beans.Character;

import java.util.ArrayList;

public interface SearchCharactersContract {
    interface View{
        void success(ArrayList<Character> characters);
        void successVoid(String notFound);
        void error(String mensaje);
    }
    interface Presenter{
        void getCharacter(String nombre);
    }
    interface Model{
        void getCharacterWS(OnCharacterListener onCharacterLister, String nombre);

        interface OnCharacterListener{
            void resolve(ArrayList<Character> characters);
            void resolveVoid(String notFound);
            void reject(String error);
        }
    }
}