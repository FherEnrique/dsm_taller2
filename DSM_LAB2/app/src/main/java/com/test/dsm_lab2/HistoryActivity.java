package com.test.dsm_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.test.dsm_lab2.adapters.HistoryAdapter;
import com.test.dsm_lab2.services.ShoppingService;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoryActivity extends AppCompatActivity {
    @Inject
    ShoppingService shoppingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ArrayList<Double> history = shoppingService.getBoughtItems();
        RecyclerView recyclerView = findViewById(R.id.historyRecyclerView);

        recyclerView.setAdapter(new HistoryAdapter(history));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
