package com.example.cats.models.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cats.fragments.vehicle_modification.inventory.InventoryItem;
import com.example.cats.models.entities.Component;
import com.example.cats.models.entities.UserComponent;

import java.util.List;

@Dao
public interface ComponentDao {

    @Query("SELECT * FROM Component")
    List<Component> getAll();

    @Query("SELECT * FROM component WHERE itemId IN (:ids)")
    List<Component> getComponentFromIds(List<Long> ids);

    @Update
    void updateComponent(Component component);

    @Update
    void updateUserComponent(UserComponent userComponent);

    @Insert
    void insertAll(List<Component> components);
}
