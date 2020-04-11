package com.example.cats.fragments.register;

import android.os.AsyncTask;
import android.view.View;

import androidx.navigation.Navigation;

import com.example.cats.R;
import com.example.cats.models.AppDatabase;
import com.example.cats.models.entities.User;
import com.example.cats.viewmodels.AppViewModel;

public class RegisterTask extends AsyncTask<Void, Void, Void> {

    private AppViewModel appViewModel;
    private AppDatabase db;
    private View fragment;
    private long insertedId;
    private User user;

    public RegisterTask(AppViewModel appViewModel, AppDatabase db, View fragment, User user) {
        this.appViewModel = appViewModel;
        this.db = db;
        this.fragment = fragment;
        this.user = user;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        insertedId = db.userDao().addUser(user);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        user.uid = insertedId;
        appViewModel.loadUser(user);
        Navigation.findNavController(fragment).navigate(R.id.action_registerFragment_to_garageFragment);
    }
}

