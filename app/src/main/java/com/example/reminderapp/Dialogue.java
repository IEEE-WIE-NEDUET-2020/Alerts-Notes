package com.example.reminderapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Dialogue extends AppCompatDialogFragment {
    private EditText edtTitle;
    private EditText edtdetails;
    DatabaseReference db;
    TextView txt1 ;
    TextView txt2;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        db = FirebaseDatabase.getInstance().getReference().child("Alerts");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Alert")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = edtTitle.getText().toString();
                        String details = edtdetails.getText().toString();
                        //make an object
                        alerts_info alertsInfo = new alerts_info();
                        alertsInfo.setTitle(title);
                        alertsInfo.setDetails(details);


                        //insert data to database
                        db.child("AllAlerts").push().setValue(alertsInfo);

                    }
                });

        edtTitle = view.findViewById(R.id.edt_Title);
        edtdetails = view.findViewById(R.id.edt_Details);

        return builder.create();
    }




}


