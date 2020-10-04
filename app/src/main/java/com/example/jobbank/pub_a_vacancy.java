package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class pub_a_vacancy extends AppCompatActivity {

    EditText jobTitle,des,quali,department,xp,age_limit,job_type,closing_date;
    Button publish_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub_a_vacancy);

        jobTitle = findViewById(R.id.editJobTitle18);
        des =findViewById(R.id.multilineDescription18);
        quali=findViewById(R.id.QualificationEditText18);
        department=findViewById(R.id.departmentEdit18);
        xp=findViewById(R.id.yearsOfExEdit18);
        age_limit=findViewById(R.id.ageLimit18);
        job_type=findViewById(R.id.jobTypeEdit18);
        closing_date=findViewById(R.id.editClosingDate18);




    }
}