package com.example.reminderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class NewTask extends AppCompatActivity {
    private static final String TAG = "NewTask";

    TextView titlepage, addtitle,addesc,date,time;
    EditText titledoes,adddetails;
    EditText DisplayDate, DisplayTime;

    DatePickerDialog.OnDateSetListener DatesetListener;
    Button btnadd, btncancel;
    DatabaseReference db;

    Context context =this;
    Calendar cal,cale;

    Date reminder_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        titlepage= findViewById(R.id.titlepage);

        addtitle= findViewById(R.id.addtitle);
        addesc= findViewById(R.id.addesc);
        date= findViewById(R.id.date);
        time= findViewById(R.id.time);


        titledoes= findViewById(R.id.titledoes);
        adddetails= findViewById(R.id.adddetails);
        DisplayDate = findViewById(R.id.add);


        // -------------------------------   setting date and time picker together -------------------------//
        final Calendar newCalender = Calendar.getInstance();
        DisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(NewTask.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {

                        final Calendar newDate = Calendar.getInstance();
                        Calendar newTime = Calendar.getInstance();
                        TimePickerDialog time = new TimePickerDialog(NewTask.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                newDate.set(year,month,dayOfMonth,hourOfDay,minute,0);
                                Calendar tem = Calendar.getInstance();
                                Log.w("TIME",System.currentTimeMillis()+"");
                                if(newDate.getTimeInMillis()-tem.getTimeInMillis()>0){

                                    DisplayDate.setText(newDate.getTime().toString());
                                    reminder_date = newDate.getTime();  // reminder  date and time saved in Date variable

                                }

                                else{
                                    Toast.makeText(NewTask.this,"Invalid time",Toast.LENGTH_SHORT).show();

                                }}
                        },newTime.get(Calendar.HOUR_OF_DAY),newTime.get(Calendar.MINUTE),true);
                        time.show();

                    }
                },newCalender.get(Calendar.YEAR),newCalender.get(Calendar.MONTH),newCalender.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dialog.show();


            }
        });

        btnadd=findViewById(R.id.btnadd);
        btncancel=findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnt = new Intent(NewTask.this, MainActivity.class);
                startActivity(intnt);

            }
        });
        db = FirebaseDatabase.getInstance().getReference().child("Tasks");
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("cal "+reminder_date);
                String Title = titledoes.getText().toString();
                String Desc = adddetails.getText().toString();

                //make an object
                Notes reminder_task = new Notes();
                reminder_task.setTitle(Title);
                reminder_task.setDesc(Desc);
                reminder_task.setReminder_date(reminder_date);

                //insert data to databasw
                db.child("AllTasks").push().setValue(reminder_task);
                Intent intent = new Intent(NewTask.this, MainActivity.class);
                startActivity(intent);


            }
        });



    }
}