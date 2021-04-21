package com.example.actividaddeaprendizaje.characters.searchCharacters.view;

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
import com.example.actividaddeaprendizaje.characters.searchCharacters.contract.SearchCharactersContract;
import com.example.actividaddeaprendizaje.characters.searchCharacters.presenter.SearchCharactersPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchCharacterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchCharacterFragment extends Fragment implements SearchCharactersContract.View {

    private RecyclerView recycler;
    private SearchCharactersPresenter searchCharactersPresenter;
    private Button retry;
    private ProgressBar progressBar;
    private View layoutError;
    private TextView textViewError;

    private static final String ARG_EXTRA_NAME = "ARG_EXTRA_NAME";

    private String mName;

    public SearchCharacterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @return A new instance of fragment SearchCharacterFragment.
     */
    public static SearchCharacterFragment newInstance(String name) {
        SearchCharacterFragment fragment = new SearchCharacterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EXTRA_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_EXTRA_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search_character, container, false);

        initComponent(v);
        setListeners();

        progressBar.setVisibility(View.VISIBLE);

        searchCharactersPresenter = new SearchCharactersPresenter(this);
        searchCharactersPresenter.getCharacter(mName);

        return v;
    }

    private void initComponent(View view){
        recycler = view.findViewById(R.id.fragments_search_recyclerView);
        progressBar = view.findViewById(R.id.fragment_search_characters_ProgressBar);
        layoutError = view.findViewById(R.id.fragment_search_characters_linearLayout_error);
        retry = view.findViewById(R.id.fragment_search_characters_button_retry);
        textViewError = view.findViewById(R.id.fragment_search_characters_tv_error);
    }


    private void setListeners(){
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                layoutError.setVisibility(View.GONE);

                searchCharactersPresenter.getCharacter(mName);
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
    public void successVoid(String notFound) {
        progressBar.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
        hideError();

        NotFoundFragment notFoundFragment = NotFoundFragment.newInstance(notFound);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_lst_characters_layout_Relative,notFoundFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void error(String mensaje) {
        progressBar.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);

        showError(mensaje);
    }
}