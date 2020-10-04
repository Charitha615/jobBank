package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class company_bookmarked_list extends AppCompatActivity {


    String firebaseChild ="User_Req_Job";
    myadapter_company_bookmarked adapter;

    FirebaseRecyclerOptions<model_company_bookmarked> options;


    RecyclerView recview_bookmarked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_bookmarked_list);

        recview_bookmarked = (RecyclerView) findViewById(R.id.recview_bookmarked);

        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(this.getApplicationContext()));
        recview_bookmarked.setLayoutManager(linearLayoutManager);

        options = new FirebaseRecyclerOptions.Builder<model_company_bookmarked>().setQuery(FirebaseDatabase.getInstance().getReference().child(firebaseChild), model_company_bookmarked.class).build();
        adapter = new myadapter_company_bookmarked(options);
        recview_bookmarked.setAdapter(adapter);
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