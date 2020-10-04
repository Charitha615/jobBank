package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class view_published_vacancies extends AppCompatActivity {
    RecyclerView recview;
    myadapter adapter;
    pubVacancy pubVacancy_model;

    myadapter.RecyclerViewClickListener listener;
    FirebaseRecyclerOptions<pubVacancy> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_published_vacancies);

        setOnClickListner();
        recview = (RecyclerView)findViewById(R.id.jobTitleRecView);

        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(getApplicationContext()));
        recview.setLayoutManager(linearLayoutManager);

        options =
                new FirebaseRecyclerOptions.Builder<pubVacancy>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PublishVacancy"), pubVacancy.class)
                        .build();

        adapter = new myadapter(options,listener);

        recview.setAdapter(adapter);


    }

    private void setOnClickListner() {
        listener = new myadapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                Intent i = new Intent(getApplicationContext(), view_vacancy.class);
//                i.putExtra("DESCRIPTION",pubVacancy_model.getDescription());
//                i.putExtra("QUALIFICATION",pubVacancy_model.getQualification());
//                i.putExtra("JOB_TITLE",pubVacancy_model.getJobTitle());
//                i.putExtra("CLOSING_DATE",pubVacancy_model.getClosingDate());
//                //i.putExtra("PUBLISHED_DATE",pubVacancy_model.getPublishedDate());
                i.putExtra("DESCRIPTION", options.getSnapshots().get(position).getDescription());
                i.putExtra("QUALIFICATION", options.getSnapshots().get(position).getQualification());
                i.putExtra("JOB_TITLE", options.getSnapshots().get(position).getJobTitle());
                i.putExtra("CLOSING_DATE", options.getSnapshots().get(position).getClosingDate());
                i.putExtra("PUBLISHED_DATE", options.getSnapshots().get(position).getPublishedDate());

                startActivity(i);
            }
        };
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("start","fragment started");
        adapter.startListening();
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.i("stop","fragment stopped");
        adapter.stopListening();
    }
}