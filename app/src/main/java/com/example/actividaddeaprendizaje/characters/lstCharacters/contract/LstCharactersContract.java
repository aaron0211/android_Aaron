package com.example.actividaddeaprendizaje.characters.lstCharacters.contract;

import com.example.actividaddeaprendizaje.beans.Character;

import java.util.ArrayList;

public interface LstCharactersContract {
    interface View{
        void success(ArrayList<Character> characters);
        void error(String message);
    }
    interface Presenter{
        void getCharacters();
    }
    interface Model{
        /*Me tienes que mandar el Callback*/
        void getCharactersWS(OnLstCharactersListener onLstCharactersListener);
        /*Programación reactiva (Callback)*/
        interface OnLstCharactersListener{
            void resolve(ArrayList<Character> characters);
            void reject(String error);
        }
    }
}
