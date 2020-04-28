package com.example.cats.fragments.vehicle_modification;

import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cats.MainActivity;
import com.example.cats.R;
import com.example.cats.fragments.vehicle_modification.inventory.Inventory;
import com.example.cats.fragments.vehicle_modification.inventory.InventoryItem;
import com.example.cats.fragments.vehicle_modification.inventory.InventoryItemAdapter;
import com.example.cats.models.AppDatabase;
import com.example.cats.models.entities.Component;
import com.example.cats.viewmodels.AppViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleModificationFragment extends Fragment {

    private View mView;
    private AppDatabase mDb;
    private AppViewModel mAppViewModel;

    public VehicleModificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_vehicle_modification, container, false);

        final Inventory inventory = mView.findViewById(R.id.recyclerView);

        mDb = ((MainActivity)getActivity()).getDatabase();

        mAppViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        inventory.setLayoutManager(layoutManager);




        (new Thread(new Runnable() {
            @Override
            public void run() {
//                List<Component> items = mDb.componentDao().getAll();
//                List<Long> ids = mDb.userDao().getUserInventory(mAppViewModel.getUser().getValue().userId);
//                List<Component> items = mDb.componentDao().getComponentFromIds(ids);
                List<InventoryItem> items = mDb.userDao().getUserInventory1(mAppViewModel.getUser().getValue().userId);
                ItemInfoView itemInfoView = new ItemInfoView(mView.findViewById(R.id.itemInfoView));
                final Vehicle vehicle = mView.findViewById(R.id.vehicleModification);
                InventoryItemAdapter adapter = new InventoryItemAdapter(getContext(), items, itemInfoView, vehicle, inventory);
                inventory.setAdapter(adapter);
            }
        })).start();

        return mView;
    }
}
