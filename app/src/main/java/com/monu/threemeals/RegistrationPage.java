package com.monu.threemeals;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class RegistrationPage extends AppCompatActivity {
    EditText name, email, phoneno, address, password, cpassword;
    Button login;
    ProgressBar progressBar;
    String url = "http://13.235.250.119/v2/register/fetch_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        name = findViewById(R.id.et_name);
        email = findViewById(R.id.et_email);
        phoneno = findViewById(R.id.et_phoneno);
        address = findViewById(R.id.et_address);
        password = findViewById(R.id.et_password);
        cpassword = findViewById(R.id.et_confirmpassword);
        login = findViewById(R.id.btn_signup);

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        //progressBar = new ProgressBar(RegistrationPage.this);
       // progressBar.setVisibility(View.INVISIBLE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myname = name.getText().toString();
                String myemail = email.getText().toString();
                String myphoneno = phoneno.getText().toString();
                String myaddress = address.getText().toString();
                String mypass = password.getText().toString();
                String mycpass = cpassword.getText().toString();
                if(myname.isEmpty()){
                    Toast.makeText(RegistrationPage.this, "Invalid Name", Toast.LENGTH_SHORT).show();
                }
                else if(myemail.isEmpty() || !myemail.matches(emailPattern)){
                    Toast.makeText(RegistrationPage.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                else if(myphoneno.length() != 10){
                    Toast.makeText(RegistrationPage.this, "Invalid phoneno", Toast.LENGTH_SHORT).show();
                }
                else if(myaddress.isEmpty()){
                    Toast.makeText(RegistrationPage.this, "Invalid address", Toast.LENGTH_SHORT).show();
                }
                else if(mypass.isEmpty() || !mypass.equals(mycpass)){
                    Toast.makeText(RegistrationPage.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    Registerme(myname,myemail,myphoneno,myaddress,mypass);
                }
            }
        });
    }

    private void Registerme(String name, String email, String phoneno, String address, String pass) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject data = new JSONObject();
        try {
            data.put("name",name);
            data.put("mobile_number",phoneno);
            data.put("password",pass);
            data.put("address",address);
            data.put("email",email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               // Toast.makeText(RegistrationPage.this, response.toString(), Toast.LENGTH_LONG).show();
                Log.e("TAG", response.toString());
                try {

                   JSONObject data = response.getJSONObject("data");
                   Log.e("step1","pass");
                    boolean successmsg = data.getBoolean("success");
                    String errorMsg = data.getString("errorMessage");
                    JSONObject data1 = data.getJSONObject("data");
                    Log.e("step2","pass");
                    String user_id = data1.getString("user_id");
                    String name = data1.getString("name");
                    String mobile_number = data1.getString("mobile_number");
                    String address = data1.getString("address");
                    String email = data1.getString("email");

                    if(successmsg){
                        Log.e("step3","pass");

                        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                        sh.edit().putString("user_id",user_id).apply();
                        sh.edit().putString("user_name",name).apply();
                        sh.edit().putString("user_mobile_number",mobile_number).apply();
                        sh.edit().putString("user_address",address).apply();
                        sh.edit().putString("user_email",email).apply();
                        Log.e("sp",sh.getString("user_id",null));
                        Log.e("sp",sh.getString("user_name",null));
                        Log.e("sp",sh.getString("user_mobile_number",null));
                        Log.e("sp",sh.getString("user_address",null));
                        Log.e("sp",sh.getString("user_email",null));
                        //SharedPreferences.Editor editor = sh.edit();
                        //editor.putString("user_id",user_id);
                        //editor.putString("user_name",name);
                        //editor.putString("user_mobile_number", mobile_number);
                        //editor.putString("user_address", address);
                        //editor.putString("user_email",email);
                        //editor.apply();
                        Toast.makeText(RegistrationPage.this, "Registration success!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegistrationPage.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(RegistrationPage.this, errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegistrationPage.this, "Registration error2!", Toast.LENGTH_SHORT).show();

            }
        })
        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> headers = new HashMap<String,String>();
                headers.put("token","a22ec6b2ab5980");
                headers.put("Content-Type","application/json");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);

    }

}