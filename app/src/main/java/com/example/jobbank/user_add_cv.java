package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class user_add_cv extends AppCompatActivity {

    EditText first_name, last_name, email, mobile, work_ex, age, description, qualification;
    String first_name_in, last_name_in, email_in, mobile_in, work_ex_in, age_in, description_in, qualification_in,company_name_in,com_title_in,closing_date_in;
    EditText qualification1,description1;
    //String qulafication10,description10;
    TextView company_name_textView,job_title_textView,closing_date_textView,qualification_textView,job_type_textView,description_textView,age_limit_textView;
    Button add_cv_btn;

    ImageButton interested;
    DatabaseReference dbref;
    long maxid = 0;
    String data2;

    Interested_Model inter_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_cv);

        interested = findViewById(R.id.star_btn_01);

        add_cv_btn = findViewById(R.id.cv_submit_Applicant_btn);

        first_name = findViewById(R.id.cv_full_name_input);
        last_name = findViewById(R.id.cv_last_name_input);
        email = findViewById(R.id.cv_email_input);
        mobile = findViewById(R.id.cv_mobile_input);
        work_ex = findViewById(R.id.cv_work_ex_input);
        age = findViewById(R.id.cv_age_input);
        description1 = findViewById(R.id.cv_description_input);
        qualification1 = findViewById(R.id.cv_qualification_input);




        ///////////////////////////////////////////////////////////////////////////////////////////////////

        job_title_textView = findViewById(R.id.job_Title__);
        company_name_textView = findViewById(R.id.com_address_in);
        qualification_textView = findViewById(R.id.qualifications_in);
        closing_date_textView = findViewById(R.id.closing_date_in);
        job_type_textView = findViewById(R.id.job_type);
        description_textView = findViewById(R.id.com_des_);
        age_limit_textView = findViewById(R.id.age_limit);

//        age_limit_textView = findViewById(R.id.age_limit);
//        age_limit_textView = findViewById(R.id.age_limit);

        Intent intent = getIntent();

        final String COM_NAMEIN = intent.getStringExtra("COMPANY_NAME");
        final String JOB_TITLEIN = intent.getStringExtra("JOB_TITLE");
        String QUALIFICATIONIN = intent.getStringExtra("QUALIFICATION1");
        final String AGE_LIMITIN = intent.getStringExtra("AGE_LIMIT");
        String DESCRIPTIONIN = intent.getStringExtra("DESCRIPTION1");
        String CLOSING_DATEIN = intent.getStringExtra("CLOSING_DATE");
        String JOB_TYPEIN = intent.getStringExtra("JOB_TYPE");



        job_title_textView.setText(JOB_TITLEIN);
        company_name_textView.setText(COM_NAMEIN);
        qualification_textView.setText(QUALIFICATIONIN);
        closing_date_textView.setText(CLOSING_DATEIN);
        job_type_textView.setText(JOB_TYPEIN);
        description_textView.setText(DESCRIPTIONIN);
        age_limit_textView.setText(AGE_LIMITIN);





/////////////////////////////////////////////Interested list//////////////////////////////////////////////////////
        inter_model = new Interested_Model();
        dbref = FirebaseDatabase.getInstance().getReference().child("User_Interested");

        data2=user_login.getActivityInstance().getData();
        //data2 = "4OruyYujvbU3jdOOtlgOwN3pbpp1";
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

        interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inter_model.setUserID(data2);
                inter_model.setCompanyName(COM_NAMEIN);
                inter_model.setJobTitle(JOB_TITLEIN);

                String childd = (String.valueOf(data2 +"_COUNT_"+(maxid+1))); //GET UPLODER ID
               dbref.child(childd).setValue(inter_model);


                Toast.makeText(getApplicationContext(),"Add to Interested List",Toast.LENGTH_LONG).show();


            }
        });






        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //Edit data from cv preview page

        Intent p = getIntent();
        first_name.setText(p.getStringExtra("FIRST_NAME")) ; //using put Extra
        last_name.setText(p.getStringExtra("LAST_NAME")); //using put Extra
        email.setText(p.getStringExtra("EMAIL"));  //using put Extra
        mobile.setText(p.getStringExtra("MOBILE")); //using put Extra
        work_ex.setText(p.getStringExtra("WORK_EX")); //using put Extra
        age.setText(p.getStringExtra("AGE")); //using put Extra
        description1.setText(p.getStringExtra("DESCRIPTION")); //using put Extra
        qualification1.setText(p.getStringExtra("QUALIFICATION")); //using put Extra


        add_cv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if(TextUtils.isEmpty(first_name.getText().toString())) //Check ID Is Empty
                        Toast.makeText(getApplicationContext(),"Invalid First Name",Toast.LENGTH_LONG).show();

                    else if(TextUtils.isEmpty(last_name.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Invalid Last Name",Toast.LENGTH_LONG).show();

                    else if(TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_LONG).show();

                    else if(TextUtils.isEmpty(mobile.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Invalid Mobile",Toast.LENGTH_LONG).show();

                    else if(TextUtils.isEmpty(work_ex.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Invalid Working Experience",Toast.LENGTH_LONG).show();

                    else if(TextUtils.isEmpty(age.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Invalid Age",Toast.LENGTH_LONG).show();

                    else if(TextUtils.isEmpty(description1.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Invalid Description",Toast.LENGTH_LONG).show();

                    else if(TextUtils.isEmpty(qualification1.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Invalid Qualification",Toast.LENGTH_LONG).show();

                    else {

                        //saving age as an Integer value
                        int result = Integer.parseInt(age.getText().toString().trim());
                        int ageLimit = Integer.parseInt(AGE_LIMITIN);

                        //Passing a parameter to the CheckAge function
                        String result2 = CheckAge(result, ageLimit);
                        if (result2 == "Invalid") {
                            Toast.makeText(getApplicationContext(), "Invalid Age", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            Intent i = new Intent(getApplicationContext(), user_cv_preview.class);

                            first_name_in = first_name.getText().toString();
                            last_name_in = last_name.getText().toString();
                            email_in = email.getText().toString();
                            mobile_in = mobile.getText().toString();
                            work_ex_in = work_ex.getText().toString();
                            age_in = age.getText().toString();
                            description_in = description1.getText().toString();
                            qualification_in = qualification1.getText().toString();
                            company_name_in = company_name_textView.getText().toString();
                            com_title_in = job_title_textView.getText().toString();
                            closing_date_in = closing_date_textView.getText().toString();


                            i.putExtra("FIRST_NAME", first_name_in); //using put Extra
                            i.putExtra("LAST_NAME", last_name_in); //using put Extra
                            i.putExtra("EMAIL", email_in);
                            i.putExtra("MOBILE", mobile_in);
                            i.putExtra("WORK_EX", work_ex_in);
                            i.putExtra("AGE", age_in);
                            i.putExtra("DESCRIPTION14", description_in);
                            i.putExtra("QUALIFICATION14", qualification_in);
                            i.putExtra("COM_NAME", company_name_in);
                            i.putExtra("COM_ADDRESS", com_title_in);
                            i.putExtra("CLOSING_DATE", closing_date_in);


                            startActivity(i);

                        }

                    }
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getApplicationContext(),"Invalid Contact Number",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    //checking whether the age is greater than the age limit using a function
   public static String CheckAge(int age, int limit){
        if(age > limit){
            return "Invalid";
        }
        else
            return "Valid";
    }


}