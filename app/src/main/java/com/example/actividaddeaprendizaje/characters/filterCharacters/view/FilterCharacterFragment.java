package com.example.actividaddeaprendizaje.characters.filterCharacters.view;

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
import com.example.actividaddeaprendizaje.characters.filterCharacters.contract.FilterCharactersContract;
import com.example.actividaddeaprendizaje.characters.filterCharacters.presenter.FilterCharactersPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterCharacterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilterCharacterFragment extends Fragment implements FilterCharactersContract.View {

    private RecyclerView recycler;
    private FilterCharactersPresenter filterCharactersPresenter;
    private Button retry;
    private ProgressBar progressBar;
    private View layoutError;
    private TextView textViewError;

    private static final String ARG_EXTRA_DATE = "ARG_EXTRA_DATE";

    private String mDate;

    public FilterCharacterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param date Parameter 1.
     * @return A new instance of fragment FilterCharacterFragment.
     */
    public static FilterCharacterFragment newInstance(String date) {
        FilterCharacterFragment fragment = new FilterCharacterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EXTRA_DATE, date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDate = getArguments().getString(ARG_EXTRA_DATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_filter_character, container, false);

        initComponent(v);
        setListeners();

        progressBar.setVisibility(View.VISIBLE);

        filterCharactersPresenter = new FilterCharactersPresenter(this);
        filterCharactersPresenter.getCharacter(mDate);

        return v;
    }

    private void initComponent(View view){
        recycler = view.findViewById(R.id.fragments_filter_recyclerView);
        progressBar = view.findViewById(R.id.fragment_filter_characters_ProgressBar);
        layoutError = view.findViewById(R.id.fragment_filter_characters_linearLayout_error);
        retry = view.findViewById(R.id.fragment_filter_characters_button_retry);
        textViewError = view.findViewById(R.id.fragment_filter_characters_tv_error);
    }


    private void setListeners(){
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                layoutError.setVisibility(View.GONE);

                filterCharactersPresenter.getCharacter(mDate);
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
    public void error(String mensaje) {
        progressBar.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);

        showError(mensaje);
    }
}