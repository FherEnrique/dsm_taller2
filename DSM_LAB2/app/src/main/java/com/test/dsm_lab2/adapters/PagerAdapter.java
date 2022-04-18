package com.test.dsm_lab2.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.test.dsm_lab2.fragments.DrinksFragment;
import com.test.dsm_lab2.fragments.FoodsFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new FoodsFragment();
            case 1: return new DrinksFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "PLATOS FUERTES";
            case 1: return "BEBIDAS";
            default: return null;
        }
    }
}
