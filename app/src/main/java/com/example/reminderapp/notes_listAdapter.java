package com.example.reminderapp;

import android.app.Activity;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class notes_listAdapter extends ArrayAdapter<Notes> {

    private Context context;
    private List<Notes> notesList;
    private SparseBooleanArray mSelectedItemsIds;
   // private Integer[] imageId;

    public notes_listAdapter(Activity context, List<Notes> notesList){

        super(context, R.layout.notes_list, notesList);
        this.context = context;
        this.notesList = notesList;
     //   this.imageId= imageId;
    }


    @Override
    public int getCount() {
        return notesList.size();
    }

    @Override
    public Notes getItem(int position) {
        return notesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v=View.inflate(context, R.layout.notes_list,null);
        TextView tvTitle=(TextView)v.findViewById(R.id.title);
        TextView tvDesc=(TextView)v.findViewById(R.id.desc);
      //  TextView tvDate=(TextView)v.findViewById(R.id.date);
       // ImageView imageView= (ImageView) v.findViewById(R.id.icon);

        Notes notes = notesList.get(position);

       tvTitle.setText(notesList.get(position).getTitle());
       tvDesc.setText((notesList.get(position).getDesc()));
//       tvDate.setText((notesList.get(position).getReminder_date()));
      //  imageView.setImageResource(imageId[position]);

       v.setTag(notesList.get(position).getId());
        return v;

    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();

        notifyDataSetChanged();
    }

    public void  toggleSelection(int position) {

        selectView(position, !mSelectedItemsIds.get(position));

    }

    public void selectView(int position, boolean value) {

        if (value)

            mSelectedItemsIds.put(position,  value);

        else

            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();

    }

    public  SparseBooleanArray getSelectedIds() {

        return mSelectedItemsIds;

    }
}
