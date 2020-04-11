package com.example.cats.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cats.MainActivity;
import com.example.cats.R;
import com.example.cats.models.AppDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class StartFragment extends Fragment {

    View view;


    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_start, container, false);



        Button choosePlayerButton = view.findViewById(R.id.choosePlayerButton);

        Button newPlayerButton = view.findViewById(R.id.newPlayerButton);

        choosePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_startFragment_to_choosePlayerFragment);
            }
        });

        newPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_startFragment_to_registerFragment);
            }
        });

        return view;
    }

}
