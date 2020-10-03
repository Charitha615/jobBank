package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class publish_a_vacancy extends AppCompatActivity {

    //Declaring
    EditText  description, qualification, department, yearsOfExp, ageLimit, jobType, closingDate, jobTitle;
    Button publishBtn;
    DatabaseReference dbRef;
    pubVacancy std;
    private FirebaseUser publishedID;

    String vacancyNo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_a_vacancy);

        jobTitle = findViewById(R.id.editJobTitle);
        description = findViewById(R.id.multilineDescription);
        qualification = findViewById(R.id.QualificationEditText);
        department = findViewById(R.id.departmentEdit);
        yearsOfExp = findViewById(R.id.yearsOfExEdit);
        ageLimit = findViewById(R.id.ageLimit);
        jobType = findViewById(R.id.jobTypeEdit);
        closingDate = findViewById(R.id.editClosingDate);

        publishBtn = findViewById(R.id.publishBtn);

        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dbRef = FirebaseDatabase.getInstance().getReference().child("Publish_Vacancy");

                getUserData();

                try {
                    if(TextUtils.isEmpty(jobTitle.getText().toString())) //Check ID Is Empty
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
//                        std.setJobTitle(jobTitle.getText().toString().trim()); //Trim is Remove the Spaces in text fields
//                        std.setDescription(description.getText().toString().trim());
//                        std.setQualification(qualification.getText().toString().trim());
//                        std.setDepartment(department.getText().toString().trim()); //Trim is Remove the Spaces in text fields
//                        std.setYrsOfExp(yearsOfExp.getText().toString().trim());
//                        std.setAgeLimit(ageLimit.getText().toString().trim());
//                        std.setJobType(jobType.getText().toString().trim()); //Trim is Remove the Spaces in text fields
//                        std.setClosingDate(closingDate.getText().toString().trim());
//                        dbRef.child(jobTitle.getText().toString().trim()).setValue(std);
//                        Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                        //clearBox();

                    }
                }
                catch (NumberFormatException ex){
                    Toast.makeText(getApplicationContext(),"Error Publishing Vacancy",Toast.LENGTH_SHORT).show();
                }
            }


            /*private void clearBox()
            {
                jobtitle.setText("");
                description.setText("");
                qualification.setText("");
                department.setText("");
                yearsOfExp.setText("");
                ageLimit.setText("");
                jobType.setText("");
                closingDate.setText("");
            }*/


            public void getUserData(){

                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");


                //globally assigning values
                //String descrip,qualifi,dep,yrs,age,jobt,closingD;

                String title = jobTitle.getText().toString();
                String descrip = description.getText().toString();
                String qualifi = qualification.getText().toString();
                String dep = department.getText().toString();
                String yrs = yearsOfExp.getText().toString();
                String age = ageLimit.getText().toString();
                String jobt = jobType.getText().toString();
                String closingD = closingDate.getText().toString();
                String pubdate = currentDate.format(calendar.getTime());
                String publishedID;
                String CompanyName;

//                for(String vacancyNo = null){
//                    vacancyNo = vacancyNo + 1;
//                }
                //String vacancyNo = "0";


                final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("PublishVacancy");

                //dbRef = FirebaseDatabase.getInstance().getReference().child("Publish_Vacancy");



                HashMap<String, Object> UserDatamap = new HashMap<>();

                    UserDatamap.put("JobTitle", title);
                    UserDatamap.put("Description", descrip);
                    UserDatamap.put("Qualification",  qualifi);
                    UserDatamap.put("publishedDate",  pubdate);
                    UserDatamap.put("Department",  dep);
                    UserDatamap.put("Years_Of_Experience",  yrs);
                    UserDatamap.put("AgeLimit",  age);
                    UserDatamap.put("JobType",  jobt);
                    UserDatamap.put("ClosingDate",  closingD);
                    //UserDatamap.put("PublishedID", publishedID);
                    //UserDatamap.put("uid",uid.getUid());



                //String vacancyNo = null;
                vacancyNo = vacancyNo + 1;
                dbRef.child(vacancyNo).updateChildren(UserDatamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                              /*  Log.i("t","success");

                                dbRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String sn = snapshot.child("JobTitle").getValue().toString();
                                        Log.i("te","got values");
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });*/
                            }

                        }
                    });



            }
        });


    }
}