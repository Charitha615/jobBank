package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout bottom,top;
    private ImageView homeLink, searchLink,notificationLink,messageLink;
    private CircleImageView profileLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom = (LinearLayout)findViewById(R.id.bottom);
        top = (LinearLayout)findViewById(R.id.top);

        homeLink = (ImageView)findViewById(R.id.home);



        Fragment fragment;

        /*fragment = new MessageFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment).addToBackStack(null);
        ft.commit();*/

    }
}