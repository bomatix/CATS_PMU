package com.example.cats.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cats.util.TimeConverter;

import java.util.Date;

@Entity
public class ClosedBox {
    @PrimaryKey(autoGenerate = true)
    public long boxId;

    public long userId;

    @ColumnInfo
    @TypeConverters({TimeConverter.class})
    public Date createdAt;
}
