package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class view_list extends AppCompatActivity {


    String firebaseChild ="User_Req_Job";
    myadapter_view_list adapter_list;

    FirebaseRecyclerOptions<model_view_list> options;
    myadapter_view_list.RecyclerViewClickListener listener;
    TextView job_title_TextView;

    RecyclerView recview_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        setOnClickListener();
        recview_list = (RecyclerView) findViewById(R.id.rec_view_list_view_interview);
        job_title_TextView = findViewById(R.id.job_title14);

        Intent o = getIntent();
        job_title_TextView.setText(o.getStringExtra("TITLE"));

        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(this.getApplicationContext()));
        recview_list.setLayoutManager(linearLayoutManager);

        options = new FirebaseRecyclerOptions.Builder<model_view_list>().setQuery(FirebaseDatabase.getInstance().getReference().child(firebaseChild), model_view_list.class).build();

        adapter_list = new myadapter_view_list(options,listener);
        recview_list.setAdapter(adapter_list);
    }

    private void setOnClickListener() {
        listener = new myadapter_view_list.RecyclerViewClickListener() {

            @Override
            public void onClick(View v, int position) {

                Intent intent = new Intent(getApplicationContext(), examine_applicants.class);
                intent.putExtra("vJobTitle", options.getSnapshots().get(position).getPosition());
                intent.putExtra("vName", options.getSnapshots().get(position).getFull_name());
                intent.putExtra("vNic", options.getSnapshots().get(position).getNic_number());
                intent.putExtra("vEmail", options.getSnapshots().get(position).getEmail());
                intent.putExtra("vAge", options.getSnapshots().get(position).getAge());
                intent.putExtra("vExperience", options.getSnapshots().get(position).getExperience());
                intent.putExtra("vDescription", options.getSnapshots().get(position).getDescription());
                intent.putExtra("vQualification", options.getSnapshots().get(position).getQualifications());
                intent.putExtra("vRemarks", options.getSnapshots().get(position).getRemarks());
                startActivity(intent);
            }
        };
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("start","Activity started");
        adapter_list.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("stop","Activity stopped");
        adapter_list.stopListening();
    }
}