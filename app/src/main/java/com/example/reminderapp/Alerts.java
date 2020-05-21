package com.example.reminderapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.widget.Toast.*;

public class Alerts extends AppCompatActivity {
    private ListView listAlerts;
    private alerts_listAdapter adapter;
    private List<alerts_info> alertsList;
    private Button btnAdd;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
        reference = FirebaseDatabase.getInstance().getReference("Alerts").child("AllAlerts");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                alerts_info alerts = dataSnapshot.getValue(alerts_info.class);
                alertsList.add(alerts);
                Collections.reverse(alertsList);

                alerts_listAdapter adapter = new alerts_listAdapter(Alerts.this, alertsList);
                listAlerts.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        listAlerts= (ListView)findViewById(R.id.List_View);
        btnAdd= findViewById(R.id.button);
        alertsList=new ArrayList<>();

        adapter=new alerts_listAdapter(getApplicationContext(),alertsList);
        listAlerts.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });
    }
    public void openDialog() {
        Dialogue exampleDialog = new Dialogue();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

}

