package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListenerDemo extends AppCompatActivity {

    TextView t1, t2; Button button;
    String TAG = "Testing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener_demo);

        t1 = findViewById(R.id.myTextView);
        t2 = findViewById(R.id.myTextView2);
        button = findViewById(R.id.myButton);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "TextView1 was clicked");
                Toast.makeText(ListenerDemo.this, "TextView1 was clicked", Toast.LENGTH_LONG).show();
            }
        });

        t2.setOnClickListener(t2Listener);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Button was clicked");
                Toast.makeText(ListenerDemo.this, "Button was clicked", Toast.LENGTH_LONG).show();
            }
        });

    }

    View.OnClickListener t2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.i(TAG, "TextView2 was clicked");
            Toast.makeText(ListenerDemo.this, "TextView2 was clicked", Toast.LENGTH_LONG).show();
        }
    };

}