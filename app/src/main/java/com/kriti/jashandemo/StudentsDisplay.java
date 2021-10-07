package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudentsDisplay extends AppCompatActivity {

    ArrayList<StudentInfo> stdList = new ArrayList<StudentInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_display);

        String url = "http://"+ getString(R.string.host) + "/jashandemo/select.php";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject json = new JSONObject(response);
                    String res = json.getString("res");
                    Log.d("Students", "Response: " + res);

                    if (res.equals("OK"))
                    {
                        JSONArray nameArray = json.getJSONArray("name");
                        JSONArray classArray = json.getJSONArray("class");
                        JSONArray rollNoArray = json.getJSONArray("rollno");
                        JSONArray marksArray = json.getJSONArray("marks");

                        for (int i=0; i<marksArray.length(); i++)
                        {
                            StudentInfo info = new StudentInfo();

                            info.setRollno(rollNoArray.getInt(i));
                            info.setName(nameArray.getString(i));
                            info.setCls(classArray.getInt(i));
                            info.setMarks(marksArray.getDouble(i));

                            stdList.add(info);
                        }

                        Log.d("Students", String.valueOf(stdList.size()));

                    }
                    else
                    {
                        Toast.makeText(StudentsDisplay.this, "Empty", Toast.LENGTH_SHORT).show();
                        Log.d("Students", "Empty");
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentsDisplay.this, "Error connecting", Toast.LENGTH_SHORT).show();
                Log.d("Volley Error", error.toString());
            }
        });

        queue.add(request);

    }
}