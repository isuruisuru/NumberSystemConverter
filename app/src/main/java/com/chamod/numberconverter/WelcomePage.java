package com.chamod.numberconverter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        Button loginButton = findViewById(R.id.Loginbtn);
        Button registerButton = findViewById(R.id.Registerbtn);

        loginButton.setOnClickListener(view -> {
            // Create an intent to start the Login activity
            Intent intent = new Intent(WelcomePage.this, LoginPage.class);
            startActivity(intent);
        });

        registerButton.setOnClickListener(view -> {
            // Create an intent to start the Register_Page activity
            Intent intent = new Intent(WelcomePage.this, RegisterPage.class);
            startActivity(intent);
        });
    }
}