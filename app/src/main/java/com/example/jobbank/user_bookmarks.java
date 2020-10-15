package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class user_bookmarks extends AppCompatActivity {

    String firebaseChild ="UserBookmarks";
    RecyclerView recViewb;
    adapter_bookmarks adapter;
    FirebaseRecyclerOptions<modelBookmarks> options;
    String data;
    com.google.android.material.floatingactionbutton.FloatingActionButton addNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bookmarks);

        data=user_login.getActivityInstance().getData();
        recViewb = findViewById(R.id.bookmarksRecView);
        addNew = findViewById(R.id.add);

        LinearLayoutManager linearLayoutManager = (new LinearLayoutManager(this.getApplicationContext()));
        recViewb.setLayoutManager(linearLayoutManager);

        options = new FirebaseRecyclerOptions.Builder<modelBookmarks>().setQuery(FirebaseDatabase.getInstance().getReference().child(firebaseChild).
                        orderByChild("userID").equalTo(data), modelBookmarks.class).build();

        adapter = new adapter_bookmarks(options);
        recViewb.setAdapter(adapter);


        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), user_home.class);
                startActivity(i);
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