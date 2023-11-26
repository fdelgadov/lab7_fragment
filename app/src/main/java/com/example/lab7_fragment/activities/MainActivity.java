package com.example.lab7_fragment.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.lab7_fragment.R;
import com.example.lab7_fragment.fragments.FragmentCallback;
import com.example.lab7_fragment.fragments.FragmentSpanish;
import com.example.lab7_fragment.fragments.FragmentEnglish;

import com.example.lab7_fragment.fragments.FragmentTranslate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements MainCallback {
    public final String TAG = "MainActivity";
    public static final int TRANSLATE = 0;
    private Fragment fragmentCurrent;
    private FragmentCallback fragmentCurrentCallback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadTranslate();
        loadMenu();
        switchFragment(new FragmentSpanish());
    }

    private void handleItemSelected(MenuItem item){
        if (item.getItemId() == R.id.action_english) {
            fragmentCurrent = new FragmentEnglish();
            fragmentCurrentCallback = (FragmentCallback) fragmentCurrent;
            switchFragment(fragmentCurrent);
        } else if (item.getItemId() == R.id.action_spanish) {
            fragmentCurrent = new FragmentSpanish();
            fragmentCurrentCallback = (FragmentCallback) fragmentCurrent;
            switchFragment(fragmentCurrent);
        }
    }

    private void loadTranslate(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_translate, new FragmentTranslate());
        fragmentTransaction.commit();
    }

    private void loadMenu(){
        BottomNavigationView botNav = findViewById(R.id.bottom_navigation);

        botNav.setOnNavigationItemSelectedListener(item -> {
            handleItemSelected(item);
            return true;
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_principal, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void msgToMain(int k, int v){
        switch (k){
            case TRANSLATE:
                fragmentCurrentCallback.msgToFragment();
                break;
        }
    }
}
