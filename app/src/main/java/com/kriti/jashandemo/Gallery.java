package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Gallery extends AppCompatActivity {

    ArrayList<Bitmap> imagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

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