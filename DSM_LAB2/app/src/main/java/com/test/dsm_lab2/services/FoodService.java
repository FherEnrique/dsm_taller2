package com.test.dsm_lab2.services;

import android.os.Handler;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.test.dsm_lab2.models.Consumable;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class FoodService {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<Consumable> foods = new ArrayList<>();
    private ArrayList<Consumable> drinks = new ArrayList<>();

    @Inject FoodService() { }

    @Provides
    public ArrayList<Consumable> getDrinksCollection(Runnable runnable) {
        if (drinks.size() == 0) {
            return getConsumables("drinks", runnable);
        }
        return drinks;
    }

    @Provides
    public ArrayList<Consumable> getFoodsCollection(Runnable runnable) {
        if (foods.size() == 0) {
            return getConsumables("foods", runnable);
        }
        return foods;
    }

    @Provides
    public ArrayList<Consumable> getDrinks() {
        return drinks;
    }

    @Provides
    public ArrayList<Consumable> getFoods() {
        return foods;
    }

    @Provides
    public FirebaseFirestore getDb() {
        return db;
    }

    private Consumable assignDocumentToInstance(QueryDocumentSnapshot document) {
        String id = document.getId();

        String name = document.getString("name");
        Double price = document.getDouble("price");
        String image = document.getString("image");

        return new Consumable(id, name, price, image);
    }

    private ArrayList<Consumable> getConsumables(String collectionName, Runnable callback) {
        ArrayList<Consumable> consumables = new ArrayList<>();
        db.collection(collectionName).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    try {
                        consumables.add(assignDocumentToInstance(document));
                    } catch (NullPointerException exception) {
                        Log.e("NPE", exception.getMessage());
                    }
                }
                if (consumables.size() > 0) {
                    if (collectionName.equals("foods")) {
                        foods = consumables;
                    } else {
                        drinks = consumables;
                    }
                }
                if (callback != null) {
                    callback.run();
                }
            }
        });
        return consumables;
    }
}
