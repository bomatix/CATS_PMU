package com.example.cats.fragments.choose_player;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.cats.models.AppDatabase;
import com.example.cats.models.entities.User;
import com.example.cats.viewmodels.AppViewModel;

import java.util.ArrayList;
import java.util.List;

public class GetPlayersTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private AppViewModel appViewModel;
    private AppDatabase db;
    private Spinner spinner;
    private List<User> users;

    public GetPlayersTask(Context context, AppViewModel appViewModel, AppDatabase db, Spinner spinner) {
        this.context = context;
        this.appViewModel = appViewModel;
        this.db = db;
        this.spinner = spinner;
        this.users = users;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        users = db.userDao().getAll();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                List<String> usernames = new ArrayList<>();
                for(User user: users){
                    usernames.add(user.username);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, usernames);
                spinner.setAdapter(adapter);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("SpinnerItem", users.get(position).username);
                appViewModel.loadUser(users.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return null;
    }
}
