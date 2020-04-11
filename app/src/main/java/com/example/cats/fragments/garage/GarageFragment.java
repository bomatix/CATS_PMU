package com.example.cats.fragments.garage;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cats.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GarageFragment extends Fragment {

    private View mView;
    private MusicThread musicThread;

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

        return mView;
    }

    @Override
    public void onPause() {
        super.onPause();

        musicThread.stopThread();
    }
}
