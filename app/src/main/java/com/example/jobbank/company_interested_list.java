package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class company_interested_list extends AppCompatActivity {


    String firebaseChild ="Users";
    myadapter_company_interested adapter;

    FirebaseRecyclerOptions<model_company_interested> options;


    RecyclerView recview_interest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_interested_list);

        recview_interest = (RecyclerView) findViewById(R.id.rec_view_company_interested);

        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(this.getApplicationContext()));
        recview_interest.setLayoutManager(linearLayoutManager);

        options = new FirebaseRecyclerOptions.Builder<model_company_interested>().setQuery(FirebaseDatabase.getInstance().getReference().child(firebaseChild), model_company_interested.class).build();
        adapter = new myadapter_company_interested(options);
        recview_interest.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("start","Activity started");
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("stop","Activity stopped");
        adapter.stopListening();
    }
}