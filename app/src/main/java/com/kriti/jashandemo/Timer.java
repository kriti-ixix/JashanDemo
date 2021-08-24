package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Timer extends AppCompatActivity {

    EditText alarmEditText; Button alarmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        alarmEditText = findViewById(R.id.alarmEditText);
        alarmButton = findViewById(R.id.alarmButton);

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = Integer.parseInt(alarmEditText.getText().toString());
                Intent intent = new Intent(Timer.this, MyReceiver.class);
                PendingIntent pIntent = PendingIntent.getBroadcast(Timer.this, 1, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000),pIntent);
                Toast.makeText(Timer.this, "Alarm Set", Toast.LENGTH_SHORT).show();
            }
        });
    }
}