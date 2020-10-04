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


                //retrieving data from firebase
                Intent i = new Intent(getApplicationContext(), view_vacancy.class);
                i.putExtra("DESCRIPTION", options.getSnapshots().get(position).getDescription());
                i.putExtra("QUALIFICATION", options.getSnapshots().get(position).getQualification());
                i.putExtra("JOB_TITLE", options.getSnapshots().get(position).getJobTitle());
                i.putExtra("CLOSING_DATE", options.getSnapshots().get(position).getClosingDate());
                i.putExtra("PUBLISHED_DATE", options.getSnapshots().get(position).getPublishedDate());
                i.putExtra("VACANCY_ID", options.getSnapshots().get(position).getVacancyId());

                i.putExtra("AGE_LIMIT", options.getSnapshots().get(position).getAgeLimit());
                i.putExtra("JOB_TYPE", options.getSnapshots().get(position).getJobType());
                i.putExtra("YEARS_OF_EXP", options.getSnapshots().get(position).getYrsOfExp());
                i.putExtra("DEPARTMENT", options.getSnapshots().get(position).getDepartment());

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