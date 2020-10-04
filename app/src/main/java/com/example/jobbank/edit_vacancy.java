package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class edit_vacancy extends AppCompatActivity {

    EditText description, qualification, department, yrsOfEx, ageLimit, JobType, closingDate;
    DatabaseReference dbRef;
    pubVacancy std;
    Button savePreview;
    TextView department1, yrsOfExp, ageLimit1, JobType1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vacancy);

        Intent i =getIntent();


        String descrip = i.getStringExtra("Description01");
        String qualifi = i.getStringExtra("qualification01");
        String dep = i.getStringExtra("department01");
        String agel = i.getStringExtra("ageLimit01");
        String yrs_ofEx = i.getStringExtra("Years_Of_Exp01");
        String jType = i.getStringExtra("JobType01");
        String cDate = i.getStringExtra("ClosingDate01");

        description=findViewById(R.id.multilineDescription1);
        qualification=findViewById(R.id.QualificationEditText1);
        department=findViewById(R.id.departmentEdit1);
        yrsOfEx=findViewById(R.id.yearsOfExEdit1);
        ageLimit=findViewById(R.id.ageLimit1);
        JobType=findViewById(R.id.jobTypeEdit1);
        closingDate=findViewById(R.id.editClosingDate1);
        savePreview=findViewById(R.id.savebtn);

        savePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}