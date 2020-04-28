package com.example.cats.models;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cats.models.daos.ComponentDao;
import com.example.cats.models.daos.UserDao;
import com.example.cats.models.entities.ClosedBox;
import com.example.cats.models.entities.Component;
import com.example.cats.models.entities.User;
import com.example.cats.models.entities.UserComponent;
import com.example.cats.models.entities.UserInventory;

@Database(entities = {  User.class,
                        ClosedBox.class,
                        Component.class,
                        UserComponent.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ComponentDao componentDao();
}
