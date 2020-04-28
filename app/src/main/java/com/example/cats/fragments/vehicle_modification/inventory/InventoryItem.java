package com.example.cats.fragments.vehicle_modification.inventory;

import androidx.room.Embedded;

import com.example.cats.models.entities.Component;

public class InventoryItem {
    @Embedded
    public Component component;
    public boolean active;

    public InventoryItem(String name, String type, int health, String img, int power, int energy, boolean active) {
        this.component = new Component(name, type, health, img, power, energy);
        this.active = active;
    }

    public InventoryItem() {
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
