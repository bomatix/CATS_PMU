package com.example.cats.fragments.vehicle_modification;

import android.content.Context;
import android.util.Pair;
import android.view.View;

import com.example.cats.fragments.vehicle_modification.inventory.InventoryItem;
import com.example.cats.models.entities.Component;

public interface Slottable {
    Pair<Boolean, InventoryItem> onSlotDrop(Context context, InventoryItem item, View view);
}
