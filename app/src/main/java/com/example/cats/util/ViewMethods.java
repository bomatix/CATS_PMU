package com.example.cats.util;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

public class ViewMethods {

    public static ShapeDrawable borderBackground1() {
        ShapeDrawable sd = new ShapeDrawable();
        sd.setShape(new RectShape());
        sd.getPaint().setColor(Color.RED);
        sd.getPaint().setStrokeWidth(10f);
        sd.getPaint().setStyle(Paint.Style.STROKE);
        return sd;
    }

    public static GradientDrawable borderBackground() {
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(25);
        gd.setColor(0x33FFFFFF);
        return gd;
    }

}
