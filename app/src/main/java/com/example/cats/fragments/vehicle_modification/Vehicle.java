package com.example.cats.fragments.vehicle_modification;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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
import com.example.cats.models.entities.Component;
import com.example.cats.util.Methods;

import java.util.ArrayList;
import java.util.List;

class SlottedSpace  implements Slottable {

    private ImageView imageView;
    private String type;
    private InventoryItem inventoryItem;

    public SlottedSpace(ImageView imageView, String type) {
        this.imageView = imageView;
        this.type = type;
    }

    public SlottedSpace(ImageView imageView, String type, InventoryItem inventoryItem) {
        this.imageView = imageView;
        this.type = type;
        this.inventoryItem = inventoryItem;
    }

    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public boolean onSlotDrop(Context context, InventoryItem item, View draggable) {
        boolean droppedOk = Methods.doViewsOverlap(imageView, draggable) && (item.component.type.equals(type));
        if(droppedOk) {
            inventoryItem = item;
            int drw = Methods.drawableFromString(context, item.component.img);
            imageView.setImageResource(drw);
        }
        return droppedOk;
    }
}

public class Vehicle extends ConstraintLayout {
    private SlottedSpace chassis, wheel1, wheel2, weapon;

    public ImageView getChassis() {
        return chassis.getImageView();
    }

    public ImageView getWheel1() {
        return wheel1.getImageView();
    }

    public ImageView getWheel2() {
        return wheel2.getImageView();
    }

    public ImageView getWeapon() {
        return weapon.getImageView();
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
    }

    public Vehicle(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init();

    }



    public void init() {
        inflate(getContext(), R.layout.custom_vehicle, this);

        chassis = new SlottedSpace((ImageView) findViewById(R.id.chassis), "chassis");
        wheel1 = new SlottedSpace((ImageView) findViewById(R.id.wheel1), "wheel");
        wheel2 = new SlottedSpace((ImageView) findViewById(R.id.wheel2), "wheel");
        weapon = new SlottedSpace((ImageView) findViewById(R.id.weapon), "weapon");

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this);
        constraintSet.connect(R.id.wheel1, ConstraintSet.BOTTOM, R.id.chassis, ConstraintSet.BOTTOM,0);
        constraintSet.connect(R.id.wheel1, ConstraintSet.LEFT, R.id.chassis, ConstraintSet.LEFT,15);

        constraintSet.connect(R.id.wheel2, ConstraintSet.BOTTOM, R.id.chassis, ConstraintSet.BOTTOM,0);
        constraintSet.connect(R.id.wheel2, ConstraintSet.LEFT, R.id.chassis, ConstraintSet.LEFT,210);

        constraintSet.connect(R.id.weapon, ConstraintSet.TOP, R.id.chassis, ConstraintSet.TOP,40);
        constraintSet.connect(R.id.weapon, ConstraintSet.LEFT, R.id.chassis, ConstraintSet.LEFT,200);

        constraintSet.applyTo(this);
    }
}
