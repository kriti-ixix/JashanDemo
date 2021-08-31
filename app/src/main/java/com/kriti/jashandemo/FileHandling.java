package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileHandling extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button saveButton, readButton;
    static final String FILENAME = "Example.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_handling);

        editText = findViewById(R.id.editTextFile);
        saveButton = findViewById(R.id.saveFileButton);
        readButton = findViewById(R.id.readFileButton);

        saveButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        try
        {
            if (id == R.id.saveFileButton)
            {
                //Saving the file
                File file = new File(this.getFilesDir() + "/" + FILENAME);
                //com.kriti.jashandemo/Example.txt
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                String content = editText.getText().toString();
                bw.write(content);
                bw.flush();
            }
            else
            {
                //Reading from the file
                File file = new File(this.getFilesDir() + "/" + FILENAME);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String x = "", y;

                while ((y = br.readLine()) != null)
                {
                    x += y;
                    x += "\n";
                }

                editText.setText(x);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}