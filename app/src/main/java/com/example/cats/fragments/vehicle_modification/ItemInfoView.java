package com.example.cats.fragments.vehicle_modification;

import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cats.R;
import com.example.cats.fragments.vehicle_modification.inventory.InventoryItem;
import com.example.cats.models.entities.Component;

public class ItemInfoView {

    private View infoView;
    private TextView power, energy, health;

    public ItemInfoView(View infoView) {
        this.infoView = infoView;
        power = infoView.findViewById(R.id.power);
        energy = infoView.findViewById(R.id.energy);
        health = infoView.findViewById(R.id.health);
    }

    public void setViewData(InventoryItem item) {
        power.setText(item.component.power+"");
        energy.setText(item.component.energy+"");
        health.setText(item.component.health+"");
    }


}
