
package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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


        Intent i =getIntent();
        final String description_ = i.getStringExtra("DESCRIPTION");
        final String qualificcation_ = i.getStringExtra("QUALIFICATION");
        final String closing_Date = i.getStringExtra("CLOSING_DATE");
        String publishedDate = i.getStringExtra("DESCRIPTION");
        final String jobtitle = i.getStringExtra("JOB_TITLE");
        String pub_date = i.getStringExtra("PUBLISHED_DATE");
        final String vID = i.getStringExtra("VACANCY_ID");


        final String dep = i.getStringExtra("DEPARTMENT");
        final String yrs = i.getStringExtra("YEARS_OF_EXP");
        final String age_ = i.getStringExtra("AGE_LIMIT");
        final String Jobtype = i.getStringExtra("JOB_TYPE");

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
        //vac_ID.setText(String.valueOf(vID));

        final String finalJobTitle = jobTitle.getText().toString().trim();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(view_vacancy.this, edit_vacancy.class);

                //sending data from view vacancy to edit vacancy
                i.putExtra("Description01", description_);
                i.putExtra("qualification01", qualificcation_);
                i.putExtra("department01", dep);
                i.putExtra("ageLimit01", age_);
                i.putExtra("Years_Of_Exp01", yrs);
                i.putExtra("JobType01", Jobtype);
                i.putExtra("ClosingDate01", closing_Date);
                i.putExtra("vID", vID);




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

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(view_vacancy.this);
                builder.setMessage("Do you really want to DELETE the vacancy?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dbRef = FirebaseDatabase.getInstance().getReference("PublishVacancy/" + vID);
                                dbRef.removeValue();
                                //Log.e("Test", "success!");
                                //Log.e("Test", vID);
                                Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();

                                Intent inte = new Intent(getApplicationContext(), view_published_vacancies.class);
                                startActivity(inte);
                            }
                        })
                        .setNegativeButton("Cancel", null);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }
}
