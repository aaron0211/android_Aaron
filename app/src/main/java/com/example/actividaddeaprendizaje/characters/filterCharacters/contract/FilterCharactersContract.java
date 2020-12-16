package com.example.actividaddeaprendizaje.characters.filterCharacters.contract;

import com.example.actividaddeaprendizaje.beans.Character;

import java.util.ArrayList;

public interface FilterCharactersContract {
    interface View{
        void success(ArrayList<Character> character);
        void error(String mensaje);
    }
    interface Presenter{
        void getCharacter(String fecha);
    }
    interface Model{
        void getCharacterWS(OnCharacterListener onCharacterListener, String fecha);

        interface OnCharacterListener{
            void resolve(ArrayList<Character> character);
            void reject(String error);
        }
    }
}