package com.example.jobbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference refUsers, database;
    private String firebaseUserId, emailVal, passwordVal, usernameVal, profileImageVal;
    private EditText email, password;
    private Button signInBtn;
    private TextView registerLink, forgotPasswordLink;
    private ProgressDialog loadingBar;
    private FirebaseUser user;

    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = findViewById(R.id.signuplink);

        mAuth = FirebaseAuth.getInstance();

        signInBtn = (Button) findViewById(R.id.signinbtn);
        email = (EditText) findViewById(R.id.inemail);
        password = (EditText) findViewById(R.id.inpassword);
        loadingBar = new ProgressDialog(this);
        forgotPasswordLink = findViewById(R.id.forgetpassword);
        database = FirebaseDatabase.getInstance().getReference().child("Users");


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        forgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText())) {
                    Toast.makeText(LoginActivity.this, "Please enter your email address", Toast.LENGTH_SHORT).show();

                } else {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String emailAddress = email.getText().toString().trim();

                    auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "We have sent you an email to reset password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

    }

    private void loginUser() {

        emailVal = email.getText().toString().trim();
        passwordVal = password.getText().toString().trim();

        if (TextUtils.isEmpty(passwordVal)) {
            Toast.makeText(this, "Password missing", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(emailVal)) {
            Toast.makeText(this, "email missing", Toast.LENGTH_LONG).show();
        } else {

            loadingBar.setTitle("Sign In");
            loadingBar.setMessage("Please wait we are checking your credentials..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(emailVal, passwordVal)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                user = mAuth.getCurrentUser();
                                // checkIsAdmin();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                startActivity(intent);

                                loadingBar.dismiss();


                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(LoginActivity.this, "User Sign in failed", Toast.LENGTH_LONG).show();

                                loadingBar.dismiss();

                            }

                        }


                    });

        }

    }

}