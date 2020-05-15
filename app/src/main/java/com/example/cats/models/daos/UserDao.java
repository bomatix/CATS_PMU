package com.example.cats.models.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.cats.fragments.vehicle_modification.inventory.InventoryItem;
import com.example.cats.models.entities.Component;
import com.example.cats.models.entities.User;
import com.example.cats.models.entities.UserComponent;
import com.example.cats.models.entities.UserInventory;
import com.example.cats.models.entities.UserWithClosedBoxes;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE userId = :userId")
    List<UserWithClosedBoxes> getUserWithClosedBoxes(long userId);
//
//    @Transaction
//    @Query("SELECT uc.itemId FROM usercomponent uc WHERE userId = :userId")
//    List<Long> getUserInventory(long userId);


    @Transaction
    @Query("SELECT c.*, uc.active  FROM component c, usercomponent uc WHERE uc.userId = :userId AND uc.itemId = c.itemId")
    List<InventoryItem> getUserInventory(long userId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addComponentToInventory(UserComponent userComponent);

    @Insert
    long addUser(User user);

}
