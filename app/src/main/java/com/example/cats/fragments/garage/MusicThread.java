package com.example.cats.fragments.garage;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.MediaPlayer;

import com.example.cats.R;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MusicThread extends Thread {

    private List<String> songs;
    private Context context;
    private int songIndex = 0;
    private MediaPlayer mediaPlayer;
    private boolean running = true;

    public MusicThread(Context context) {
        this.songs = new ArrayList();
        songs.add("anewbeginning");
        songs.add("creativeminds");
        songs.add("summer");
        songs.add("ukulele");
        this.context = context;
    }

    public synchronized void stopThread() {
        notify();
        running = false;
    }

    @Override
    public void run() {
        while(running){
            songIndex = (int)(Math.random()*songs.size());
            if(mediaPlayer != null) mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(context, getResId(songs.get(songIndex), R.raw.class));
            mediaPlayer.setOnCompletionListener(new onCompletion());
            mediaPlayer.start();
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    class onCompletion implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            synchronized(MusicThread.this) {
                MusicThread.this.notify();
            }
        }
    }

    public static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
