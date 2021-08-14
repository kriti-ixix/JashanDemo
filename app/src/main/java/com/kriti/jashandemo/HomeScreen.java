package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {

    TextView displayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        displayTextView = findViewById(R.id.displayTextView);

        Intent in = getIntent();
        String name = in.getStringExtra("username");
        displayTextView.setText(name);
    }
}