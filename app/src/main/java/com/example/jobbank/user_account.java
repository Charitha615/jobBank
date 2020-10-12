package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class user_account extends AppCompatActivity {
    
    TextView username,Email,Password;
    String data;

    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        username = findViewById(R.id.username_display);
        Email = findViewById(R.id.email_display);
        Password = findViewById(R.id.passwordDisplay);

        data=user_login.getActivityInstance().getData();


        //username.setText(data);

///////////////////////////////////////////////////////////////////////////////////////////////


            dbref = FirebaseDatabase.getInstance().getReference().child("Users/" +data);
            dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    if (dataSnapshot.hasChildren()) {


                        {
                            username.setText(dataSnapshot.child("username").getValue().toString());
                            Email.setText(dataSnapshot.child("email").getValue().toString());
                            /*in_Address.setText(dataSnapshot.child("address").getValue().toString());
                            in_Contact.setText(dataSnapshot.child("contact").getValue().toString());*/
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Can not Find", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



///////////////////////////////////////////////////////////////////////////////////////////////
    }
}