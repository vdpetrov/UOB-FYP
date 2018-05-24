package com.example.vdpetrov.pleasefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextView tvGoReg;
    EditText aEmail, aPassword;
    Button btn_login;
    private static final String LoginURL = "http://lamp.scim.brad.ac.uk:50857/Login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        aEmail = findViewById(R.id.aEmail);
        aPassword = findViewById(R.id.aPassword);
        btn_login = findViewById(R.id.btn_login);
        tvGoReg = findViewById(R.id.tvGoReg);

        tvGoReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

    }

    public void Login(){

        StringRequest request = new StringRequest(StringRequest.Method.POST, LoginURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("success")){
                    Intent intent = new Intent(LoginActivity.this, StoreActivity.class);
                    startActivity(intent);
                    finish();
                }else
                    Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("loginuser", "true");
                    params.put("dEmail", aEmail.getText().toString().trim());
                    params.put("dPassword", aPassword.getText().toString().trim());
                    return params;

                }
        };

        Volley.newRequestQueue(this).add(request);




















    }
}
