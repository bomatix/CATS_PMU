package com.example.cats.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public long userId;

    @ColumnInfo
    public String username;

    @ColumnInfo(defaultValue = "0")
    public int numberOfReadyBoxes;

    @ColumnInfo(defaultValue = "0")
    public int totalNumberOfWonBattles;

    @ColumnInfo(defaultValue = "0")
    public int wonBattlesCounter;
}
