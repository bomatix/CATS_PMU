package com.example.cats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;

import com.example.cats.models.AppDatabase;
import com.example.cats.models.entities.Component;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.concurrent.Executors;

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

        Stetho.initializeWithDefaults(this);
        RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Component> components = new ArrayList<>();
                        components.add(new Component("Chainsaw", "weapon",5, "weapon_chainsaw", 1, 2));
                        components.add(new Component("Drill", "weapon",5, "weapon_drill", 1, 3));
                        components.add(new Component("Stinger", "weapon",5, "weapon_stinger", 1, 2));

                        components.add(new Component("Bigfoot", "wheel",5, "wheel_bigfoot", 1, 2));
                        components.add(new Component("Knob", "wheel",5, "wheel_knob", 1, 5));
                        components.add(new Component("Roller", "wheel",5, "wheel_roller", 1, 2));
                        components.add(new Component("Scooter", "wheel",5, "wheel_scooter", 1, 2));

                        components.add(new Component("Boulder", "chassis",5, "chassis_boulder", 1, 2));
                        components.add(new Component("Classic", "chassis",5, "chassis_classic", 1, 2));
                        components.add(new Component("Pyramid", "chassis",5, "chassis_pyramid", 1, 2));
                        components.add(new Component("Sneaky", "chassis",5, "chassis_sneaky", 1, 2));
                        components.add(new Component("Titan", "chassis",5, "chassis_titan", 1, 2));
                        getDatabase().componentDao().insertAll(components);
                    }
                });
            }
        };

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "cats").addCallback(rdc).build();

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
