package com.example.guide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AddTodoer extends AppCompatActivity {
   private ListView listView;
   private ArrayList<String> keys = new ArrayList<String>();
   private ArrayList<String> fruitsArrayList = new ArrayList<String>();
   private ArrayAdapter<String> arrayAdapter;
   private FirebaseDatabase db = FirebaseDatabase.getInstance();
   private DatabaseReference rootRef = db.getReference();
   private DatabaseReference fruitsRef = rootRef.child("ToDo");
//Important in Viewing data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todoer);
        listView = (ListView) findViewById(R.id.listViewfortodo);


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TodoContent.class);
                startActivity(intent);
            }
        });
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fruitsArrayList);
        listView.setAdapter(arrayAdapter);
        fruitsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String val = dataSnapshot.getValue().toString();
                String key = dataSnapshot.getKey();
                fruitsArrayList.add(val); keys.add(key);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Integer ind = (s == null) ? 0 : Integer.parseInt(s);
                fruitsArrayList.set(ind, dataSnapshot.getValue().toString());

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                Integer ind = keys.indexOf(key);
                fruitsArrayList.remove(ind); keys.remove(ind);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

          }


    }


