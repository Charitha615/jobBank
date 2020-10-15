package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Company_Profile extends AppCompatActivity {

    ImageView img_bookmark;
    TextView cName;
    String data,data2;
    modelBookmarks bookmark;
    DatabaseReference dbref;
    long maxid = 0;

    TextView in_cName,in_cVision,in_cMission,in_cEmps,in_cDepts,in_cAddress,in_cPhone,in_cEmail;
    Button btn_edit_prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__profile);

        img_bookmark = findViewById(R.id.bookmark);
        cName = findViewById(R.id.company_name_profile);
        data = "e";
                //user_login.getActivityInstance().getData();
        data2 = company_login.getActivityInstance().getData();
        bookmark = new modelBookmarks();

        btn_edit_prof = findViewById(R.id.edit_com_profile);

        in_cName = findViewById(R.id.company_name_profile);
        in_cVision = findViewById(R.id.textVsadswwiew);
        in_cMission = findViewById(R.id.textdsaVsadswwiew);
        in_cEmps = findViewById(R.id.teeawde);
        in_cDepts = findViewById(R.id.tewweawde);
        in_cAddress = findViewById(R.id.address);
        in_cPhone = findViewById(R.id.mobile);
        in_cEmail = findViewById(R.id.email___);

        //dbref = FirebaseDatabase.getInstance().getReference().child("Users/" + "sample");


        dbref = FirebaseDatabase.getInstance().getReference().child("Users/" +"sample");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    {
                        in_cName.setText(dataSnapshot.child("name").getValue().toString());
                        in_cVision.setText(dataSnapshot.child("vision").getValue().toString());
                        in_cMission.setText(dataSnapshot.child("mission").getValue().toString());
                        in_cEmps.setText(dataSnapshot.child("employees").getValue().toString());
                        in_cDepts.setText(dataSnapshot.child("departments").getValue().toString());
                        in_cAddress.setText(dataSnapshot.child("address").getValue().toString());
                        in_cPhone.setText(dataSnapshot.child("phone").getValue().toString());
                        in_cEmail.setText(dataSnapshot.child("email").getValue().toString());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Can not Find", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        Intent i = getIntent();

        String nameC = i.getStringExtra("ComName");
        String visionC = i.getStringExtra("ComVision");
        String missionC = i.getStringExtra("ComMission");
        String empsC = i.getStringExtra("ComEmps");
        String deptsC = i.getStringExtra("ComDepts");
        String addressC = i.getStringExtra("ComAddress");
        String phoneC = i.getStringExtra("ComPhone");
        String emailC = i.getStringExtra("ComEmail");

        in_cName.setText(String.valueOf(nameC));
        in_cVision.setText(String.valueOf(visionC));
        in_cMission.setText(String.valueOf(missionC));
        in_cEmps.setText(String.valueOf(empsC));
        in_cDepts.setText(String.valueOf(deptsC));
        in_cAddress.setText(String.valueOf(addressC));
        in_cPhone.setText(String.valueOf(phoneC));
        in_cEmail.setText(String.valueOf(emailC));

        btn_edit_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), company_profile_edit.class);
                startActivity(intent);
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference().child("UserBookmarks");

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

        img_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbref = FirebaseDatabase.getInstance().getReference().child("UserBookmarks");
                String childd = (String.valueOf(data +"_COUNT_"+(maxid+1)));
                dbref.child(childd).setValue(bookmark);

                bookmark.setCompanyName(cName.getText().toString().trim());
                bookmark.setUserID(data);

                Toast.makeText(getApplicationContext(),"You Bookmarked this Page",Toast.LENGTH_SHORT).show();
            }
        });
    }
}