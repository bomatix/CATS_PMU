package com.example.cats.fragments.vehicle_modification;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.widget.ImageViewCompat;

import com.example.cats.R;
import com.example.cats.fragments.vehicle_modification.inventory.InventoryItem;
import com.example.cats.models.AppDatabase;
import com.example.cats.models.entities.Component;
import com.example.cats.util.Methods;

import java.util.ArrayList;
import java.util.List;

class SlottedSpace  implements Slottable {

    private ImageView imageView;
    private InventoryItem inventoryItem;
    private String type;
    private AppDatabase mDb;

    public SlottedSpace(ImageView imageView, String type, AppDatabase db) {
        this.imageView = imageView;
        this.type = type;
        this.mDb = db;
    }

    public SlottedSpace(ImageView imageView, InventoryItem inventoryItem, String type) {
        this.imageView = imageView;
        this.type = type;
        this.inventoryItem = inventoryItem;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setInventoryItem(Context context, InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
        int drw = Methods.drawableFromString(context, inventoryItem.component.img);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageResource(drw);
            }
        });

    }



    @Override
    public Pair<Boolean, InventoryItem> onSlotDrop(Context context, InventoryItem item, View draggable) {
        boolean droppedOk = Methods.doViewsOverlap(imageView, draggable) && (item.component.type.equals(type));
        InventoryItem oldInventoryItem = null;
        if(droppedOk) {
            oldInventoryItem = inventoryItem;
            inventoryItem = item;
            int drw = Methods.drawableFromString(context, item.component.img);
            imageView.setImageResource(drw);
            (new Thread(new Runnable() {
                @Override
                public void run() {

                }
            })).start();
        }
        return new Pair<>(droppedOk, oldInventoryItem);
    }
}

public class Vehicle extends ConstraintLayout {
    private SlottedSpace chassis, wheel1, wheel2, weapon;
    private Context context;
    private AppDatabase mDb;

    private int power, health, energy;

    public void setDatabase(AppDatabase db) {
        this.mDb = db;
    }

    private void setInventoryItem(Context context, InventoryItem inventoryItem) {
        switch (inventoryItem.component.type) {
            case "chassis" : {
                this.chassis.setInventoryItem(context, inventoryItem);
                break;
            }
            case "weapon" : {
                this.weapon.setInventoryItem(context, inventoryItem);
                break;
            }
            case "wheel" : {
                this.wheel1.setInventoryItem(context, inventoryItem);
                this.wheel2.setInventoryItem(context, inventoryItem);
                break;
            }
        }
    }

    public void setWheel1(SlottedSpace wheel1) {
        this.wheel1 = wheel1;
    }

    public void setWheel2(SlottedSpace wheel2) {
        this.wheel2 = wheel2;
    }

    public void setWeapon(SlottedSpace weapon) {
        this.weapon = weapon;
    }

    public List<Slottable> getComponents() {
        ArrayList<Slottable> components = new ArrayList<>();
        components.add(chassis);
        components.add(weapon);
        components.add(wheel1);
        components.add(wheel2);
        return components;
    }

    public Vehicle(Context context) {
        super(context);
        this.context = context;
    }

    public Vehicle(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init();

    }

    public void init(List<InventoryItem> items) {
        inflate(getContext(), R.layout.custom_vehicle, this);

        chassis = new SlottedSpace((ImageView) findViewById(R.id.chassis), "chassis", mDb);
        wheel1 = new SlottedSpace((ImageView) findViewById(R.id.wheel1), "wheel", mDb);
        wheel2 = new SlottedSpace((ImageView) findViewById(R.id.wheel2), "wheel", mDb);
        weapon = new SlottedSpace((ImageView) findViewById(R.id.weapon), "weapon", mDb);

        for(InventoryItem i: items) {
            setInventoryItem(getContext(), i);
            power += i.component.power;
            health += i.component.health;
            energy += i.component.energy;
        }


        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        constraintSet.connect(R.id.wheel1, ConstraintSet.BOTTOM, R.id.chassis, ConstraintSet.BOTTOM,0);
        constraintSet.connect(R.id.wheel1, ConstraintSet.LEFT, R.id.chassis, ConstraintSet.LEFT,15);

        constraintSet.connect(R.id.wheel2, ConstraintSet.BOTTOM, R.id.chassis, ConstraintSet.BOTTOM,0);
        constraintSet.connect(R.id.wheel2, ConstraintSet.LEFT, R.id.chassis, ConstraintSet.LEFT,210);

        constraintSet.connect(R.id.weapon, ConstraintSet.TOP, R.id.chassis, ConstraintSet.TOP,40);
        constraintSet.connect(R.id.weapon, ConstraintSet.LEFT, R.id.chassis, ConstraintSet.LEFT,200);

        final ConstraintLayout constraintLayout = this;

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                constraintSet.applyTo(constraintLayout);
            }
        });

    }
}
