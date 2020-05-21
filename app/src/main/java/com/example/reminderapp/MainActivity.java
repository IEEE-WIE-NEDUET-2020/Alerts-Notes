package com.example.reminderapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {
     ListView listNotes;
     notes_listAdapter adapter;
     List<Notes> notesList;
     Button btnAdd;
     DatabaseReference ref;
 //   ArrayList<String> str;
     TextView txt,title, desc, date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ref = FirebaseDatabase.getInstance().getReference("Tasks").child("AllTasks");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                
                Notes notes = dataSnapshot.getValue(Notes.class);
                notesList.add(notes);
//                String firstLetter = dataSnapshot.child("title").getValue(String.class);
//                str.add(firstLetter);
//
                Collections.reverse(notesList);
//                Collections.reverse(str);
//
//                int size = str.size();
//                final Integer[] imgname = new Integer[size];
//                int c=0;
//                for (String i : str){
//                    String temp = String.valueOf(i.charAt(0)).toLowerCase();
//                    imgname[c] = mapping(temp);
//                    c++;
//                }
                notes_listAdapter adapter = new notes_listAdapter(MainActivity.this, notesList);
                listNotes.setAdapter(adapter);
//                listNotes.setLongClickable(true);

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
        listNotes= (ListView)findViewById(R.id.lstview);
        notesList = new ArrayList<>();

        btnAdd= findViewById(R.id.button);
        //     str= new ArrayList<>();
        txt = findViewById(R.id.titlepage);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        date = findViewById(R.id.date);

        btnAdd.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,NewTask.class);
                startActivity(i);
            }
        }));


        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Clicked Task #" + view.getTag(), Toast.LENGTH_SHORT).show();

            }
        });


    }}


//        listNotes.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
//
//        listNotes.setMultiChoiceModeListener(new  AbsListView.MultiChoiceModeListener() {
//
//            @Override
//            public boolean  onPrepareActionMode(ActionMode mode, Menu menu) {
//                return false;
//            }
//
//
//            @Override
//            public void  onDestroyActionMode(ActionMode mode) {
//            }
//            @Override
//            public boolean  onCreateActionMode(ActionMode mode, Menu menu) {
//                mode.getMenuInflater().inflate(R.menu.multiple_delete, menu);
//                return true;
//            }
//            @Override
//            public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {
//
//                switch  (item.getItemId()) {
//
//                    case R.id.selectAll:
//
//                        final int checkedCount  = notesList.size();
//                        adapter.removeSelection();
//                        for (int i = 0; i <  checkedCount; i++) {
//
//                            listNotes.setItemChecked(i,   true);
//                        }
//                        mode.setTitle(checkedCount  + "  Selected");
//
//                        return true;
//
//                    case R.id.delete:
//                        androidx.appcompat.app.AlertDialog.Builder  builder = new androidx.appcompat.app.AlertDialog.Builder(
//
//                                MainActivity.this);
//
//                        builder.setMessage("Do you  want to delete selected record(s)?");
//                        builder.setNegativeButton("No", new  DialogInterface.OnClickListener() {
//
//                            @Override
//
//                            public void  onClick(DialogInterface dialog, int which) {
//                            }
//
//                        });
//
//                        builder.setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
//
//                            @Override
//
//                            public void  onClick(DialogInterface dialog, int which) {
//
//                                SparseBooleanArray selected = adapter.getSelectedIds();
//
//                                for (int i =  (selected.size() - 1); i >= 0; i--) {
//
//                                    if  (selected.valueAt(i)) {
////
//
//                                        Notes selecteditem = adapter.getItem(selected.keyAt(i));
//                                        String assignId = selecteditem.getId();
//                                        DatabaseReference newReference = FirebaseDatabase.getInstance().getReference("Tasks").child(assignId);
//                                        newReference.removeValue();
//                                    }
//                                }
//                                mode.finish();
//                                selected.clear();
//                            }
//
//                        });
//
//                        androidx.appcompat.app.AlertDialog alert =  builder.create();
//                        alert.setIcon(R.drawable.questionicon);// dialog  Icon
//                        alert.setTitle("Confirmation"); // dialog  Title
//                        alert.show();
//                        return true;
//                    default:
//                        return false;
//
//                }
//            }
//
//            @Override
//            public void  onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
//                final int checkedCount  = listNotes.getCheckedItemCount();
//                mode.setTitle(checkedCount  + "  Selected");
//                adapter.toggleSelection(position);
//
//            }
//
//        });



//        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                String first_letter = str.get(position);
//                String temp2 = String.valueOf(first_letter.charAt(0)).toLowerCase();
//
//                Notes note = notesList.get(position);
//                showDialog(temp2, note.getTitle(), note.getDesc());
//
//
//            }
//        });
//
//



      //  ImageView im1 = (ImageView) findViewById(R.id.im1);



//
//    public void delTest(final String testId){
//        DatabaseReference newReference = FirebaseDatabase.getInstance().getReference("AllTasks").child(testId);
//        newReference.removeValue();
//        Toast.makeText(this, "Test is deleted", Toast.LENGTH_LONG).show();
//    }}

//    private Integer mapping(String mychar) {
//        Integer[] imgname = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v, R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z };
//        String myabc = "abcdefghijklmnopqrstuvwxyz";
//        int pos = myabc.indexOf(mychar);
//        return imgname[pos];
//    }
//

//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                notesList.clear();
//                str.clear();
//                for(DataSnapshot notesSnapshot : dataSnapshot.getChildren()){
//                    Notes notes = notesSnapshot.getValue(Notes.class);
//                    notesList.add(notes);
//
//                    String firstLetter = notesSnapshot.child("title").getValue(String.class);
//                    str.add(firstLetter);
//                }
//
//                int size = str.size();
//                final Integer[] imgname = new Integer[size];
//                int c=0;
//                for (String i : str){
//                    String temp = String.valueOf(i.charAt(0)).toLowerCase();
//                    imgname[c] = mapping(temp);
//                    c++;
//                }
//
//                notes_listAdapter adapter = new notes_listAdapter(MainActivity.this, notesList, imgname);
//                listNotes.setAdapter(adapter);
//                listNotes.setLongClickable(true);
//
//        }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//
//            }
//        });

