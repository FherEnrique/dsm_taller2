package com.test.dsm_lab2.services;

import com.test.dsm_lab2.models.Consumable;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Singleton
public class ShoppingService {
    private final ArrayList<Consumable> consumables = new ArrayList<>();
    private final ArrayList<Double> bought = new ArrayList<>();

    @Inject
    ShoppingService() { }

    public ArrayList<Consumable> addItem(Consumable consumable) {
        consumables.add(consumable);
        return consumables;
    }

    public ArrayList<Consumable> getItems() {
        return consumables;
    }

    public void clearItems() {
        consumables.clear();
    }

    public void removeItemAt(int position) {
        consumables.remove(position);
    }

    public void addBoughtItem(double finalPrice) {
        bought.add(finalPrice);
    }

    public ArrayList<Double> getBoughtItems() {
        return bought;
    }
}
