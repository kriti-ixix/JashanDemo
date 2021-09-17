package com.kriti.jashandemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Gallery extends AppCompatActivity {

    ArrayList<Bitmap> imagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1);
            }
            else
            {
                getImages();
            }
        }
        else
        {
            getImages();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getImages();
            }
            else
                {
                Toast.makeText(this, "Please allow to read storage", Toast.LENGTH_LONG).show();
            }
        }
    }

    void getImages()
    {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Downloads/");
        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s)
            {
                String extension = s.substring(s.lastIndexOf('.'));
                if (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png"))
                {
                    return true;
                }
                return false;
            }
        });

        for (File f: files)
        {
            String filePath = f.getAbsolutePath();
            Log.d("File", filePath);
            Bitmap bm = BitmapFactory.decodeFile(filePath);
            imagesList.add(bm);
        }
    }
}