package com.example.reminderapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class alerts_listAdapter extends ArrayAdapter<alerts_info> {
    private Context context;
    private List<alerts_info> alertslist;

    public alerts_listAdapter(Context context, List<alerts_info> alertslist) {
        super(context, R.layout.alerts_list, alertslist);
        this.context = context;
        this.alertslist = alertslist;
    }

    public alerts_listAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    @Override
    public int getCount() {
        return alertslist.size();
    }

    @Override
    public alerts_info getItem(int position) {
        return alertslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        {


            View v=View.inflate(context, R.layout.notes_list,null);
            TextView tvTitle=(TextView)v.findViewById(R.id.title);
            TextView tvDesc=(TextView)v.findViewById(R.id.desc);
            //  TextView tvDate=(TextView)v.findViewById(R.id.date);

            alerts_info alertsInfo = alertslist.get(position);

            tvTitle.setText(alertslist.get(position).getTitle());
            tvDesc.setText((alertslist.get(position).getDetails()));
//       tvDate.setText((notesList.get(position).getReminder_date()));
            System.out.println(tvTitle);


            return v;

        }}}
