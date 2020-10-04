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

public class company_login extends AppCompatActivity {

    TextView com_signup;
    String passVal,emailVal,emailAddress,passwordVal;
    TextView forgot_pass;
    EditText email_in,password_in;
    Button Com_signin;

    private FirebaseAuth Login_Company;
    private DatabaseReference refCompanies, database;
    private String firebaseUserId;
    private EditText email, password;
    private Button signInBtn;
    private TextView registerLink, forgotPasswordLink,Create_Acc;
    private ProgressDialog loadingBar;
    private String company;
    //private FirebaseUser company_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);

        com_signup = findViewById(R.id.com_signuplink4);

        Login_Company = FirebaseAuth.getInstance();
        refCompanies = FirebaseDatabase.getInstance().getReference().child("Companies");//////////////////////////


        Com_signin = findViewById(R.id.Com_SignIn);
        forgot_pass = findViewById(R.id.forgot_Com_pass);
        email_in = findViewById(R.id.Com_Email_Input);
        password_in = findViewById(R.id.Com_Pass_Input);
        loadingBar = new ProgressDialog(this);


        passVal = password_in.getText().toString().trim();

        //Forgot Password
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(email_in.getText())) {
                    Toast.makeText(company_login.this, "Please enter your email address", Toast.LENGTH_SHORT).show();

                } else {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    emailAddress = email_in.getText().toString().trim();

                    auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(company_login.this, "We have sent you an email to reset password "+emailAddress+"", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }

            }
        });

        Com_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        //User Sign In Link

        com_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),com_register_1.class));
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

            Login_Company.signInWithEmailAndPassword(emailVal, passwordVal)
                    .addOnCompleteListener(company_login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                company = Login_Company.getCurrentUser().getUid();
                                DatabaseReference rootref = FirebaseDatabase.getInstance().getReference().child("Companies");

                                rootref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.child(company).exists())
                                        {


                                            // checkIsAdmin();

                                            Intent intent = new Intent(company_login.this, Company_home.class);

                                            startActivity(intent);

                                            loadingBar.dismiss();

                                        }
                                        else
                                            Toast.makeText(company_login.this, "This account is not available as a company ", Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });




                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(company_login.this, "User Sign in failed", Toast.LENGTH_LONG).show();

                                loadingBar.dismiss();

                            }

                        }


                    });

        }

    }
}