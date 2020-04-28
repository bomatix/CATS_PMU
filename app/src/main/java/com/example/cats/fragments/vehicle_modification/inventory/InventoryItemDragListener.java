package com.example.cats.fragments.vehicle_modification.inventory;

import android.os.Vibrator;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.MotionEventCompat;

import com.example.cats.R;
import com.example.cats.fragments.vehicle_modification.Vehicle;
import com.example.cats.models.entities.Component;
import com.example.cats.util.Methods;


public class InventoryItemDragListener implements View.OnTouchListener, View.OnLongClickListener, View.OnClickListener {

    private Vehicle vehicle;
    private Inventory inventory;
    private InventoryItem item;
    private InventoryItemAdapter.InventoryItemViewHolder viewHolder;
    private float mStartX, mStartY, mPosX, mPosY, mLastTouchX, mLastTouchY ;
    private boolean longPressActivated;
    public InventoryItemDragListener(Vehicle vehicle) {

        this.vehicle = vehicle;
    }

    public void setViewHolder(InventoryItemAdapter.InventoryItemViewHolder vh) {
        this.viewHolder = vh;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setComponent(InventoryItem item) {
        this.item = item;
    }

    public InventoryItemDragListener() {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int action = event.getActionMasked();
//        Log.d("EVENT", "EVENT " + action);
        Methods.logMotionEventType(event, "child");
            switch (action) {
                case MotionEvent.ACTION_DOWN : {
                    inventory.onTouchEvent(event);
                    mStartX = v.getX();
                        mStartY = v.getY();
                        mPosX = mStartX;
                        mPosY = mStartY;
                    mLastTouchX = event.getRawX();
                    mLastTouchY = event.getRawY();

                    if(longPressActivated) {

                        v.findViewById(R.id.text).setVisibility(View.INVISIBLE);

                        v.invalidate();
                    }


//                Log.d("LastTouch", "x: " + mLastTouchX + ", y: " + mLastTouchY);
                    break;
                }
                case MotionEvent.ACTION_MOVE : {

                    if(longPressActivated) {

                        final float x = event.getRawX();
                        final float y = event.getRawY();

                        final float dx = x - mLastTouchX;
                        final float dy = y - mLastTouchY;

                        mPosX += dx;
                        mPosY += dy;

                        v.setX(mPosX);
                        v.setY(mPosY);

                        v.invalidate();

                        mLastTouchX = x;
                        mLastTouchY = y;
                        return true;
                    }
//                Log.d("LastTouch", "x: " + mLastTouchX + ", y: " + mLastTouchY);

//                return true;
                    break;
                }
                case MotionEvent.ACTION_POINTER_UP : {
                    Log.d("ACTION", "ACTION_POINTER_UP");
//                return true;
                    break;
                }
                case MotionEvent.ACTION_UP : {
                    if(longPressActivated) {

                        if (viewHolder.tryToFit(item, v)) {
                            Log.d("DRAG", "TRUE");
                        }
//                Log.d("ACTION", "ACTION_UP");
                        v.setX(mStartX);
                        v.setY(mStartY);

                        v.findViewById(R.id.text).setVisibility(View.VISIBLE);

                        v.invalidate();
                        longPressActivated = false;

//                return true;
                    }
                    break;
                }
            }
         return false;
    }

    @Override
    public boolean onLongClick(View v) {
        Log.d("LongClick", "TRUE");
        inventory.vibrate();
        longPressActivated = true;
        inventory.disableScroll();
        return false;
    }

    @Override
    public void onClick(View v) {

    }
}
