package com.example.cats.fragments.garage;

import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cats.models.entities.User;
import com.example.cats.util.Consts;
import com.example.cats.util.Methods;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class BoxTimerThread extends Thread {

    private TextView mTextView;
    private int time;

    public BoxTimerThread(TextView textView) {
        mTextView = textView;
        time = Consts.TIME_TO_OPEN_BOX;
    }

    @Override
    public void run() {
        Handler handler = new Handler(Looper.getMainLooper());
        while(time >= 0) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText(Methods.milisecondsFormat(time));
                    time--;
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
