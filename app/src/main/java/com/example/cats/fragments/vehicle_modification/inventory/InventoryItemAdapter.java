package com.example.cats.fragments.vehicle_modification.inventory;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cats.R;
import com.example.cats.fragments.vehicle_modification.ItemInfoView;
import com.example.cats.fragments.vehicle_modification.Slottable;
import com.example.cats.fragments.vehicle_modification.Vehicle;
import com.example.cats.models.entities.Component;
import com.example.cats.util.Methods;
import com.example.cats.util.ViewMethods;

import java.util.ArrayList;
import java.util.List;

public class InventoryItemAdapter extends RecyclerView.Adapter<InventoryItemAdapter.InventoryItemViewHolder> {

    private List<InventoryItem> items;
    private int selected = -1;
    private View selectedView = null;
    private ItemInfoView itemInfoView;
    private Context context;
    private Vehicle vehicle;
    private Inventory inventory;

    public InventoryItemAdapter(Context context, List<InventoryItem> items, ItemInfoView itemInfoView, Vehicle vehicle, Inventory inventory) {
        this.items = items;
        this.itemInfoView = itemInfoView;
        this.context = context;
        this.vehicle = vehicle;
        this.inventory = inventory;
    }

    private void selectedColor(View v, int position) {
        if(selected == position) {
            v.setBackground(ViewMethods.borderBackground());
        }
        else {
            v.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @NonNull
    @Override
    public InventoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item_adapter, parent, false);
        InventoryItemViewHolder vh = new InventoryItemViewHolder(v);
        vh.setVehicle(vehicle);
        vh.getInventoryItemDragListener().setViewHolder(vh);
        vh.setDrag();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryItemViewHolder holder, final int position) {
        TextView tv = (TextView) holder.view.findViewById(R.id.text);
        selectedColor(holder.view, position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                inventory.disableScroll();
                Log.d("Click", "TRUE");
                selected = selected == position ? (-1) : position;
                if(selectedView != null) {
                    selectedView.setBackgroundColor(Color.TRANSPARENT);
                }
                selectedView = v;
                selectedColor(v, position);
                if(selected != -1) {
                    itemInfoView.setViewData(items.get(position));
                }
                else {

                }
            }
        });
        tv.setText(items.get(position).component.name);
        ImageView imgv = (ImageView) holder.view.findViewById(R.id.item_img);
        int drw = Methods.drawableFromString(context, items.get(position).component.img);
        Log.d("drawable", drw + "");
        imgv.setImageResource(drw);
        holder.getInventoryItemDragListener().setComponent(items.get(position));
        holder.setSlottables(vehicle.getComponents());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class InventoryItemViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public String type;
        private boolean clicked;
        private InventoryItemDragListener inventoryItemDragListener;
        private List<Slottable> slottables = new ArrayList<>();

        public void setSlottables(List<Slottable> slottables) {
            this.slottables = slottables;
        }

        public boolean tryToFit(InventoryItem item, View view) {
            for(Slottable s: slottables) {
                if(s.onSlotDrop(context, item, view)) {
                    items.remove(item);
                    notifyDataSetChanged();
                    return true;
                }
            }
            return false;
        }

        public void setVehicle(Vehicle vehicle) {
            inventoryItemDragListener.setVehicle(vehicle);
        }

        public void setDrag() {
            inventoryItemDragListener.setVehicle(vehicle);
            inventoryItemDragListener.setInventory(inventory);
            view.setOnTouchListener(inventoryItemDragListener);
            view.setOnLongClickListener(inventoryItemDragListener);
        }

        public InventoryItemDragListener getInventoryItemDragListener() {
            return inventoryItemDragListener;
        }

        public InventoryItemViewHolder(View v) {
            super(v);
            view = v;
            inventoryItemDragListener = new InventoryItemDragListener();
//            view.setOnTouchListener(new InventoryItemDragListener(vehicle));
        }

    }
}
