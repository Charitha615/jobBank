package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vacancy);

        description=findViewById(R.id.multilineDescription1);
        qualification=findViewById(R.id.QualificationEditText1);
        department=findViewById(R.id.departmentEdit1);
        yrsOfEx=findViewById(R.id.yearsOfExEdit1);
        ageLimit=findViewById(R.id.ageLimit1);
        JobType=findViewById(R.id.jobTypeEdit1);
        closingDate=findViewById(R.id.editClosingDate1);

        savePreview=findViewById(R.id.savebtn);
        //editButton=findViewById(R.id.editBtn);



   //////////////////////////////////////////////////////////////////////////////////////////////////////////


        savePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




          /*          dbRef = FirebaseDatabase.getInstance().getReference().child("PublishVacancy");
                    dbRef.child("editV/"+description.getText().toString().trim()+"/description").setValue(description.getText().toString().trim());
                    dbRef.child("editV/"+qualification.getText().toString().trim()+"/qualification").setValue(qualification.getText().toString().trim());
                    dbRef.child("editV/"+department.getText().toString().trim()+"/department").setValue(department.getText().toString().trim());
                    dbRef.child("editV/"+yrsOfEx.getText().toString().trim()+"/yrsOfExp").setValue(yrsOfEx.getText().toString().trim());
                    dbRef.child("editV/"+ageLimit.getText().toString().trim()+"/ageLimit").setValue(ageLimit.getText().toString().trim());
                    dbRef.child("editV/"+JobType.getText().toString().trim()+"/jobType").setValue(JobType.getText().toString().trim());
                    dbRef.child("editV/"+closingDate.getText().toString().trim()+"/closingDate").setValue(closingDate.getText().toString().trim());
                    Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_LONG).show();
                    //clearBox();*/

            }
        });

     /////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////
//        editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (TextUtils.isEmpty(description.getText().toString()))
//                    Toast.makeText(getApplicationContext(), "Check Your description", Toast.LENGTH_LONG).show();
//
//                else{
//                    dbRef = FirebaseDatabase.getInstance().getReference().child("editV/" + description.getText().toString().trim());
//                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//                            if (dataSnapshot.hasChildren()) {
//
//
//                                {
//                                    description.setText(dataSnapshot.child("id").getValue().toString());
//                                    qualification.setText(dataSnapshot.child("name").getValue().toString());
//                                    department.setText(dataSnapshot.child("address").getValue().toString());
//                                    yrsOfEx.setText(dataSnapshot.child("contact").getValue().toString());
//                                    ageLimit.setText(dataSnapshot.child("id").getValue().toString());
//                                    JobType.setText(dataSnapshot.child("name").getValue().toString());
//                                    closingDate.setText(dataSnapshot.child("address").getValue().toString());
//                                }
//                            } else {
//                                Toast.makeText(getApplicationContext(), "Can not Find", Toast.LENGTH_LONG).show();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });
//                }
//            }
//        });

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        savePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //dbRef = FirebaseDatabase.getInstance().getReference().child("Publish_Vacancy");

                getUserData();

                try {
                    if(TextUtils.isEmpty(description.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Description",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(qualification.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in qualification",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(department.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Department",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(yrsOfEx.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Years of Experience",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(ageLimit.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Age Limit",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(JobType.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Job Type",Toast.LENGTH_LONG).show();
                    else if(TextUtils.isEmpty(closingDate.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Error in Closing Date",Toast.LENGTH_LONG).show();


                    else
                   {
                       /* std.setDescription(description.getText().toString().trim());
                        std.setQualification(qualification.getText().toString().trim());
                        std.setDepartment(department.getText().toString().trim()); //Trim is Remove the Spaces in text fields
                        std.setYrsOfExp(yrsOfEx.getText().toString().trim());
                        std.setAgeLimit(ageLimit.getText().toString().trim());
                        std.setJobType(JobType.getText().toString().trim()); //Trim is Remove the Spaces in text fields
                        std.setClosingDate(closingDate.getText().toString().trim());
                        dbRef.child(jobTitle.getText().toString().trim()).setValue(std);
                        Toast.makeText(getApplicationContext(),"Successfully Inserted",Toast.LENGTH_SHORT).show();
                        //clearBox();*/
                    }
                }
                catch (NumberFormatException ex){
                    Toast.makeText(getApplicationContext(),"Error in Saving",Toast.LENGTH_SHORT).show();
                }

            }

            //getting user data
            public void getUserData(){

                //globally assigning values

                String descrip = description.getText().toString();
                String qualifi = qualification.getText().toString();
                String dep = department.getText().toString();
                String yrs = yrsOfEx.getText().toString();
                String age = ageLimit.getText().toString();
                String jobt = JobType.getText().toString();
                String closingD = closingDate.getText().toString();

                final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("PublishVacancy");

                //DatabaseReference rootref = FirebaseDatabase.getInstance().getReference().child("Des")


                HashMap<String, Object> EditUserData = new HashMap<>();

                EditUserData.put("Description", descrip);
                EditUserData.put("Qualification", qualifi);
                EditUserData.put("Department", dep);
                EditUserData.put("Years_Of_Experience", yrs);
                EditUserData.put("AgeLimit", age);
                EditUserData.put("JobType", jobt);
                EditUserData.put("ClosingDate", closingD);

                dbRef.updateChildren(EditUserData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){}
                    }
                });
            }
        });
    }
}