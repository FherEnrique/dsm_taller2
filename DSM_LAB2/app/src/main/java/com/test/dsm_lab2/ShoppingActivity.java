package com.test.dsm_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.dsm_lab2.adapters.ConsumableAdapter;
import com.test.dsm_lab2.models.Consumable;
import com.test.dsm_lab2.services.ShoppingService;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShoppingActivity extends AppCompatActivity {
    @Inject
    ShoppingService shoppingService;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        ArrayList<Consumable> consumables = reloadShoppingValues(false);
        prepareBuy(consumables);
    }

    public ArrayList<Consumable> reloadShoppingValues(boolean runCalculations) {
        RecyclerView recyclerView = findViewById(R.id.shoppingHistory);
        ArrayList<Consumable> consumables = shoppingService.getItems();
        ConsumableAdapter adapter = new ConsumableAdapter(consumables, shoppingService, false, () -> {
            reloadShoppingValues(true);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (runCalculations) {
            prepareBuy(consumables);
        }

        return consumables;
    }

    @SuppressLint("SetTextI18n")
    private double calculateFinalPrice(ArrayList<Consumable> consumables) {
        TextView finalTotalText = findViewById(R.id.finalTotal);
        double finalPrice = 0;
        for (Consumable consumable : consumables) {
            finalPrice += consumable.getPrice();
        }
        finalTotalText.setText("$" + finalPrice);
        return finalPrice;
    }

    @SuppressLint("SetTextI18n")
    private void prepareBuy(ArrayList<Consumable> consumables) {
        TextView finalTotalText = findViewById(R.id.finalTotal);
        Button buyButton = findViewById(R.id.buyButton);

        if (consumables.size() > 0) {
            double finalPrice = calculateFinalPrice(consumables);
            buyButton.setOnClickListener(view -> {
                shoppingService.addBoughtItem(finalPrice);
                shoppingService.clearItems();

                finalTotalText.setText("$0.00");

                reloadShoppingValues(true);

                super.onBackPressed();
            });
        } else {
            finalTotalText.setText("$0.00");
            buyButton.setVisibility(View.INVISIBLE);
        }
    }
}
