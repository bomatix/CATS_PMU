package com.example.cats.models.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.cats.models.entities.ClosedBox;

@Dao
public interface ClosedBoxDao {

    @Insert
    void addClosedBox(ClosedBox closedBox);
}
