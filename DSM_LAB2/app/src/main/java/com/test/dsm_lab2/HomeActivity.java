package com.test.dsm_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.test.dsm_lab2.adapters.PagerAdapter;
import com.test.dsm_lab2.services.ShoppingService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {
    @Inject
    ShoppingService shoppingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.menuTabLayout);

        // Default 2 tabs
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        FloatingActionButton cartButton = findViewById(R.id.cartButton);
        FloatingActionButton historyButton = findViewById(R.id.historyButton);

        cartButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ShoppingActivity.class);
            startActivity(intent);
        });

        historyButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        return keyCode != KeyEvent.KEYCODE_BACK;
    }
}