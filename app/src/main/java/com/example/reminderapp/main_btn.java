package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_btn extends AppCompatActivity {
    Button btnnotes, btnalerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_btn);
        btnnotes= findViewById(R.id.btnnotes);
        btnalerts= findViewById(R.id.btnalerts);

        btnnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e =new Intent(main_btn.this,MainActivity.class);
                startActivity(e);

            }
        });


        btnalerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e =new Intent(main_btn.this,Alerts.class);
                startActivity(e);

            }
        });
    }
}
