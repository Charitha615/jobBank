package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class company_profile_edit extends AppCompatActivity {

    EditText ed_name,ed_vision,ed_mission,ed_emps,ed_depts,ed_address,ed_phone,ed_email;
    Button btn_save_editProfile;
    DatabaseReference dbRef;
    model_comProfile comProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile_edit);

        ed_name = findViewById(R.id.com_name_editTxt);
        ed_vision = findViewById(R.id.com_vision_editTxt);
        ed_mission = findViewById(R.id.com_mission_editTxt);
        ed_emps = findViewById(R.id.noOfEmps_editTxt);
        ed_depts = findViewById(R.id.noOfDepts_editTxt);
        ed_address = findViewById(R.id.address_editTxt);
        ed_phone = findViewById(R.id.phone_editTxt);
        ed_email = findViewById(R.id.email_editTxt);

        btn_save_editProfile = findViewById(R.id.btn_edit_profile_save);

        //dbRef = FirebaseDatabase.getInstance().getReference().child("CompanyDetails");

        comProf = new model_comProfile();
        btn_save_editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Companies");

                comProf.setComName(ed_name.getText().toString().trim());
                comProf.setComVision(ed_vision.getText().toString().trim());
                comProf.setComMission(ed_mission.getText().toString().trim());
                comProf.setComEmp(ed_emps.getText().toString().trim());
                comProf.setComDept(ed_depts.getText().toString().trim());
                comProf.setComAddress(ed_address.getText().toString().trim());
                comProf.setComPhone(ed_phone.getText().toString().trim());
                comProf.setComEmail(ed_email.getText().toString().trim());

                dbRef.child(ed_name.getText().toString().trim()).setValue(comProf);
                Toast.makeText(getApplicationContext(),"Successfully Inserted Company Details",Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(getApplicationContext(), Company_Profile.class);
                intent.putExtra("ComName",ed_name.getText().toString());
                intent.putExtra("ComVision",ed_vision.getText().toString());
                intent.putExtra("ComMission",ed_mission.getText().toString());
                intent.putExtra("ComEmps",ed_emps.getText().toString());
                intent.putExtra("ComDepts",ed_depts.getText().toString());
                intent.putExtra("ComAddress",ed_address.getText().toString());
                intent.putExtra("ComPhone",ed_phone.getText().toString());
                intent.putExtra("ComEmail",ed_email.getText().toString());
                startActivity(intent);

                clearBox();
            }
        });
    }

    private void clearBox()
    {
        ed_name.setText("");
        ed_vision.setText("");
        ed_mission.setText("");
        ed_emps.setText("");
        ed_depts.setText("");
        ed_address.setText("");
        ed_phone.setText("");
        ed_email.setText("");
    }
}