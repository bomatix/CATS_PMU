package com.example.cats.models.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class UserInventory {
    @Embedded public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "itemId",
            associateBy = @Junction(UserComponent.class)
    )
    public List<Component> components;
}
