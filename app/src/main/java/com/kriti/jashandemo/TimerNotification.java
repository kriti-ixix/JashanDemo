package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TimerNotification extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_notification);

        button = findViewById(R.id.notificationButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TAG = "Notification";
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder;

                if (Build.VERSION.SDK_INT >= 26)
                {
                    NotificationChannel channel = new NotificationChannel("1",
                            "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
                    manager.createNotificationChannel(channel);
                    builder = new NotificationCompat.Builder(TimerNotification.this, "1");

                }
                else
                {
                    builder = new NotificationCompat.Builder(TimerNotification.this);
                }

                builder.setContentTitle("My Notification");
                builder.setContentText("This is my notification");
                builder.setSmallIcon(R.drawable.ic_baseline_home_24);
                builder.setLargeIcon(BitmapFactory.decodeResource(
                        TimerNotification.this.getResources(), R.drawable.ic_baseline_library_music_24));

                Notification notification = builder.build();
                manager.notify(1, notification);
            }
        });
    }
}