package com.example.cats.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"userId", "itemId"})
public class UserComponent {
    public UserComponent(long userId, long itemId) {
        this.userId = userId;
        this.itemId = itemId;
        active = false;
    }

    public UserComponent(long userId, long itemId, boolean active) {
        this.userId = userId;
        this.itemId = itemId;
        this.active = active;
    }

    public UserComponent() {
    }

    public long userId;
    public long itemId;

    @ColumnInfo
    public boolean active;
}
