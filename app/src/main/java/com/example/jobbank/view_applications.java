package com.example.jobbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AutomaticZenRule;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class view_applications extends AppCompatActivity {

    TextView in_position,in_company,in_closing,in_stts;
    Button btn_view;
    ImageView img_back1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applications);

        img_back1 = findViewById(R.id.backApps);
        btn_view= findViewById(R.id.edit);

        in_position = findViewById(R.id.positionFill);
        in_company = findViewById(R.id.companyFill);
        in_closing = findViewById(R.id.dateFill);
        in_stts = findViewById(R.id.statusFill);

        Intent i = getIntent();
        String pos = i.getStringExtra("vName");
        String com = i.getStringExtra("cName");
        String close = i.getStringExtra("cDate");
        String stts = i.getStringExtra("vStatus");


        in_position.setText(String.valueOf(pos));
        in_company.setText(String.valueOf(com));
        in_closing.setText(String.valueOf(close));
        in_stts.setText(String.valueOf(stts));

        final String fname = i.getStringExtra("fName");
        final String NIC = i.getStringExtra("nic");
        final String email = i.getStringExtra("emailVal");
        final String phone = i.getStringExtra("phoneVal");

        final String ex = i.getStringExtra("exVal");
        final String age = i.getStringExtra("ageVal");
        final String des = i.getStringExtra("desVal");
        final String qua = i.getStringExtra("quaVal");


        if (checkStatus(stts)){
            btn_view.setEnabled(true);
        }

        img_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), application_update.class);

                intent.putExtra("fnamePass", fname);
                intent.putExtra("nicPass", NIC);
                intent.putExtra("emailPass", email);
                intent.putExtra("phonePass", phone);

                intent.putExtra("exPass", ex);
                intent.putExtra("agePass", age);
                intent.putExtra("desPass", des);
                intent.putExtra("quaPass", qua);

                startActivity(intent);
            }
        });

    }

    public static boolean checkStatus(String st){

        boolean val = false;
        if(st.equals("Initial")){
            val = true;
        }

        return val;
    }
}