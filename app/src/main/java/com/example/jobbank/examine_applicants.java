package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.os.Bundle;

import java.io.File;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class examine_applicants extends AppCompatActivity {

    private static String status1;
    TextView in_name,in_jobtitle,in_email,in_age,in_experience,in_description,in_qualifications,in_marks,in_totalMarks;
    EditText in_remarks,in_marks1,in_marks2,in_marks3,in_marks4;
    Button btn_calc_tot, btn_save,btn_interview,btn_pending,btn_rejected,btn_delete,btn_cv;
    DatabaseReference dbref;
    int total = 0;
    String status = "";
    model_view_applicants applicant;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    StorageReference ref;
    public String nicp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examine_applicants);

        btn_calc_tot = findViewById(R.id.btn_TotalPoints2);
        btn_save = findViewById(R.id.btn_save_examineApplicant);
        btn_interview = findViewById(R.id.btn_Interview);
        btn_pending = findViewById(R.id.btn_Pending);
        btn_rejected = findViewById(R.id.btn_Rejected);
        btn_delete = findViewById(R.id.btn_delete_examineApplicant);
        btn_cv = findViewById(R.id.btn_CV);

        in_marks1 = findViewById(R.id.marks1);
        in_marks2 = findViewById(R.id.marks2);
        in_marks3 = findViewById(R.id.marks3);
        in_marks4 = findViewById(R.id.marks4);
        in_totalMarks = findViewById(R.id.txtview_totmarks);

        in_name = findViewById(R.id.applicantName);
        in_jobtitle = findViewById(R.id.job_title);
        in_email = findViewById(R.id.txtview_email);
        in_age = findViewById(R.id.txtview_age);
        in_experience = findViewById(R.id.txtview_experience);
        in_description = findViewById(R.id.txtview_description);
        in_qualifications = findViewById(R.id.txtview_qualifications);
        in_marks =findViewById(R.id.txtview_totmarks);
        in_remarks =(EditText) findViewById(R.id.edittxt_remarks);


        Intent i = getIntent();

        String nameP = i.getStringExtra("vName");
        String jobtitleP = i.getStringExtra("vJobTitle");
        String emailP = i.getStringExtra("vEmail");
        String ageP = i.getStringExtra("vAge");
        String experienceP = i.getStringExtra("vExperience");
        String descriptionP = i.getStringExtra("vDescription");
        String qualificationsP = i.getStringExtra("vQualification");
        String remarksP = i.getStringExtra("vRemarks");
        nicp = i.getStringExtra("vNic");

        in_jobtitle.setText(String.valueOf(jobtitleP));
        in_name.setText(String.valueOf(nameP));
        in_email.setText(String.valueOf(emailP));
        in_description.setText(String.valueOf(descriptionP));
        in_qualifications.setText(String.valueOf(qualificationsP));
        in_remarks.setText(String.valueOf(remarksP));
        in_age.setText(String.valueOf(ageP));
        in_experience.setText(String.valueOf(experienceP));

        btn_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadCV();
            }
        });

        btn_calc_tot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(in_marks1.getText().toString()) || TextUtils.isEmpty(in_marks2.getText().toString()) || TextUtils.isEmpty(in_marks3.getText().toString()) || TextUtils.isEmpty(in_marks4.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Marks fields cannot be empty!!!", Toast.LENGTH_LONG).show();
                else if ((Integer.parseInt(in_marks1.getText().toString())>10) || (Integer.parseInt(in_marks2.getText().toString())>10) || (Integer.parseInt(in_marks3.getText().toString())>10) || (Integer.parseInt(in_marks4.getText().toString())>10))
                    Toast.makeText(getApplicationContext(), "Marks should be given out of 10", Toast.LENGTH_LONG).show();
                else{
                    calcTot();
                    status = setStatus(total);
                }
            }
        });

        btn_interview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "Interview";
            }
        });

        btn_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "Pending";
            }
        });

        btn_rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "Rejected";
            }
        });

        applicant = new model_view_applicants();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference();

                if (TextUtils.isEmpty(in_marks1.getText().toString()) || TextUtils.isEmpty(in_marks2.getText().toString()) || TextUtils.isEmpty(in_marks3.getText().toString()) || TextUtils.isEmpty(in_marks4.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Marks fields cannot be empty!!!", Toast.LENGTH_LONG).show();
                else {

                    dbref.child("User_Req_Job/" + nicp + "/marks").setValue(Integer.parseInt(in_marks.getText().toString()));
                    dbref.child("User_Req_Job/" + nicp + "/remarks").setValue(in_remarks.getText().toString());
                    dbref.child("User_Req_Job/" + nicp + "/status").setValue(status);

                    Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                    clearBox();

                    Intent intent = new Intent(getApplicationContext(), view_applicant.class);
                    intent.putExtra("TITLE",in_jobtitle.getText().toString());
                    startActivity(intent);
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(examine_applicants.this);
                builder.setMessage("Do you really want to REMOVE this applicant?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                    dbref = FirebaseDatabase.getInstance().getReference("User_Req_Job/" + nicp);
                                    dbref.removeValue();
                                    Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();

                                    Intent inte = new Intent(getApplicationContext(), view_applicant.class);
                                    startActivity(inte);
                            }
                        })
                        .setNegativeButton("Cancel", null);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void calcTot() {
        total = Integer.parseInt(in_marks1.getText().toString()) + Integer.parseInt(in_marks2.getText().toString()) + Integer.parseInt(in_marks3.getText().toString()) + Integer.parseInt(in_marks4.getText().toString());
        in_totalMarks.setText(String.valueOf(total));
    }

    public static String setStatus(int marks){
        if(marks >= 30){
            status1 = "Interview";
        }
        else if(marks >= 15){
            status1 = "Pending";
        }
        else{
            status1 = "Rejected";
        }
        return status1;
    }

    private void clearBox()
    {
        in_marks1.setText("");
        in_marks2.setText("");
        in_marks3.setText("");
        in_marks4.setText("");
        in_totalMarks.setText("");
    }

    public void downloadCV(){
        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child(nicp);

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFiles(examine_applicants.this, nicp, DIRECTORY_DOWNLOADS,url);
                Toast.makeText(getApplicationContext(), "File Downloaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }

    public long downloadFiles(Context context, String fileName, String destinationDirectory, String url) {
        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName);
        return downloadmanager.enqueue(request);
    }

}