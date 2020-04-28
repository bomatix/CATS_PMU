package com.example.cats.fragments.garage;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cats.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GarageFragment extends Fragment {

    private View mView;
    private MusicThread musicThread;
    private BoxTimerThread boxTimerThread;

    public GarageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_garage, container, false);

        musicThread = new MusicThread(mView.getContext());
        musicThread.start();

        boxTimerThread = new BoxTimerThread((TextView) mView.findViewById(R.id.time));
        boxTimerThread.start();

        ImageButton vehicle = (ImageButton) mView.findViewById(R.id.vehicle);
        vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(mView).navigate(R.id.action_garageFragment_to_vehicleModificationFragment);
            }
        });

        return mView;
    }

    @Override
    public void onPause() {
        super.onPause();

        musicThread.stopThread();
    }
}
