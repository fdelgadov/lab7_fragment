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
import com.example.lab7_fragment.fragments.FragmentSpanish;
import com.example.lab7_fragment.fragments.FragmentEnglish;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public final String TAG = "MainActivity";
    private Fragment fragmentCurrent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView botNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                handleItemSelected(item);
                return true;
            }
        });
    }

    private void handleItemSelected(MenuItem item){
        if (item.getItemId() == R.id.action_english) {
            fragmentCurrent = new FragmentEnglish();
            switchFragment(fragmentCurrent);
        } else if (item.getItemId() == R.id.action_spanish) {
            fragmentCurrent = new FragmentSpanish();
            switchFragment(fragmentCurrent);
        }
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_principal, fragment);
        fragmentTransaction.commit();
    }
}
