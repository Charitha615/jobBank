package com.example.jobbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class com_register_2 extends AppCompatActivity {

    TextView re_signin2;

    EditText description,no_department,no_employee,postal;
    Button Create_btn;



    private FirebaseAuth Authent_Company;
    private DatabaseReference refUsers;
    private String firebaseUserId;
    private String descriptionVal,usernameval;
    private String no_Department_Val,com_Name,com_Email,no_Employee_Val,postal_Val;
    private String com_Password;
    private FirebaseUser comID;

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_register_2);

        re_signin2 = findViewById(R.id.com_signin_2);

        Authent_Company = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        description = findViewById(R.id.Input_Description_input);
        no_department = findViewById(R.id.Input_Nu_Department);
        no_employee = findViewById(R.id.Input_Num_Emp);
        postal = findViewById(R.id.Input_postal_town);

        Create_btn = findViewById(R.id.Input_Create_btn);



        Intent i = getIntent(); // catch the put extras values
        com_Name = i.getStringExtra("Com_NAME"); //using put Extra
        com_Email  = i.getStringExtra("Com_EMAIL"); //using put Extra
        com_Password = i.getStringExtra("Com_PASSWORD");

        //Company Create Button
        Create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        //Return Sign in page
        re_signin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),company_login.class));

            }
        });

    }
    private void registerUser() {
        descriptionVal = description.getText().toString().trim();
        no_Department_Val = no_department.getText().toString().trim();
        no_Employee_Val = no_employee.getText().toString().trim();
        postal_Val = postal.getText().toString().trim();


        if (TextUtils.isEmpty(descriptionVal)) {
            Toast.makeText(this, "Description missing", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(no_Department_Val)) {
            Toast.makeText(this, "Departments missing", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(no_Employee_Val)) {
            Toast.makeText(this, "Number of Employee missing", Toast.LENGTH_LONG).show();
        }else if (TextUtils.isEmpty(postal_Val)) {
            Toast.makeText(this, "Postal Town missing", Toast.LENGTH_LONG).show();
        }
        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait we are creating your Company account..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            Authent_Company.createUserWithEmailAndPassword(com_Email, com_Password)
                    .addOnCompleteListener(com_register_2.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(com_register_2.this, "Company Registration Successful", Toast.LENGTH_LONG).show();

                                comID = Authent_Company.getCurrentUser();
                                addUserData();

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(com_register_2.this, "Company Registration failed...Try again ", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();

                            }

                        }
                    });


        }

    }

    private void addUserData() {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (!(datasnapshot.child("Companies").child(comID.getUid()).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("Uid", comID.getUid());
                    userdataMap.put("username", com_Name);
                    userdataMap.put("description", descriptionVal);
                    userdataMap.put("No_Department", no_Department_Val);
                    userdataMap.put("No_Employee", no_Employee_Val);
                    userdataMap.put("Postal_Town", postal_Val);
                    userdataMap.put("email", com_Email);
                    userdataMap.put("ProfileImage", "https://firebasestorage.googleapis.com/v0/b/xplorer-4fa2d.appspot.com/o/DefaultProfilePicture.png?alt=media&token=edd1db50-1f36-46c4-af64-f8f226407ce9");
                    userdataMap.put("LoginStatus", "OffLine");
                    userdataMap.put("search", com_Name.toLowerCase());


                    RootRef.child("Companies").child(comID.getUid()).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(com_register_2.this, "Congratulations!!, your  account has been created ", Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(com_register_2.this, company_login.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(com_register_2.this, "Network error.. Please try again", Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();

                                    }
                                }
                            });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseerror) {

            }
        });


    }
}