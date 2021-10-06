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

import org.json.JSONObject;

public class StudentsDisplay extends AppCompatActivity {

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

                    if (res.equals("OK"))
                    {

                    }
                    else
                    {
                        Toast.makeText(StudentsDisplay.this, "Empty", Toast.LENGTH_SHORT).show();
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
                Log.d("Volley Error", error.getMessage());
            }
        });

        queue.add(request);

    }
}