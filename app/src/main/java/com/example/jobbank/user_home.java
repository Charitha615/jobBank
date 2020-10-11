package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class user_home extends AppCompatActivity {

    TextView single_card;
    ImageButton menu,home,my_app,search;
    FirebaseRecyclerOptions<Model_Home> options;
    String firebaseChild ="PublishVacancy";
    my_adapter_home adapter;
    my_adapter_home.RecyclerViewClickListener listener;
    RecyclerView recyclerView;
    SearchView searchView;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
       // single_card = (TextView) findViewById(R.id.com_name_in);
        menu = findViewById(R.id.user_menu_btn);
        my_app = findViewById(R.id.my_application_nav);
        home = findViewById(R.id.home_btn);
        Paper.init(this);
        Fragment fragment;
        fragment = new Fragment_Home();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment).addToBackStack(null);
        ft.commit();



      //Dashboard Button

      menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment;

                fragment = new Fragment_User_Dashbord();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment).addToBackStack(null);
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
                ft.replace(R.id.fragment_container, fragment).addToBackStack(null);
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
                ft.replace(R.id.fragment_container, fragment).addToBackStack(null);
                ft.commit();
            }
        });

  // search



    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
//Search


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.searchh);

        searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                procsssearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procsssearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void procsssearch(String s) {
        recyclerView =findViewById(R.id.home_recycler1);
        setOnClickListner();

        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(getApplicationContext()));
        recyclerView.setLayoutManager(linearLayoutManager);
        options =
                new FirebaseRecyclerOptions.Builder<Model_Home>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(firebaseChild).orderByChild("jobTitle").startAt(s).endAt(s+"uf8ff"), Model_Home.class)
                        .build();

        adapter = new my_adapter_home(options,listener);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListner() {
        listener = new my_adapter_home.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                startActivity(new Intent(getApplicationContext(),user_add_cv.class));

            }
        };



    }
}