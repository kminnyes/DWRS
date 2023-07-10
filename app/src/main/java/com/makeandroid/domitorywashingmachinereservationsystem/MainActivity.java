package com.makeandroid.domitorywashingmachinereservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private FragHome fragHome;
    private FragTimeline fragTimeline;
    private FragNotifications fragNotifications;
    private FragPerson fragPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체 생성
        fragHome = new FragHome();
        fragTimeline = new FragTimeline();
        fragNotifications = new FragNotifications();
        fragPerson = new FragPerson();

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragHome).commit();

        NavigationBarView bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homeicon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragHome).commit();
                        return true;
                    case R.id.timelineicon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragTimeline).commit();
                        return true;
                    case R.id.notificationicons:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragNotifications).commit();
                        return true;
                    case R.id.personicon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, fragPerson).commit();
                        return true;

                }


                return false;
            }
        });


    }
}