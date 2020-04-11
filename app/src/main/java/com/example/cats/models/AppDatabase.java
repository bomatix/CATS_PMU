package com.example.cats.models;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cats.models.daos.UserDao;
import com.example.cats.models.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
