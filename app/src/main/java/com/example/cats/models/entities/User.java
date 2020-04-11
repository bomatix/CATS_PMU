package com.example.cats.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public long uid;

    @ColumnInfo
    public String username;

    @ColumnInfo
    public int 
}
