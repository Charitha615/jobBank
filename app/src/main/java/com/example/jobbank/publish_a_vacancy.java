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
    EditText  description, qualification, department, yearsOfExp, ageLimit, jobType, closingDate, jobTitle, companyName, vacancyid;
    Button publishBtn;
    DatabaseReference dbRef;
    pubVacancy std;
    private FirebaseUser publishedID;
    ImageView back;
    pubVacancy pubVacancy_;


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
        vacancyid = findViewById(R.id.editVacancyID);

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

//                Intent i = new Intent(getApplicationContext(), view_published_vacancies.class);
//                startActivity(i);

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
                    else if(TextUtils.isEmpty(vacancyid.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Closing Date",Toast.LENGTH_LONG).show();


                    else
                    {
                        //saving years of experience as an Integer value
                        int result = Integer.parseInt(yearsOfExp.getText().toString());

                        //getting age limit into a integer
                        int value = Integer.parseInt(ageLimit.getText().toString().trim());

                        //Passing a parameter to the validExp function
                        String result2 = Check_Experience(result);
                        String value2 = CheckAge(value);

                        if (result2 == "Invalid"){
                            Toast.makeText(getApplicationContext(),"Invalid Years Of Experience",Toast.LENGTH_SHORT).show();
                        }

                        else if (value2 == "Error"){
                            Toast.makeText(getApplicationContext(),"Please recheck age Limit",Toast.LENGTH_SHORT).show();
                        }
                        else {

                            pubVacancy_.setJobTitle(jobTitle.getText().toString().trim()); //Trim is Remove the Spaces in text fields
                            pubVacancy_.setDescription(description.getText().toString().trim());
                            pubVacancy_.setQualification(qualification.getText().toString().trim());
                            pubVacancy_.setDepartment(department.getText().toString().trim()); //Trim is Remove the Spaces in text fields
                            pubVacancy_.setYrsOfExp(yearsOfExp.getText().toString().trim());
                            pubVacancy_.setAgeLimit(ageLimit.getText().toString().trim());
                            pubVacancy_.setJobType(jobType.getText().toString().trim()); //Trim is Remove the Spaces in text fields
                            pubVacancy_.setClosingDate(closingDate.getText().toString().trim());
                            pubVacancy_.setCompanyName(companyName.getText().toString().trim());
                            pubVacancy_.setVacancyId(vacancyid.getText().toString().trim());
                            pubVacancy_.setPublishedDate(pubdate);
                            dbRef.child(vacancyid.getText().toString().trim()).setValue(pubVacancy_);
                            Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                            clearBox();
                            pubVacancy_.setPublishedDate(pubdate);
                            pubVacancy_.setPublishedID(String.valueOf(pubVacancy_));
                            //dbRef.push().setValue(pubVacancy_);


                            Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                            clearBox();

                            Intent i = new Intent(getApplicationContext(), view_published_vacancies.class);
                            startActivity(i);
                        }
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
                vacancyid.setText("");
            }



        });



    }


    //function to check years of experience
    public static String Check_Experience(int Exp){
        if(Exp >= 80){
            //Toast.makeText("Invalid Years Of Experience",Toast.LENGTH_SHORT).show();
            //Exp = "Invalid Years Of Experience";
            return "Invalid";
        }
        else
            return "Valid";

    }

    public static String CheckAge(int ageLimit1){
        if(ageLimit1 >= 70){
            return "Error";
        }
        else
            return "Valid";
    }
}