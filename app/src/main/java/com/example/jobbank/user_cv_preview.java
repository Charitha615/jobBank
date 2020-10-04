package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class user_cv_preview extends AppCompatActivity {

    TextView full_name, Nic_number, email, mobile, work_ex, age, description, qualification,notification;
    String full_name_in, Nic_number_in, email_in, mobile_in, work_ex_in, age_in, description_in, qualification_in,url,company_name_in,com_address_in,closing_date_in;
    Button confirm_btn, edit_btn,select_CV;
    DatabaseReference dbref;
    cv_uploader_java std;

    FirebaseStorage storage;
    FirebaseDatabase database;
    Uri pdfUri; //
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cv_preview);

        edit_btn =findViewById(R.id.Edit_btn_cv_pre);
        std = new cv_uploader_java(); // call the Java class


        ///////////////////////////////UPLOAD CV////////////////////////////////////////////

        storage = FirebaseStorage.getInstance(); //return an object of Firebase Storage
        database = FirebaseDatabase.getInstance(); //return an object of Firebase Storage
        select_CV = findViewById(R.id.select_cv);
        confirm_btn = findViewById(R.id.cv_pre_submit_btn);
        notification = findViewById(R.id.notifi);




        select_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContextCompat.checkSelfPermission(user_cv_preview.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    selectPdf();
                }
                else
                {
                    ActivityCompat.requestPermissions(user_cv_preview.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);
                }
            }
        });





        ///////////////////////////////////////////////////////////////////////////


        full_name = findViewById(R.id.cv_full_name_pre);
        Nic_number = findViewById(R.id.cv_last_name_pre);
        email = findViewById(R.id.cv_email_pre);
        mobile = findViewById(R.id.cv_mobile_pre);
        work_ex = findViewById(R.id.cv_work_ex_pre);
        age = findViewById(R.id.cv_age_pre);
        description = findViewById(R.id.cv_description_pre);
        qualification = findViewById(R.id.cv_qualification_pre);

        Intent i = getIntent(); // catch the put extras values

        full_name_in = i.getStringExtra("FIRST_NAME"); //using put Extra
        Nic_number_in = i.getStringExtra("LAST_NAME"); //using put Extra
        email_in = i.getStringExtra("EMAIL"); //using put Extra
        mobile_in = i.getStringExtra("MOBILE"); //using put Extra
        work_ex_in = i.getStringExtra("WORK_EX"); //using put Extra
        age_in = i.getStringExtra("AGE"); //using put Extra
        description_in = i.getStringExtra("DESCRIPTION14"); //using put Extra
        qualification_in = i.getStringExtra("QUALIFICATION14"); //using put Extra
        company_name_in = i.getStringExtra("COM_NAME");
        com_address_in = i.getStringExtra("COM_ADDRESS");
        closing_date_in = i.getStringExtra("CLOSING_DATE");

        full_name.setText(full_name_in);
        Nic_number.setText(Nic_number_in);
        email.setText(email_in);
        mobile.setText(mobile_in);
        work_ex.setText(work_ex_in);
        age.setText(age_in);
        description.setText(description_in);
        qualification.setText(qualification_in);

        //Edit Button
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(getApplicationContext(), user_add_cv.class);



                i.putExtra("FIRST_NAME", full_name_in); //using put Extra
                i.putExtra("LAST_NAME", Nic_number_in); //using put Extra
                i.putExtra("EMAIL", email_in);
                i.putExtra("MOBILE", mobile_in);
                i.putExtra("WORK_EX", work_ex_in);
                i.putExtra("AGE", age_in);
                i.putExtra("DESCRIPTION", description_in);
                i.putExtra("QUALIFICATION", qualification_in);

                startActivity(i);*/
                finish();
            }
        });
////////////////////////////////////////////INSERT AND CV UPLOAD/////////////////////////////////////////////////
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbref = FirebaseDatabase.getInstance().getReference().child("User_Req_Job");
                if(pdfUri!=null)
                {
                    uploadFile(pdfUri); // Upload CV

                std.setFull_name(full_name_in); //Trim is Remove the Spaces in text filds
                std.setNic_number(Nic_number_in);
                std.setEmail(email_in);
                std.setPhone(mobile_in);
                std.setExperience(work_ex_in);
                std.setAge(age_in);
                std.setDescription(description_in);
                std.setQualifications(qualification_in);
                std.setClosingDate(closing_date_in);///
                std.setCompany(company_name_in);///
                std.setPosition(com_address_in);///
                std.setStatus("Initial");
                std.setMarks(0);
                std.setRemarks("None");
                dbref.child(Nic_number.getText().toString().trim()).setValue(std);

                Toast.makeText(getApplicationContext(),"Your Data Inserted",Toast.LENGTH_SHORT).show();



                    //startActivity(new Intent(getApplicationContext(),user_home.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Select a file",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void uploadFile(Uri pdfUri) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading file");
        progressDialog.setProgress(0);
        progressDialog.show();

        final String fileName = Nic_number_in+".pdf";
        StorageReference storageReference = storage.getReference(); // return path

        storageReference.child(fileName).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        url = taskSnapshot.getUploadSessionUri().toString();///

                        DatabaseReference reference = database.getReference();
                        reference.child(fileName).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),"File is successfuly uploaded",Toast.LENGTH_LONG).show();

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"File is not successfuly uploaded",Toast.LENGTH_LONG).show();

                                }
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                int currentprograss = (int) (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                progressDialog.setProgress(currentprograss);
            }
        });
    }

    private void selectPdf() {
        //To offer user select file using file manager
        //user intent

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);//to fetch files
        startActivityForResult(intent,86);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //check whether user has selected  a file or not EX PDF
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 86 && resultCode == RESULT_OK && data != null)
        {
            pdfUri = data.getData(); // return the uri of selected file....
            notification.setText("A File is Selected");
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please Select CV",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 9 && grantResults[0] ==PackageManager.PERMISSION_GRANTED)
        {
            selectPdf();
        }
        else
            Toast.makeText(getApplicationContext(),"Please Provide Permission",Toast.LENGTH_LONG).show();
    }
}