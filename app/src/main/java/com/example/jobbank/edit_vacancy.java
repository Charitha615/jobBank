package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
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
    FirebaseRecyclerOptions<pubVacancy> options;
    myadapter.RecyclerViewClickListener listener;


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
        final String vid = i.getStringExtra("vID");


        description=findViewById(R.id.multilineDescription1);
        qualification=findViewById(R.id.QualificationEditText1);
        department=findViewById(R.id.departmentEdit1);
        yrsOfEx=findViewById(R.id.yearsOfExEdit1);
        ageLimit=findViewById(R.id.ageLimit1);
        JobType=findViewById(R.id.jobTypeEdit1);
        closingDate=findViewById(R.id.editClosingDate1);
        savePreview=findViewById(R.id.savebtn);


        description.setText(String.valueOf(descrip));
        qualification.setText(String.valueOf(qualifi));
        department.setText(String.valueOf(dep));
        yrsOfEx.setText(String.valueOf(yrs_ofEx));
        ageLimit.setText(String.valueOf(agel));
        JobType.setText(String.valueOf(jType));
        closingDate.setText(String.valueOf(cDate));

        savePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference();

                if (TextUtils.isEmpty(description.getText().toString()) || TextUtils.isEmpty(qualification.getText().toString()) || TextUtils.isEmpty(department.getText().toString()) || TextUtils.isEmpty(yrsOfEx.getText().toString()) || TextUtils.isEmpty(ageLimit.getText().toString()) || TextUtils.isEmpty(JobType.getText().toString()) || TextUtils.isEmpty(closingDate.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_LONG).show();
                else {
                    dbRef.child("PublishVacancy/" + vid + "/description").setValue(description.getText().toString());
                    dbRef.child("PublishVacancy/" + vid + "/qualification").setValue(qualification.getText().toString());
                    dbRef.child("PublishVacancy/" + vid + "/department").setValue(department.getText().toString());
                    dbRef.child("PublishVacancy/" + vid + "/yrsOfExp").setValue(yrsOfEx.getText().toString());
                    dbRef.child("PublishVacancy/" + vid + "/ageLimit").setValue(ageLimit.getText().toString());
                    dbRef.child("PublishVacancy/" + vid + "/jobType").setValue(JobType.getText().toString());
                    dbRef.child("PublishVacancy/" + vid + "/closingDate").setValue(closingDate.getText().toString());


                    Toast.makeText(getApplicationContext(), "Vacancy Updated Successfully", Toast.LENGTH_SHORT).show();
                    clearBox();
                    Intent in = new Intent(getApplicationContext(), view_published_vacancies.class);
                    startActivity(in);
                }
            }

            private void clearBox()
            {
                description.setText("");
                qualification.setText("");
                department.setText("");
                yrsOfEx.setText("");
                ageLimit.setText("");
                JobType.setText("");
                closingDate.setText("");
            }
        });
    }
}