package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GettingAnImage extends AppCompatActivity {

    Button button; ImageView imageView;
    String imageUrl = "https://images.unsplash.com/photo-1630313621450-9e90dcf99d09?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1587&q=80";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_an_image);

        button = findViewById(R.id.downloadButton);
        imageView = findViewById(R.id.downloadedImageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try
                {
                    GetImage getImage = new GetImage();
                    Bitmap bitmap = getImage.execute(imageUrl).get();
                    imageView.setImageBitmap(bitmap);
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    class GetImage extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... strings)
        {
            try
            {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
    }
}