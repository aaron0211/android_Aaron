package com.example.actividaddeaprendizaje.characters.searchCharacters.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.actividaddeaprendizaje.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotFoundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotFoundFragment extends Fragment {

    private static final String ARG_EXTRA_NOT_FOUND = "ARG_EXTRA_NOT_FOUND";

    private String mNotFound;

    private TextView tvNotFound;

    public NotFoundFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param notFound Parameter 1.
     * @return A new instance of fragment NotFoundFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotFoundFragment newInstance(String notFound) {
        NotFoundFragment fragment = new NotFoundFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EXTRA_NOT_FOUND, notFound);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNotFound = getArguments().getString(ARG_EXTRA_NOT_FOUND);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_not_found, container, false);

        tvNotFound = v.findViewById(R.id.fragment_not_found_textView);
        tvNotFound.setText(mNotFound);

        return v;
    }
}