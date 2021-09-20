package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GettingAPage extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_apage);

        button = findViewById(R.id.getWeatherButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    GetWeather getWeather = new GetWeather();
                    String weather = getWeather
                            .execute("https://api.openweathermap.org/data/2.5/weather?q=London&appid=d15e4f8bb247b53efc9e1861ed4c18e0")
                            .get();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    class GetWeather extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            String result = ""; URL url;
            HttpURLConnection connection = null;

            try
            {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                int data = isr.read();

                while (data != -1)
                {
                    char current = (char) data;
                    result += current;
                    data = isr.read();
                }

                Log.d("Result", result);

                return result;

            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }

        }
    }
}