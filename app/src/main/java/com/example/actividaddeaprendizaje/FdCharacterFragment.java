package com.example.actividaddeaprendizaje;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FdCharacterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FdCharacterFragment extends Fragment {

    private TextView nombre;
    private TextView descripcion;
    private ImageView imagen;

    public static String ARG_EXTRA_NAME = "ARG_EXTRA_NAME";
    public static String ARG_EXTRA_IMG = "ARG_EXTRA_IMG";
    public static String ARG_EXTRA_DESCRICPTION = "ARG_EXTRA_DESCRIPTION";

    // TODO: Rename and change types of parameters
    private String mName;
    private String mImg;
    private String mDescription;

    public FdCharacterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @param img Parameter 2.
     * @param description Parameter 3.
     * @return A new instance of fragment FdCharacterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FdCharacterFragment newInstance(String name, String img, String description) {
        FdCharacterFragment fragment = new FdCharacterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EXTRA_NAME, name);
        args.putString(ARG_EXTRA_IMG, img);
        args.putString(ARG_EXTRA_DESCRICPTION, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_EXTRA_NAME);
            mImg = getArguments().getString(ARG_EXTRA_IMG);
            mDescription = getArguments().getString(ARG_EXTRA_DESCRICPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fd_character, container, false);
        nombre = view.findViewById(R.id.fragment_fdcharacter_tv_nombre);
        descripcion = view.findViewById(R.id.fragment_fdcharacter_tv_descripcion);
        imagen = view.findViewById(R.id.fragment_fdcharacter_imagen);

        nombre.setText(mName);
        descripcion.setText(mDescription);
        Picasso.get().load(mImg).into(imagen);

        return view;
    }
}