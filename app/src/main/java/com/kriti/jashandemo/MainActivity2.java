package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    Button addButton, subButton, mulButton, divButton;
    EditText firstEditText, secondEditText;
    TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addButton = findViewById(R.id.addButton);
        subButton = findViewById(R.id.subButton);
        mulButton = findViewById(R.id.mulButton);
        divButton = findViewById(R.id.divButton);
        firstEditText = findViewById(R.id.firstEditText);
        secondEditText = findViewById(R.id.secondEditText);
        outputTextView = findViewById(R.id.outputTextView);

        addButton.setOnClickListener(this);
        subButton.setOnClickListener(this);
        mulButton.setOnClickListener(this);
        divButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        double first = Double.parseDouble(firstEditText.getText().toString());
        double second = Double.parseDouble(secondEditText.getText().toString());
        int id = view.getId();
        double output = 0;

        if (id == R.id.addButton)
        {
            output = first + second;
        }
        else if (id == R.id.subButton)
        {
            output = first - second;
        }
        else if (id == R.id.mulButton)
        {
            output = first * second;
        }
        else if (id == R.id.divButton)
        {
            output = first / second;
        }

        outputTextView.setText(String.valueOf(output));
    }
}

