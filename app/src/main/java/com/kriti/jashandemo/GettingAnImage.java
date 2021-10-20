package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GettingAnImage extends AppCompatActivity {

    Button button; ImageView imageView; ProgressBar progressBar;
    String imageUrl = "https://images.unsplash.com/photo-1630313621450-9e90dcf99d09?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1587&q=80";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_an_image);

        button = findViewById(R.id.downloadButton);
        imageView = findViewById(R.id.downloadedImageView);
        progressBar = findViewById(R.id.progressBar);

        //Check for permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //Ask for permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try
                {
                    GetImage getImage = new GetImage();
                    Bitmap bitmap = getImage.execute(imageUrl).get();
                    imageView.setImageBitmap(bitmap);
                    Log.d("Downloading", "Image downloaded");
                    saveImage(bitmap);
                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    void saveImage(Bitmap bitmap)
    {
        try
        {
            File sdCard = Environment.getExternalStorageDirectory();
            File directory = new File(sdCard.getAbsolutePath() + "/Download");
            //directory.mkdir();

            File output = new File(directory, "my image.jpg");
            FileOutputStream fos = new FileOutputStream(output);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush(); fos.close();

            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap,
                    "my image.jpg", null);

            Log.d("Downloading", "Image saved");

            Toast.makeText(this, "Download complete", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    class GetImage extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... strings)
        {
            progressBar.setVisibility(View.VISIBLE);

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

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}