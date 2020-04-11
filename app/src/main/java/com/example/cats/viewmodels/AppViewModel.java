package com.example.cats.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cats.models.entities.User;

public class AppViewModel extends ViewModel {

    private MutableLiveData<User> activeUser = new MutableLiveData<>();

    public LiveData<User> getUser(){
        return activeUser;
    }

    public void loadUser(User user) {
        activeUser.setValue(user);
    }
}
