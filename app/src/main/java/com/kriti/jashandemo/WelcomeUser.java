package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeUser extends AppCompatActivity {

    Button sendButton;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);

        sendButton = findViewById(R.id.sendNameButton);
        nameEditText = findViewById(R.id.userEditText);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = nameEditText.getText().toString();
                Intent in = new Intent(WelcomeUser.this, HomeScreen.class);
                in.putExtra("username", userName); //Key - value pairs
                startActivity(in);
            }
        });

    }

}