package com.example.guide;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class TodoContent extends AppCompatActivity {
EditText topic, contentTodo;
Button submit;
DatabaseReference rootRef,demoRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_content);
        topic= (EditText) findViewById(R.id.Titletodo);
        contentTodo= (EditText) findViewById(R.id.Contenttodo);
        submit= (Button) findViewById(R.id.subbtn);

        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("ToDo");

        //SETTING ONCLICK LISTENER
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = topic.getText().toString().toUpperCase();
                String valueContent = contentTodo.getText().toString();
            //push creates a unique id in database
                Map<String, Object> map = new HashMap<>();
                map.put("id", demoRef.getKey());
                map.put("title", topic.getText().toString());
                map.put("desc", contentTodo.getText().toString());
                demoRef.push().setValue(value + ":\n\n " + valueContent);
                Toast.makeText(getApplicationContext(),"YOUR UPLOAD SUCCESSFUL",Toast.LENGTH_LONG).show();
                topic.setText(""); contentTodo.setText("");
                Intent intent = new Intent(v.getContext(),AddTodoer.class);
                startActivity(intent);
            }
        });

    }
}
