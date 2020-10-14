package com.example.jobbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class application_update extends AppCompatActivity {

    EditText in_fName, in_id, in_email, in_phone, in_ex, in_age, in_des, in_qua;
    ImageView img_back;
    Button btn_Update, btn_Delete;
    //Test std; // create a object for call the java(Test) Class
    DatabaseReference dbref;
    //user_home home = new user_home();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_update);

        img_back = findViewById(R.id.backApps);

        btn_Update = findViewById(R.id.update);
        btn_Delete = findViewById(R.id.remove);

        in_fName = findViewById(R.id.fNameFill);
        in_id = findViewById(R.id.idFill);
        in_email = findViewById(R.id.EmailAddressFill);
        in_phone = findViewById(R.id.PhoneFill);

        in_ex = findViewById(R.id.exFill);
        in_age = findViewById(R.id.ageFill);
        in_des = findViewById(R.id.desFill);
        in_qua = findViewById(R.id.quaFill);

        Intent i = getIntent();
        String fnameP = i.getStringExtra("fnamePass");
        String Nic = i.getStringExtra("nicPass");
        String emailP = i.getStringExtra("emailPass");
        String phoneP = i.getStringExtra("phonePass");

        String exP = i.getStringExtra("exPass");
        String ageP = i.getStringExtra("agePass");
        String desP = i.getStringExtra("desPass");
        String quaP = i.getStringExtra("quaPass");

        in_fName.setText(String.valueOf(fnameP));
        in_id.setText(String.valueOf(Nic));
        in_email.setText(String.valueOf(emailP));
        in_phone.setText(String.valueOf(phoneP));

        in_ex.setText(String.valueOf(exP));
        in_age.setText(String.valueOf(ageP));
        in_des.setText(String.valueOf(desP));
        in_qua.setText(String.valueOf(quaP));

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(in_id.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Check Your ID", Toast.LENGTH_LONG).show();

                else {
                    dbref = FirebaseDatabase.getInstance().getReference();

                    dbref.child("User_Req_Job/"+in_id.getText().toString().trim()+"/full_name").setValue(in_fName.getText().toString().trim());
                    dbref.child("User_Req_Job/"+in_id.getText().toString().trim()+"/nic_number").setValue(in_id.getText().toString().trim());
                    dbref.child("User_Req_Job/"+in_id.getText().toString().trim()+"/email").setValue(in_email.getText().toString().trim());
                    dbref.child("User_Req_Job/"+in_id.getText().toString().trim()+"/phone").setValue(in_phone.getText().toString().trim());

                    dbref.child("User_Req_Job/"+in_id.getText().toString().trim()+"/experience").setValue(in_ex.getText().toString().trim());
                    dbref.child("User_Req_Job/"+in_id.getText().toString().trim()+"/age").setValue(in_age.getText().toString().trim());
                    dbref.child("User_Req_Job/"+in_id.getText().toString().trim()+"/description").setValue(in_des.getText().toString().trim());
                    dbref.child("User_Req_Job/"+in_id.getText().toString().trim()+"/qualifications").setValue(in_qua.getText().toString().trim());

                    Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), user_home.class);
                    startActivity(intent);

                }


            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(application_update.this);
                builder.setMessage("Do you really want to DELETE the application?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (TextUtils.isEmpty(in_id.getText().toString()))
                            Toast.makeText(getApplicationContext(), "Check You Name", Toast.LENGTH_LONG).show();

                        else {
                            dbref = FirebaseDatabase.getInstance().getReference("User_Req_Job/" + in_id.getText().toString().trim());
                            dbref.removeValue();
                            Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), user_home.class);
                            startActivity(intent);
                        }
                    }
                })
                        .setNegativeButton("Cancel", null);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}