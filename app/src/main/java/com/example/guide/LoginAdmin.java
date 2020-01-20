package com.example.guide;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginAdmin extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    ProgressBar loadingProgressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                if(usernameEditText.getText().toString().equalsIgnoreCase("Hezekiah")&& passwordEditText.getText().toString().equals("123")){
                    Intent intent= new Intent(v.getContext(), AddTodoer.class);
                    startActivity(intent);
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"LOGGED IN AS ADMIN",Toast.LENGTH_SHORT).show();
                }
                else if (usernameEditText.getText().toString().equalsIgnoreCase("Hezekiah")){
                    Toast.makeText(getApplicationContext(),"WRONG PASSWORD!!! RE-ENTER", Toast.LENGTH_LONG).show();
                    passwordEditText.setText("");
                }
                else if (passwordEditText.getText().toString().equals("123")) {
                    Toast.makeText(getApplicationContext(), "WRONG  USERNAME!!! RE-ENTER", Toast.LENGTH_LONG).show();
                    usernameEditText.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong PASSWORD AND USERNAME", Toast.LENGTH_LONG).show();
                    usernameEditText.setText("");
                    passwordEditText.setText("");
                }
            }

        });
    }

}