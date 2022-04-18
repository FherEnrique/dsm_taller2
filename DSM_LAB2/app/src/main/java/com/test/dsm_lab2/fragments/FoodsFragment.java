package com.test.dsm_lab2.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.dsm_lab2.R;
import com.test.dsm_lab2.adapters.ConsumableAdapter;
import com.test.dsm_lab2.services.FoodService;
import com.test.dsm_lab2.services.ShoppingService;

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FoodsFragment extends Fragment {
    @Inject
    FoodService foodService;
    @Inject
    ShoppingService shoppingService;

    public FoodsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_foods, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        foodService.getFoodsCollection(() -> {
            RecyclerView recyclerView = view.findViewById(R.id.foodList);
            recyclerView.setAdapter(new ConsumableAdapter(foodService.getFoods(), shoppingService, true, null));
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        });
    }
}