package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class publish_a_vacancy extends AppCompatActivity {

    //Declaring
    EditText  description, qualification, department, yearsOfExp, ageLimit, jobType, closingDate, jobTitle, companyName;
    Button publishBtn;
    DatabaseReference dbRef;
    pubVacancy std;
    private FirebaseUser publishedID;
    ImageView back;

    //pubVacancy vacancyNo;
    pubVacancy pubVacancy_;

     //String vacancyNo= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_a_vacancy);

        companyName = (EditText) findViewById(R.id.editCompanyName);
        jobTitle =(EditText)findViewById(R.id.editJobTitle);
        description =(EditText) findViewById(R.id.multilineDescription);
        qualification = (EditText)findViewById(R.id.QualificationEditText);
        department = (EditText)findViewById(R.id.departmentEdit);
        yearsOfExp =(EditText) findViewById(R.id.yearsOfExEdit);
        ageLimit =(EditText) findViewById(R.id.ageLimit);
        jobType =(EditText) findViewById(R.id.jobTypeEdit);
        closingDate = (EditText)findViewById(R.id.editClosingDate);
        back = findViewById(R.id.back_imageView);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pubVacancy_ = new pubVacancy();


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM.dd.yyyy");
        final String pubdate = currentDate.format(calendar.getTime());




          dbRef = FirebaseDatabase.getInstance().getReference().child("PublishVacancy");


        publishBtn = findViewById(R.id.publishBtn);

        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dbRef = FirebaseDatabase.getInstance().getReference().child("Publish_Vacancy");

                //getUserData();

                try {
                    if(TextUtils.isEmpty(companyName.getText().toString())) //Check ID Is Empty
                        Toast.makeText(getApplicationContext(),"Error Company Name",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(jobTitle.getText().toString())) //Check ID Is Empty
                        Toast.makeText(getApplicationContext(),"Error Job Title",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(description.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Description",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(qualification.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in qualification",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(department.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Department",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(yearsOfExp.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Years of Experience",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(ageLimit.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Age Limit",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(jobType.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Job Type",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(closingDate.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Closing Date",Toast.LENGTH_LONG).show();


                    else
                    {
                        pubVacancy_.setJobTitle(jobTitle.getText().toString().trim()); //Trim is Remove the Spaces in text fields
                        pubVacancy_.setDescription(description.getText().toString().trim());
                        pubVacancy_.setQualification(qualification.getText().toString().trim());
                        pubVacancy_.setDepartment(department.getText().toString().trim()); //Trim is Remove the Spaces in text fields
                        pubVacancy_.setYrsOfExp(yearsOfExp.getText().toString().trim());
                        pubVacancy_.setAgeLimit(ageLimit.getText().toString().trim());
                        pubVacancy_.setJobType(jobType.getText().toString().trim()); //Trim is Remove the Spaces in text fields
                        pubVacancy_.setClosingDate(closingDate.getText().toString().trim());
                        pubVacancy_.setCompanyName(companyName.getText().toString().trim());
                        //pubVacancy_.setCompanyAddress("");
                        pubVacancy_.setPublishedDate(pubdate);
                        pubVacancy_.setPublishedID(String.valueOf(pubVacancy_));
                        //dbRef.child(jobTitle.getText().toString().trim()).setValue(std);
                        dbRef.push().setValue(pubVacancy_);




                        Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                        clearBox();

                    }
                }
                catch (NumberFormatException ex){
                    Toast.makeText(getApplicationContext(),"Error Publishing Vacancy",Toast.LENGTH_SHORT).show();
                }
            }


            private void clearBox()
            {
                companyName.setText("");
                jobTitle.setText("");
                description.setText("");
                qualification.setText("");
                department.setText("");
                yearsOfExp.setText("");
                ageLimit.setText("");
                jobType.setText("");
                closingDate.setText("");
            }


//            public void getUserData(){
//
//                Calendar calendar = Calendar.getInstance();
//
//                SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
//
//
//                //globally assigning values
//                //String descrip,qualifi,dep,yrs,age,jobt,closingD;
//
//                String title = jobTitle.getText().toString();
//                String descrip = description.getText().toString();
//                String qualifi = qualification.getText().toString();
//                String dep = department.getText().toString();
//                String yrs = yearsOfExp.getText().toString();
//                String age = ageLimit.getText().toString();
//                String jobt = jobType.getText().toString();
//                String closingD = closingDate.getText().toString();
//                String pubdate = currentDate.format(calendar.getTime());
//                String publishedID;
//                String CompanyName;
//
////                for(String vacancyNo = null){
////                    vacancyNo = vacancyNo + 1;
////                }
//                //String vacancyNo = "0";
//
//
////                               final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("PublishVacancy");
//
//                //dbRef = FirebaseDatabase.getInstance().getReference().child("Publish_Vacancy");
//
//
//
//                HashMap<String, Object> UserDatamap = new HashMap<>();
//
//                    UserDatamap.put("jobTitle", title);
//                    UserDatamap.put("description", descrip);
//                    UserDatamap.put("qualification",  qualifi);
//                    UserDatamap.put("publishedDate",  pubdate);
//                    UserDatamap.put("department",  dep);
//                    UserDatamap.put("yearsOfExp",  yrs);
//                    UserDatamap.put("ageLimit",  age);
//                    UserDatamap.put("jobType",  jobt);
//                    UserDatamap.put("closingDate",  closingD);
//                    //UserDatamap.put("PublishedID", publishedID);
//                    //UserDatamap.put("uid",uid.getUid());
//                     //UserDatamap.put("vacancyNo",vacancyNo);
//
//
//
////                //String vacancyNo = null;
////                Random generator = new Random();
////                StringBuilder randomStringBulder = new StringBuilder();
////                vacancyNo = randomStringBulder.toString();
//
//                //vacancyNo = vacancyNo + 1;
//         /*       dbRef.child(vacancyNo).updateChildren(UserDatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                              *//*  Log.i("t","success");
//
//                                dbRef.addValueEventListener(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                        String sn = snapshot.child("JobTitle").getValue().toString();
//                                        Log.i("te","got values");
//                                    }
//
//                                    @Override
//                                    public void onCancelled(@NonNull DatabaseError error) {
//
//                                    }
//                                });*//*
//                            }
//
//                        }
//                    });*/
//
//
//
//            }
        });



    }
}