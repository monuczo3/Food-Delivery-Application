package com.monu.threemeals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {
TextView forgotPassword,signup;
EditText PhoneNo, Password;
Button login;
    String url = "http://13.235.250.119/v2/login/fetch_result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        PhoneNo = findViewById(R.id.et_phoneno2);
        Password = findViewById(R.id.et_password2);
        forgotPassword = findViewById(R.id.txt_forgotPass);
        signup = findViewById(R.id.txt_signup);
        login = findViewById(R.id.btn_login2);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    Loginme(PhoneNo.getText().toString(), Password.getText().toString());
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginPage.this, RegistrationPage.class);
                startActivity(intent);
                finish();
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, forgotPassword.class);
                startActivity(intent);
            }
        });

        
        
    }

    private void Loginme(String phoneno, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject data  = new JSONObject();
        try {
            data.put("mobile_number",phoneno);
            data.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("TAG",response.toString());
                    JSONObject data = response.getJSONObject("data");
                    boolean successmsg = data.getBoolean("success");
                    JSONObject data2 = data.getJSONObject("data");

                    if(successmsg){

                        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                        sh.edit().putString("user_id",data2.getString("user_id")).apply();
                        sh.edit().putString("user_name",data2.getString("name")).apply();
                        sh.edit().putString("user_mobile_number",data2.getString("mobile_number")).apply();
                        sh.edit().putString("user_address",data2.getString("address")).apply();
                        sh.edit().putString("user_email",data2.getString("email")).apply();
                        Log.e("sp",sh.getString("user_id",null));
                        Log.e("sp",sh.getString("user_name",null));
                        Log.e("sp",sh.getString("user_mobile_number",null));
                        Log.e("sp",sh.getString("user_address",null));
                        Log.e("sp",sh.getString("user_email",null));
                        Intent intent = new Intent(LoginPage.this,DashboardActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else{
                        Toast.makeText(LoginPage.this, "Login failed!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginPage.this, "Login failed2!", Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("token","a22ec6b2ab5980");
                headers.put("Content-Type","application/json");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }


    private boolean validate() {
        boolean valid = true;
        String phoneno = PhoneNo.getText().toString();
        String password = Password.getText().toString();
        if(phoneno.isEmpty() || phoneno.length()!=10){
            Toast.makeText(this, "Phone no invalid!", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if(password.isEmpty() || password.length()<4 || password.length()>20){
            Toast.makeText( this,"Password invalid!", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }


}