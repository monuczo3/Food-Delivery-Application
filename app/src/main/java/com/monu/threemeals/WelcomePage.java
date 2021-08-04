package com.monu.threemeals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity {
Button login, createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        login = findViewById(R.id.btn_login);
        createAccount = findViewById(R.id.btn_createAccount);
        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        if(sh.contains("user_id") && sh.contains("user_name")){
            Intent intent = new Intent(WelcomePage.this,DashboardActivity.class);
            startActivity(intent);
            finish();
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomePage.this, LoginPage.class);
                startActivity(intent);

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomePage.this,RegistrationPage.class);
                startActivity(intent);
            }
        });



    }
}