package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Company_home extends AppCompatActivity {
    TextView single_card;
    ImageButton menu,home,my_app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_home);


        menu = findViewById(R.id.company_menu_btn);
        home = findViewById(R.id.c_home_btn);
        my_app = findViewById(R.id.c_my_application_nav);

//Dashboard Button
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;

                fragment = new company_dashbord();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container_, fragment).addToBackStack(null);
                ft.commit();
            }
        });

        //Home

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;

                fragment = new Fragment_Home();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container_, fragment).addToBackStack(null);
                ft.commit();
            }
        });

        //My application

        my_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;

                fragment = new myApplicationFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container_, fragment).addToBackStack(null);
                ft.commit();
            }
        });
    }
}