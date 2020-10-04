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

public class testCrud extends AppCompatActivity {

    EditText in_position,in_company,in_closing,in_stts;
    EditText in_fname,in_lname,in_age,in_ex;
    EditText in_email,in_phone,in_des,in_qua;

    Button btn_Save,btn_Show,btn_Update,btn_Delete;
    Test std; // create a object for call the java(Test) Class
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_crud);

        in_position = findViewById(R.id.position_input);
        in_company = findViewById(R.id.company_input);
        in_closing = findViewById(R.id.closing_Date_input);
        in_stts = findViewById(R.id.vacancy_Status_input);
        in_fname = findViewById(R.id.fNameFill_input);
        in_lname = findViewById(R.id.lNameFill_input);
        in_email = findViewById(R.id.EmailAddressFill_input);
        in_phone = findViewById(R.id.PhoneFill_input);
        in_ex = findViewById(R.id.exFill_input);
        in_age = findViewById(R.id.ageFill_input);
        in_des = findViewById(R.id.desFill_input);
        in_qua = findViewById(R.id.quaFill_input);

        btn_Save = findViewById(R.id.save_btn);
        btn_Show = findViewById(R.id.show_btn);
        btn_Update = findViewById(R.id.update_btn);
        btn_Delete = findViewById(R.id.delete_btn);

        std = new Test(); // call the Java(Test) Class


        /////////////////////////////////////////Insert /////////////////////////////////////////

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbref = FirebaseDatabase.getInstance().getReference().child("vacancyTest");

                try {

                    if(TextUtils.isEmpty(in_fname.getText().toString())) //Check ID Is Empty
                        Toast.makeText(getApplicationContext(),"Check Entered ID",Toast.LENGTH_LONG).show();

                    else if(TextUtils.isEmpty(in_lname.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Check You Name",Toast.LENGTH_LONG).show();

                    else if(TextUtils.isEmpty(in_email.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Check You Address",Toast.LENGTH_LONG).show();

                    else if(TextUtils.isEmpty(in_phone.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Check You Contact Number",Toast.LENGTH_LONG).show();


                        //Use Java Class

                    else
                    {
                        std.setPosition(in_position.getText().toString().trim()); //Trim is Remove the Spaces in text filds
                        std.setCompany(in_company.getText().toString().trim());
                        std.setClosingDate(in_closing.getText().toString().trim());
                        std.setVacancyStatus(in_stts.getText().toString().trim());

                        std.setFname(in_fname.getText().toString().trim());
                        std.setLname(in_lname.getText().toString().trim());
                        std.setEmail(in_email.getText().toString().trim());
                        std.setPhone(in_phone.getText().toString().trim());

                        std.setExperience(in_ex.getText().toString().trim());
                        std.setAge(in_age.getText().toString().trim());
                        std.setDescription(in_des.getText().toString().trim());
                        std.setQualifications(in_qua.getText().toString().trim());

                        dbref.child(in_fname.getText().toString().trim()).setValue(std);
                        Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                        clearBox();
                    }
                }
                catch (NumberFormatException e)
                {
                    Toast.makeText(getApplicationContext(),"Invalid Contact Number",Toast.LENGTH_SHORT).show();
                }


            }
        });

        /////////////////////////////////////////Retrieve/////////////////////////////////////////

        btn_Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(in_fname.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Check You Name", Toast.LENGTH_LONG).show();

                else{
                    dbref = FirebaseDatabase.getInstance().getReference().child("vacancyTest/" + in_fname.getText().toString().trim());
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            if (dataSnapshot.hasChildren()) {


                                {
                                    in_position.setText(dataSnapshot.child("position").getValue().toString());
                                    in_company.setText(dataSnapshot.child("company").getValue().toString());
                                    in_closing.setText(dataSnapshot.child("closingDate").getValue().toString());
                                    in_stts.setText(dataSnapshot.child("vacancyStatus").getValue().toString());

                                    in_fname.setText(dataSnapshot.child("fname").getValue().toString());
                                    in_lname.setText(dataSnapshot.child("lname").getValue().toString());
                                    in_email.setText(dataSnapshot.child("email").getValue().toString());
                                    in_phone.setText(dataSnapshot.child("phone").getValue().toString());

                                    in_ex.setText(dataSnapshot.child("experience").getValue().toString());
                                    in_age.setText(dataSnapshot.child("age").getValue().toString());
                                    in_des.setText(dataSnapshot.child("description").getValue().toString());
                                    in_qua.setText(dataSnapshot.child("qualifications").getValue().toString());
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


        /////////////////////////////////////////Update/////////////////////////////////////////

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(in_fname.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Check You Name", Toast.LENGTH_LONG).show();

                else {
                    dbref = FirebaseDatabase.getInstance().getReference();
                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/position").setValue(in_position.getText().toString().trim());
                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/company").setValue(in_company.getText().toString().trim());
                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/closingDate").setValue(in_closing.getText().toString().trim());
                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/vacancyStatus").setValue(in_stts.getText().toString().trim());

                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/fname").setValue(in_fname.getText().toString().trim());
                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/lname").setValue(in_lname.getText().toString().trim());
                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/email").setValue(in_email.getText().toString().trim());
                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/phone").setValue(in_phone.getText().toString().trim());

                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/experience").setValue(in_ex.getText().toString().trim());
                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/age").setValue(in_age.getText().toString().trim());
                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/description").setValue(in_des.getText().toString().trim());
                    dbref.child("vacancyTest/"+in_fname.getText().toString().trim()+"/qualifications").setValue(in_qua.getText().toString().trim());

                    Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_LONG).show();
                    clearBox();
                }
            }
        });

        /////////////////////////////////////////Delete/////////////////////////////////////////

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(in_fname.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Check You Name", Toast.LENGTH_LONG).show();

                else {
                    dbref = FirebaseDatabase.getInstance().getReference("vacancyTest/" + in_fname.getText().toString().trim());
                    dbref.removeValue();
                    Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();
                    clearBox();
                }
            }
        });

    }
    private void clearBox()
    {
        in_position.setText("");
        in_company.setText("");
        in_closing.setText("");
        in_stts.setText("");

        in_fname.setText("");
        in_lname.setText("");
        in_email.setText("");
        in_phone.setText("");

        in_ex.setText("");
        in_age.setText("");
        in_des.setText("");
        in_qua.setText("");

    }
}