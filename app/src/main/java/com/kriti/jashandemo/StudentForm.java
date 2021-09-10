package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentForm extends AppCompatActivity {

    EditText nameEditText, rollNumberEditText, marksEditText;
    Button insertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        nameEditText = findViewById(R.id.nameEditText);
        rollNumberEditText = findViewById(R.id.rollNumberEditText);
        marksEditText = findViewById(R.id.marksEditText);
        insertButton = findViewById(R.id.insertButton);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                int rollno = Integer.parseInt(rollNumberEditText.getText().toString());
                float marks = Float.parseFloat(marksEditText.getText().toString());

                DBHelper db = new DBHelper(StudentForm.this);
                long result = db.addValues(rollno, name, marks);

                if (result == -1)
                {
                    Toast.makeText(StudentForm.this, "Values not inserted", Toast.LENGTH_SHORT).show();
                    Log.d("Output", "Value not inserted at " + String.valueOf(result));
                }
                else
                {
                    Toast.makeText(StudentForm.this, "Values inserted", Toast.LENGTH_SHORT).show();
                    Log.d("Output", "Value inserted at " + String.valueOf(result));
                }
            }
        });
    }
}