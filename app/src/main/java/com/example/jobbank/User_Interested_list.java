package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class User_Interested_list extends AppCompatActivity {

    String firebaseChild ="User_Interested";
    RecyclerView recViewb;
    adapter_interested adapter;
    FirebaseRecyclerOptions<Interested_Model> options;
    String data;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__interested_list);

        data=user_login.getActivityInstance().getData();
        recViewb = findViewById(R.id.intersted_RecView);
        back = findViewById(R.id.backinter);

        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(this.getApplicationContext()));
        recViewb.setLayoutManager(linearLayoutManager);

        options = new FirebaseRecyclerOptions.Builder<Interested_Model>().setQuery(FirebaseDatabase.getInstance().getReference().child(firebaseChild).
                orderByChild("userID").equalTo(data), Interested_Model.class).build();

        adapter = new adapter_interested(options);
        recViewb.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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