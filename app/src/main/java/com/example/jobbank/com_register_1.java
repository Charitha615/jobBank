package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class com_register_1 extends AppCompatActivity {

    Button next;
    TextView re_signin;

    EditText In_company_name,In_company_email,In_password_compnay,In_Confirm_password;

    private String com_name,com_email,com_password,com_confirm_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_register_1);

        next = findViewById(R.id.com_next_btn);
        re_signin = findViewById(R.id.com_signin_1);

        In_company_name =findViewById(R.id.company_name_input);
        In_company_email=findViewById(R.id.company_email_input);
        In_password_compnay = findViewById(R.id.company_password_input);
        In_Confirm_password = findViewById(R.id.company_confirm_input);

        //SignUp Next Button

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                com_name = In_company_name.getText().toString().trim();
                com_email = In_company_email.getText().toString().trim();
                com_password = In_password_compnay.getText().toString().trim();
                com_confirm_pass = In_Confirm_password.getText().toString().trim();


                if (TextUtils.isEmpty(com_name)) {
                    Toast.makeText(getApplicationContext(), "Company name missing", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(com_email)) {
                    Toast.makeText(getApplicationContext(), "Company email missing", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(com_password)) {
                    Toast.makeText(getApplicationContext(), "Password missing", Toast.LENGTH_LONG).show();
                }else if (!com_password.equals(com_confirm_pass)) {
                    Toast.makeText(getApplicationContext(), "Password Mismatch", Toast.LENGTH_LONG).show();
                }
                else {



                    Intent i = new Intent(getApplicationContext(), com_register_2.class);

                    i.putExtra("Com_NAME", com_name); //using put Extra
                    i.putExtra("Com_EMAIL", com_email); //using put Extra
                    i.putExtra("Com_PASSWORD", com_password);

                    startActivity(i);
                }
            }
        });

        //Return Sign in page
        re_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),company_login.class));

            }
        });

    }
}