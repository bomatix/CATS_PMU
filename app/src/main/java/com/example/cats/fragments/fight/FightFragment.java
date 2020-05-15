package com.example.cats.fragments.fight;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.unity3d.player.UnityPlayerActivity;

import com.example.cats.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FightFragment extends Fragment {

    FrameLayout frameLayoutForUnity;

    public FightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Intent intent = new Intent(getContext(), UnityPlayerActivity.class);
        startActivity(intent);
        return null;
    }
}
