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

public class user_register extends AppCompatActivity {

    TextView return_signin;

    private FirebaseAuth Authent_User;
    private DatabaseReference ref_Users;
    private String firebaseUserId;
    private String userVal;
    private String emailVal;
    private String passwordVal, C_password;
    private FirebaseUser userID;
    private EditText user_name, email_in, password,confirm_password;
    private Button registerBtn;
    private ProgressDialog loadingBar;
    Button user_create_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        return_signin = findViewById(R.id.user_signin_link);

        Authent_User = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        user_create_button = findViewById(R.id.User_create_btn);

        user_name = findViewById(R.id.user_name_input);
        email_in = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);
        confirm_password  = findViewById(R.id.confirm_password_input);



        //Sign Up Button
        user_create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        //Return Sign In Page

        return_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),user_login.class));
            }
        });
    }

    private void registerUser() {

        userVal = user_name.getText().toString().trim();
        emailVal = email_in.getText().toString().trim();
        passwordVal = password.getText().toString().trim();
        C_password = confirm_password.getText().toString().trim();




        if (TextUtils.isEmpty(userVal)) {
            Toast.makeText(this, "username missing", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(emailVal)) {
            Toast.makeText(this, "email missing", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(passwordVal)) {
            Toast.makeText(this, "Password missing", Toast.LENGTH_LONG).show();
        }else if (!passwordVal.equals(C_password)) {
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_LONG).show();
        }
        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait we are creating your account..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            Authent_User.createUserWithEmailAndPassword(emailVal, passwordVal)
                    .addOnCompleteListener(user_register.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(user_register.this, "User Registration Successful", Toast.LENGTH_LONG).show();

                                userID = Authent_User.getCurrentUser();
                                addUserData();

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(user_register.this, "User Registration failed...Try again ", Toast.LENGTH_LONG).show();
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
                if (!(datasnapshot.child("Users").child(userID.getUid()).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("Uid", userID.getUid());
                    userdataMap.put("username", userVal);
                    userdataMap.put("email", emailVal);
                    userdataMap.put("ProfileImage", "https://us.123rf.com/450wm/solargaria/solargaria1709/solargaria170900007/85362512-user-icon-male-avatar-in-business-suit-businessman-flat-icon-man-in-business-suit-avatar-of-business.jpg?ver=6");
                    userdataMap.put("LoginStatus", "Offline");
                    userdataMap.put("search", userVal.toLowerCase());


                    RootRef.child("Users").child(userID.getUid()).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(user_register.this, "Congratulations!!, your  account has been created ", Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(user_register.this, user_login.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(user_register.this, "Network error.. Please try again", Toast.LENGTH_LONG).show();
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