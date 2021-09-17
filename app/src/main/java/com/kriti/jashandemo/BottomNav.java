package com.kriti.jashandemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNav extends AppCompatActivity {

    BottomNavigationView bottomNavView;
    HomeFragment homeFragment = new HomeFragment();
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        bottomNavView = findViewById(R.id.bottomNavView);
        fragmentManager.beginTransaction().replace(R.id.frameLayout, homeFragment).commit();

        bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.bottomNavHome)
                {
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
                }
                else if (id == R.id.bottomNavFriends)
                {
                    Toast.makeText(BottomNav.this, "Friends", Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.bottomNavSettings)
                {
                    Toast.makeText(BottomNav.this, "Settings", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
    }
}