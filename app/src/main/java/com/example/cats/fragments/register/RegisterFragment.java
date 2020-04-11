package com.example.cats.fragments.register;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cats.MainActivity;
import com.example.cats.R;
import com.example.cats.models.AppDatabase;
import com.example.cats.models.entities.User;
import com.example.cats.viewmodels.AppViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    AppDatabase mDb;
    View mView;
    AppViewModel mAppViewModel;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_register, container, false);

        mDb = ((MainActivity)getActivity()).getDatabase();

        final EditText username = mView.findViewById(R.id.username);
        Button registerButton = mView.findViewById(R.id.registerButton);

        mAppViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User();
                user.username = username.getText().toString();
                (new RegisterTask(mAppViewModel, mDb, mView, user)).execute();
            }
        });

        return mView;
    }

}
