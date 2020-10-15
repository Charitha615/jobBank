package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Company_Profile extends AppCompatActivity {

    ImageView img_bookmark;
    TextView cName;
    String data;
    modelBookmarks bookmark;
    DatabaseReference dbref;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__profile);

        img_bookmark = findViewById(R.id.bookmark);
        cName = findViewById(R.id.company_name_profile);
        data = "4OruyYujvbU3jdOOtlgOwN3pbpp1";
                //user_login.getActivityInstance().getData();
        bookmark = new modelBookmarks();

        dbref = FirebaseDatabase.getInstance().getReference().child("UserBookmarks");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxid = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        img_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbref = FirebaseDatabase.getInstance().getReference().child("UserBookmarks");
                String childd = (String.valueOf(data +"_COUNT_"+(maxid+1)));
                dbref.child(childd).setValue(bookmark);

                bookmark.setCompanyName(cName.getText().toString().trim());
                bookmark.setUserID(data);

                Toast.makeText(getApplicationContext(),"You Bookmarked this Page",Toast.LENGTH_SHORT).show();
            }
        });
    }
}