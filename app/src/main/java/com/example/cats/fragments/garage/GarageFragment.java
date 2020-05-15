package com.example.cats.fragments.garage;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cats.MainActivity;
import com.example.cats.R;
import com.example.cats.fragments.vehicle_modification.ItemInfoView;
import com.example.cats.fragments.vehicle_modification.Vehicle;
import com.example.cats.fragments.vehicle_modification.inventory.InventoryItem;
import com.example.cats.fragments.vehicle_modification.inventory.InventoryItemAdapter;
import com.example.cats.models.AppDatabase;
import com.example.cats.viewmodels.AppViewModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 */
public class GarageFragment extends Fragment {

    private View mView;
    private MusicThread musicThread;
    private BoxTimerThread boxTimerThread;
    private AppDatabase mDb;
    private AppViewModel mAppViewModel;

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

        mDb = ((MainActivity)getActivity()).getDatabase();
        mAppViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        boxTimerThread = new BoxTimerThread((TextView) mView.findViewById(R.id.time));
        boxTimerThread.start();

        Vehicle vehicle = (Vehicle) mView.findViewById(R.id.vehicle);
        vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(mView).navigate(R.id.action_garageFragment_to_vehicleModificationFragment);
            }
        });

        Button fightButton = mView.findViewById(R.id.fightButton);
        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(mView).navigate(R.id.action_garageFragment_to_fightFragment);
            }
        });

//        (new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<InventoryItem> items = mDb.userDao().getUserInventory(mAppViewModel.getUser().getValue().userId);
//                vehicle.setDatabase(mDb);
//                vehicle.init(items.stream().filter(item -> item.active == true).collect(Collectors.toList()));
//            }
//        })).start();

        return mView;
    }

    @Override
    public void onPause() {
        super.onPause();

        musicThread.stopThread();
    }
}
