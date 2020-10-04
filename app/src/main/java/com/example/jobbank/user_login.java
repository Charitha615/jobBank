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

public class user_login extends AppCompatActivity {

    TextView user_sign;

    String passVal,emailVal,emailAddress,passwordVal;
    TextView forgot_pass;
    EditText email_in,password_in;
    Button signin;


    private FirebaseAuth Login_User;
    private DatabaseReference refUsers, database;
    private String firebaseUserId;
    private EditText email, password;
    private Button signInBtn;
    private TextView registerLink, forgotPasswordLink;
    private ProgressDialog loadingBar;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        user_sign = findViewById(R.id.signuplink);

        Login_User = FirebaseAuth.getInstance();

        signin = findViewById(R.id.signin__btn);
        forgot_pass = findViewById(R.id.forgetpassword);
        email_in = findViewById(R.id.Email_Input_user);
        password_in = findViewById(R.id.password_input_user);
        loadingBar = new ProgressDialog(this);

        passVal = password_in.getText().toString().trim();

        //Sign In
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        //User Sign Up Link

        user_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),user_register.class));
            }
        });

        //Forgot Password
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(email_in.getText())) {
                    Toast.makeText(user_login.this, "Please enter your email address", Toast.LENGTH_SHORT).show();

                } else {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    emailAddress = email_in.getText().toString().trim();

                    auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(user_login.this, "We have sent you an email to reset password "+emailAddress+"", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

            }
        });
    }

    private void loginUser() {

        emailVal = email_in.getText().toString().trim();
        passwordVal = password_in.getText().toString().trim();

        if (TextUtils.isEmpty(passwordVal)) {
            Toast.makeText(this, "Password missing", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(emailVal)) {
            Toast.makeText(this, "email missing", Toast.LENGTH_LONG).show();
        } else {

            loadingBar.setTitle("Sign In");
            loadingBar.setMessage("Please wait we are checking your credentials..");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            Login_User.signInWithEmailAndPassword(emailVal, passwordVal)
                    .addOnCompleteListener(user_login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                user = Login_User.getCurrentUser().getUid();
                                DatabaseReference rootref = FirebaseDatabase.getInstance().getReference().child("Users");

                                rootref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.child(user).exists())
                                        {

                                            // checkIsAdmin();

                                            Intent intent = new Intent(user_login.this, user_home.class);

                                            startActivity(intent);

                                            loadingBar.dismiss();
                                        }
                                        else
                                            Toast.makeText(user_login.this, "This account is not available as a User ", Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });




                            }else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(user_login.this, "User Sign in failed", Toast.LENGTH_LONG).show();

                                loadingBar.dismiss();

                            }

                        }


                    });

        }

    }
}