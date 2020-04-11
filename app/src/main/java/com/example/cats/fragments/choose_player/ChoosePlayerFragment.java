package com.example.cats.fragments.choose_player;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cats.MainActivity;
import com.example.cats.R;
import com.example.cats.models.AppDatabase;
import com.example.cats.models.entities.User;
import com.example.cats.util.Methods;
import com.example.cats.viewmodels.AppViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChoosePlayerFragment extends Fragment {

    View mView;
    AppDatabase mDb;
    AppViewModel mAppViewModel;

    public ChoosePlayerFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_choose_player, container, false);

//        final TextView tv = view.findViewById(R.id.choose);

        Spinner spinner = mView.findViewById(R.id.spinner);
        Methods.avoidSpinnerDropdownFocus(spinner);

        Button choosePlayerButton = mView.findViewById(R.id.choosePlayerButton);

        choosePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(mView).navigate(R.id.action_choosePlayerFragment_to_garageFragment);
            }
        });

        mDb = ((MainActivity)getActivity()).getDatabase();

        mAppViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        (new GetPlayersTask(getContext(), mAppViewModel, mDb, spinner)).execute();

//
//        (new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final List<User> users = db.userDao().getAll();
//                Handler handler = new Handler(Looper.getMainLooper());
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        StringBuilder sb = new StringBuilder();
//                        for(User user: users){
//                            sb.append(user.username + " \n");
//                        }
//                        tv.setText(sb.toString());
//
//                    }
//                });
//            }
//        })).start();

        return mView;
    }

}
