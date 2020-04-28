package com.example.cats.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Component {

    public Component(String name, String type, int health, String img, int power, int energy) {
        this.name = name;
        this.health = health;
        this.energy = energy;
        this.power = power;
        this.img = img;
        this.type = type;
    }

    @PrimaryKey(autoGenerate = true)
    public long itemId;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public int power;

    @ColumnInfo
    public int health;

    @ColumnInfo
    public int energy;

    @ColumnInfo
    public String img;

    @ColumnInfo
    public String type;

}
