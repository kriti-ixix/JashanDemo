package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MotionSensor extends AppCompatActivity {

    SensorManager sensorManager; TextView textView; List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_sensor);
        textView = findViewById(R.id.motionTextView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        list = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (list.size() > 0)
        {
            sensorManager.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStop() {
        if (list.size()>0)
        {
            sensorManager.unregisterListener(sel);
        }
        
        super.onStop();
    }

    SensorEventListener sel = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            textView.setText("X: " + values[0] + "\nY: " + values[1] + "\nZ: " + values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {}};

}