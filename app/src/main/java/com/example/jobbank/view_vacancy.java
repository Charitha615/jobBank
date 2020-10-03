/*
package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class view_vacancy extends AppCompatActivity {

    //declaring the publishedID button which is called once the view_published_vacancy vacancy is clicked
    Button PublishedID;
    EditText description, qualification, department, yearsOfExp, ageLimit, jobType, closingDate;
    DatabaseReference dbRef;
    //ViewVacancy std;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vacancy);

        description = findViewById(R.id.multilineDescription);
        qualification = findViewById(R.id.QualificationEditText);
        department = findViewById(R.id.departmentEdit);
        yearsOfExp = findViewById(R.id.yearsOfExEdit);
        closingDate = findViewById(R.id.editClosingDate);

//////////////////////////////////////////////
        PublishedID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(in_Name.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Check You Name", Toast.LENGTH_LONG).show();

                else{
                    dbRef = FirebaseDatabase.getInstance().getReference().child("Test/" + in_Name.getText().toString().trim());
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            if (dataSnapshot.hasChildren()) {


                                {
                                    description.setText(dataSnapshot.child("id").getValue().toString());
                                    qualification.setText(dataSnapshot.child("name").getValue().toString());
                                    department.setText(dataSnapshot.child("address").getValue().toString());
                                    yearsOfExp.setText(dataSnapshot.child("contact").getValue().toString());
                                    ageLimit.setText(dataSnapshot.child("id").getValue().toString());

                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Can not Find", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });



        ////////////////////////////////////////////



    }
}*/
