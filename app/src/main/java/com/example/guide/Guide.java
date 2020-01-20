package com.example.guide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Guide extends AppCompatActivity {
    ListView listView;
    private ArrayList<String> keys = new ArrayList<String>();
    private ArrayList<String> fruitsArrayList = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = db.getReference();
    private DatabaseReference fruitsRef = rootRef.child("ToDo");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        listView= (ListView) findViewById(R.id.listView);
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
