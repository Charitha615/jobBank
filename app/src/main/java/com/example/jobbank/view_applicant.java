package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class view_applicant extends AppCompatActivity {


    String firebaseChild ="User_Req_Job";
    myadapter_view_applicant adapter_applicant;

    FirebaseRecyclerOptions<model_view_applicants> options;
    myadapter_view_applicant.RecyclerViewClickListener listener;


    RecyclerView recview_applicant;
    Button btn_view_list;
    TextView job_title_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicant);
        setOnClickListener();
        recview_applicant = (RecyclerView) findViewById(R.id.recview_view_applicant);
        btn_view_list = findViewById(R.id.btn_viewList);

        job_title_textView = findViewById(R.id.job_title);

        Intent o = getIntent();
        job_title_textView.setText(o.getStringExtra("TITLE"));


        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(this.getApplicationContext()));
        recview_applicant.setLayoutManager(linearLayoutManager);

        options = new FirebaseRecyclerOptions.Builder<model_view_applicants>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child(firebaseChild), model_view_applicants.class).build();

        adapter_applicant = new myadapter_view_applicant(options,listener);
        recview_applicant.setAdapter(adapter_applicant);

        btn_view_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), view_list.class);
                i.putExtra("TITLE", job_title_textView.getText().toString());
                startActivity(i);
            }
        });
    }

    private void setOnClickListener() {
        listener = new myadapter_view_applicant.RecyclerViewClickListener() {

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
        adapter_applicant.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("stop","Activity stopped");
        adapter_applicant.stopListening();
    }
}