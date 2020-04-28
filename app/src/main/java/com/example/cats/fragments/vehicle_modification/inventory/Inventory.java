package com.example.cats.fragments.vehicle_modification.inventory;

import android.content.Context;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cats.util.Methods;

public class Inventory extends RecyclerView {

    private boolean scroll = true, clicked = false, longClicked = false;
    private float mStartX, mStartY;
    private final int PIXEL_OFFSET = 20;
    private Context context;

    public Inventory(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void vibrate() {
        Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        final int action = e.getActionMasked();
        Methods.logMotionEventType(e, "onIntercept");
        if(action == MotionEvent.ACTION_DOWN) {
            mStartX = e.getX();
            mStartY = e.getY();
        }
        if(action == MotionEvent.ACTION_MOVE && scroll) {
            float dx = Math.abs(mStartX - e.getX());
            float dy = Math.abs(mStartY - e.getY());
            if(dx > PIXEL_OFFSET || dy > PIXEL_OFFSET)
                return true;
        }
        if(action == MotionEvent.ACTION_UP && !scroll) {
            scroll = true;
            return false;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Methods.logMotionEventType(e, "Inventory");
        super.onTouchEvent(e);
        return false;
    }

    public void disableScroll() {
        scroll = false;
    }
}
