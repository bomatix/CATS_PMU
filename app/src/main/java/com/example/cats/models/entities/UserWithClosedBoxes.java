package com.example.cats.models.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithClosedBoxes {
    @Embedded public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "boxId"
    )
    public List<ClosedBox> closedBoxes;
}
