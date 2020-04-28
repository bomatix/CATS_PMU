package com.example.cats.util;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.Spinner;

import java.lang.reflect.Field;

public class Methods {

    public static void avoidSpinnerDropdownFocus(Spinner spinner) {
        try {
            Field listPopupField = Spinner.class.getDeclaredField("mPopup");
            listPopupField.setAccessible(true);
            Object listPopup = listPopupField.get(spinner);
            if (listPopup instanceof ListPopupWindow) {
                Field popupField = ListPopupWindow.class.getDeclaredField("mPopup");
                popupField.setAccessible(true);
                Object popup = popupField.get((ListPopupWindow) listPopup);
                if (popup instanceof PopupWindow) {
                    ((PopupWindow) popup).setFocusable(false);
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static String milisecondsFormat(int seconds) {
        int minutes = seconds/60;
        seconds = seconds%60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static int drawableFromString(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public static boolean isViewContains(View view, int rx, int ry) {
        int[] l = new int[2];
        view.getLocationOnScreen(l);
        int x = l[0];
        int y = l[1];
        int w = view.getWidth();
        int h = view.getHeight();

         if (rx < x || rx > x + w || ry < y || ry > y + h) {
            return false;
        }
        return true;
    }
    public static boolean doViewsOverlap(View view1, View view2) {
        int[] l1 = new int[2];
        int[] l2 = new int[2];
        view1.getLocationOnScreen(l1);
        view2.getLocationOnScreen(l2);
        int x1 = l1[0], y1 = l1[1], x2 = l2[0], y2 = l2[1];
        int w1 = view1.getWidth(), h1 = view1.getHeight(), w2 = view2.getWidth(), h2 = view2.getHeight();

        if (x1 >= (x2 + w2) || x2 >= (x1 + w1)) {
            return false;
        }

        if (y1 >= (y2 + h2) || y2 >= (y1 + h1)) {
            return false;
        }
        return true;
    }

    public static void logMotionEventType(MotionEvent e, String tag) {
        int action = e.getActionMasked();
        String tagLog = "TOUCH_LOG_" + tag;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(tagLog, "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(tagLog, "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(tagLog, "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(tagLog, "ACTION_UP");
                break;
        }
    }
}
