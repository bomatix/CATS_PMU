package com.example.cats.models.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cats.models.entities.Component;

import java.util.List;

@Dao
public interface ComponentDao {

    @Query("SELECT * FROM Component")
    List<Component> getAll();

    @Query("SELECT * FROM component WHERE itemId IN (:ids)")
    List<Component> getComponentFromIds(List<Long> ids);

    @Insert
    void insertAll(List<Component> components);
}
