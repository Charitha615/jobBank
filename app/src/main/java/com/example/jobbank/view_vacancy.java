
package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jobbank.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class view_vacancy extends AppCompatActivity {

    //declaring the publishedID button which is called once the view_published_vacancy vacancy is clicked
    Button PublishedID;
    Button close, edit, viewList, examine;
    TextView description, qualification, closingDate;
    TextView jobTitle,pub_date_textView;
    DatabaseReference dbRef;
    //ViewVacancy std;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vacancy);


        Intent i = getIntent();
        String description_ = i.getStringExtra("DESCRIPTION");
        String qualificcation_ = i.getStringExtra("QUALIFICATION");
        String closing_Date = i.getStringExtra("CLOSING_DATE");
        String publishedDate = i.getStringExtra("DESCRIPTION");
        final String jobtitle = i.getStringExtra("JOB_TITLE");
        String pub_date = i.getStringExtra("PUBLISHED_DATE");

//        jobTitle = findViewById(R.id.Jobheading2);
//        description = (EditText) findViewById(R.id.multilineDescription2);
//        qualification = findViewById(R.id.QualificationEditText2);
//        department = findViewById(R.id.departmentEdit);
//        yearsOfExp = findViewById(R.id.yearsOfExEdit);
//        closingDate = findViewById(R.id.editClosingDate);

        jobTitle = findViewById(R.id.Jobheading2);
        description = findViewById(R.id.multilineDescription2);
        qualification = findViewById(R.id.QualificationEditText2);
        closingDate = findViewById(R.id.closingDateEdit);
        pub_date_textView = findViewById(R.id.publishedDateEdit);

        close =(Button) findViewById(R.id.closebtn);
        edit = (Button) findViewById(R.id.editBtn);
        viewList = (Button) findViewById(R.id.viewListBtn);
        examine = (Button) findViewById(R.id.examineBtn);

        jobTitle.setText(String.valueOf(jobtitle));
        description.setText(String.valueOf(description_));
        qualification.setText(String.valueOf(qualificcation_));
        closingDate.setText(String.valueOf(closing_Date));
        pub_date_textView.setText(String.valueOf(pub_date));

        final String finalJobTitle = jobTitle.getText().toString().trim();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view_vacancy.this, edit_vacancy.class);

                startActivity(i);

            }
        });

        examine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getApplicationContext(),view_applicant.class);

                o.putExtra("TITLE",finalJobTitle);
                startActivity(o);
            }
        });

        //View List
        viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(getApplicationContext(),view_list.class);

                o.putExtra("TITLE",finalJobTitle);
                startActivity(o);
            }
        });


//
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final AlertDialog.Builder builder = new AlertDialog.Builder(view_vacancy.this);
//                builder.setMessage("Do you really want to DELETE the application?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                if (TextUtils.isEmpty(in_id.getText().toString()))
//                                    Toast.makeText(getApplicationContext(), "Check You Name", Toast.LENGTH_LONG).show();
//
//                                else {
//                                    dbRef = FirebaseDatabase.getInstance().getReference("User_Req_Job/" + in_id.getText().toString().trim());
//                                    dbRef.removeValue();
//                                    Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();
//
//                                    Intent inte = new Intent(getApplicationContext(), testMain.class);
//                                    startActivity(inte);
//                                }
//                            }
//                        })
//                        .setNegativeButton("Cancel", null);
//
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//        });
//


//////////////////////////////////////////////
//        PublishedID.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (TextUtils.isEmpty(in_Name.getText().toString()))
//                    Toast.makeText(getApplicationContext(), "Check You Name", Toast.LENGTH_LONG).show();
//
//                else{
//                    dbRef = FirebaseDatabase.getInstance().getReference().child("Test/" + in_Name.getText().toString().trim());
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
//                                    yearsOfExp.setText(dataSnapshot.child("contact").getValue().toString());
//                                    ageLimit.setText(dataSnapshot.child("id").getValue().toString());
//
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



        ////////////////////////////////////////////



    }
}
