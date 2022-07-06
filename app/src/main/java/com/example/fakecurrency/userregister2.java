package com.example.fakecurrency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class userregister2 extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9;
    Button b1;
    String url;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister2);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=findViewById(R.id.editTextTextPersonName2);
        e2=findViewById(R.id.editTextTextPersonName3);
        e3=findViewById(R.id.editTextTextPersonName4);
        e4=findViewById(R.id.editTextTextPersonName5);
        e5=findViewById(R.id.editTextTextPersonName6);
        e6=findViewById(R.id.editTextTextPersonName8);
        e7=findViewById(R.id.editTextTextPersonName9);
        e8=findViewById(R.id.editTextTextPersonName12);
        e9=findViewById(R.id.editTextTextPassword2);
        b1=findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Firstname=e1.getText().toString();
                final String Lastname=e2.getText().toString();
                final String Place=e3.getText().toString();
                final String Post=e4.getText().toString();
                final String Pin=e5.getText().toString();
                final String Email=e6.getText().toString();
                final String Phone=e7.getText().toString();
                final String Username=e8.getText().toString();
                final String Password=e9.getText().toString();
                if(Firstname.equalsIgnoreCase("")) {
                    e1.setError("Enter your name");
                }

                else if((!Firstname.matches("^[a-zA-Z]*$")))
                {
                    e1.setError("characters only allowed");



                }
                else if(Lastname.equalsIgnoreCase("")) {
                    e2.setError("Enter your name");
                }
                else if(Place.equalsIgnoreCase("")) {
                    e3.setError("Enter your name");
                }
                else if(Pin.equalsIgnoreCase("")) {
                    e4.setError("Enter your name");
                }
 else if(Post.equalsIgnoreCase("")) {
                    e5.setError("Enter your name");
                }
                else if(Phone.equalsIgnoreCase("")) {
                    e7.setError("Enter your name");
                }
                else if(Phone.length()!=10)
                {
                    e7.setError("Minimum 10 nos required");
                    e7.requestFocus();
                }


                else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
                {
                    e6.setError("Enter Valid Email");
                    e6.requestFocus();
                }
                else if(Password.equalsIgnoreCase("")) {
                    e9.setError("Enter your name");
                }else if(Password.length()<=6) {
                    e9.setError("Minimum 6 nos required");
                }else if(Username.length()<=6) {
                    e8.setError("Minimum 6 nos required");
                }
                else
                {

                    RequestQueue queue = Volley.newRequestQueue(userregister2.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/userregister";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("Success")) {
                                    Toast.makeText(userregister2.this, "registration successful", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(ik);


                                } else {
                                    Toast.makeText(userregister2.this, res, Toast.LENGTH_SHORT).show();


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                            Toast.makeText(userregister2.this, "duplicated entry", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("fname", Firstname);
                            params.put("lname", Lastname);
                            params.put("place", Place);
                            params.put("post", Post);
                            params.put("email", Email);
                            params.put("phone", Phone);
                            params.put("uname", Username);
                            params.put("pswrd", Password);
                            params.put("pin", Pin);
                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }
            }
        });

    }
}