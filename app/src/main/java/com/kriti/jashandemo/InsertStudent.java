package com.kriti.jashandemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertStudent extends AppCompatActivity {

    Button button;
    EditText nameEditText, rollNumberEditText, marksEditText, classEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_student);

        button = findViewById(R.id.insertStudentButton);
        nameEditText = findViewById(R.id.insertName);
        rollNumberEditText = findViewById(R.id.insertRollNo);
        marksEditText = findViewById(R.id.insertMarks);
        classEditText = findViewById(R.id.insertClass);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, rollno, marks, cls;

                name = nameEditText.getText().toString();
                rollno = rollNumberEditText.getText().toString();
                marks = marksEditText.getText().toString();
                cls = classEditText.getText().toString();

                String url = "http://"+ getString(R.string.host) +"/jashandemo/inserting.php";
                RequestQueue queue = Volley.newRequestQueue(InsertStudent.this);
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            String output = json.getString("res");

                            if (output.equals("OK"))
                            {
                                Toast.makeText(InsertStudent.this, "Values inserted", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(InsertStudent.this, "Error inserting", Toast.LENGTH_SHORT).show();

                            }

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InsertStudent.this, "Error connecting", Toast.LENGTH_SHORT).show();
                        Log.d("Volley Error", error.getMessage());
                    }
                })
                {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap <String, String> map = new HashMap<>();
                        map.put("name", name);
                        map.put("rollno", String.valueOf(rollno));
                        map.put("class", String.valueOf(cls));
                        map.put("marks", String.valueOf(marks));
                        return map;
                    }
                };

                queue.add(request);
            }
        });
    }
}