package com.example.app_qr.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.app_qr.Fragments.Criptex;
import com.example.app_qr.Fragments.FragmentAsk;
import com.example.app_qr.Fragments.FragmentWeb;
import com.example.app_qr.Fragments.Mapa;

public class PagerAdapter2 extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public PagerAdapter2(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentAsk();
            case 1:
                return new FragmentWeb();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
