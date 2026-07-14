package com.example.pixxa;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNav = findViewById(R.id.bottomNav);

        // Load Home Fragment first
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, new HomeFragment())
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {

            Fragment fragment = null;

            if (item.getItemId() == R.id.nav_home) {
                fragment = new HomeFragment();

            } else if (item.getItemId() == R.id.nav_cart) {
                fragment = new CartFragment();

            } else if (item.getItemId() == R.id.nav_orders) {
                fragment = new OrdersFragment();

            } else if (item.getItemId() == R.id.nav_profile) {
                fragment = new ProfileFragment();
            }

            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .commit();
            }

            return true;
        });
    }
}