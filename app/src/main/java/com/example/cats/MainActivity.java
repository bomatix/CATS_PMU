package com.example.cats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;

import com.example.cats.models.AppDatabase;

public class MainActivity extends AppCompatActivity {

    private View decorView;
    public AppDatabase db;

    public AppDatabase getDatabase() {
        return db;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decorView = getWindow().getDecorView();

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "cats").build();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
            super.onWindowFocusChanged(hasFocus);
                int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                decorView.setSystemUiVisibility(uiOptions);
            getSupportActionBar().hide();
    }
}
