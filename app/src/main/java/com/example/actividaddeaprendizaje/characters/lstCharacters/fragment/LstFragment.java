package com.example.actividaddeaprendizaje.characters.lstCharacters.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.actividaddeaprendizaje.R;
import com.example.actividaddeaprendizaje.beans.Character;
import com.example.actividaddeaprendizaje.characters.adapter.CharacterAdapter;
import com.example.actividaddeaprendizaje.characters.lstCharacters.contract.LstCharactersContract;
import com.example.actividaddeaprendizaje.characters.lstCharacters.presenter.LstCharactersPresenter;
import com.example.actividaddeaprendizaje.characters.searchCharacters.view.NotFoundFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LstFragment extends Fragment implements LstCharactersContract.View{

    private RecyclerView recycler;
    private LstCharactersPresenter lstCharactersPresenter;
    private Button retry;
    private ProgressBar progressBar;
    private View layoutError;
    private TextView textViewError;

    public LstFragment() {
        // Required empty public constructor
    }

    public static LstFragment newInstance() {
        LstFragment fragment = new LstFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lst, container, false);

        initComponents(view);
        setListeners();

        progressBar.setVisibility(View.VISIBLE);

        lstCharactersPresenter = new LstCharactersPresenter(this);
        lstCharactersPresenter.getCharacters();

        return view;
    }

    private void initComponents(View view){
        recycler = view.findViewById(R.id.fragments_lst_recyclerView);
        progressBar = view.findViewById(R.id.fragment_lstCharacters_ProgressBar);
        layoutError = view.findViewById(R.id.fragment_lst_characters_linearLayout_error);
        retry = view.findViewById(R.id.fragment_lst_characters_button_retry);
        textViewError = view.findViewById(R.id.fragment_lst_characters_tv_error);
    }

    private void setListeners(){
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                layoutError.setVisibility(View.GONE);

                lstCharactersPresenter.getCharacters();
            }
        });
    }

    private void showError(String error){
        progressBar.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);

        textViewError.setText(error);
    }

    private void hideError(){
        layoutError.setVisibility(View.GONE);
    }

    @Override
    public void success(ArrayList<Character> characters) {
        progressBar.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
        hideError();

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        CharacterAdapter adapter = new CharacterAdapter(characters);
        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String message) {
        progressBar.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);

        showError(message);
    }
}