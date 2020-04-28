package com.example.cats.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class ChassisInfo {

    @ColumnInfo
    public int wheel1;

    @ColumnInfo
    public int wheel2;

    @ColumnInfo
    public int weapon_right;

    @ColumnInfo
    public int weapon_top;

    @ColumnInfo
    public int weapon_rotation;
}
