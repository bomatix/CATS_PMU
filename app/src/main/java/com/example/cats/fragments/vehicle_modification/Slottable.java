package com.example.cats.fragments.vehicle_modification;

import android.content.Context;
import android.view.View;

import com.example.cats.fragments.vehicle_modification.inventory.InventoryItem;
import com.example.cats.models.entities.Component;

public interface Slottable {
    boolean onSlotDrop(Context context, InventoryItem item, View view);
}
