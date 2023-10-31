package com.chamod.numberconverter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterPage extends AppCompatActivity {

    private EditText fullNameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button signupButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        fullNameEditText = findViewById(R.id.fullname);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        signupButton = findViewById(R.id.Signupbtn);

        dbHelper = new DBHelper(this);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = fullNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Validate full name
                if (fullName.isEmpty()) {
                    fullNameEditText.setError("Full name is required");
                    return;
                }

                // Validate email
                if (email.isEmpty()) {
                    emailEditText.setError("Email is required");
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailEditText.setError("Invalid email format");
                    return;
                }

                // Validate password
                if (password.isEmpty()) {
                    passwordEditText.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    passwordEditText.setError("Password must be at least 6 characters long");
                    return;
                }

                // Validate confirm password
                if (!confirmPassword.equals(password)) {
                    confirmPasswordEditText.setError("Passwords do not match");
                    return;
                }

                if (dbHelper.addUser(fullName, email, password, confirmPassword)) {
                    Toast.makeText(RegisterPage.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterPage.this, WelcomePage.class);
                    startActivity(intent);
                    finish(); // Close signup activity
                } else {
                    Toast.makeText(RegisterPage.this, "Signup failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}