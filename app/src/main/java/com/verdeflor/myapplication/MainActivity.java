package com.verdeflor.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Demo user credentials
    private static final String DEMO_USERNAME = "student";
    private static final String DEMO_PASSWORD = "password123";

    private EditText etUsername;
    private EditText etPassword;
    private TextView tvTogglePassword;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvTogglePassword = findViewById(R.id.tvTogglePassword);
        MaterialButton btnLogin = findViewById(R.id.btnLogin);

        // Ensure password is masked by default with asterisks/dots
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        // Password Show / Hide toggle button listener
        if (tvTogglePassword != null) {
            tvTogglePassword.setOnClickListener(v -> togglePasswordVisibility());
        }

        // Log in button click listener
        if (btnLogin != null) {
            btnLogin.setOnClickListener(v -> performLogin());
        }
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // Mask password (asterisks/dots)
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            tvTogglePassword.setText("Show");
            isPasswordVisible = false;
        } else {
            // Show password plain text
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            tvTogglePassword.setText("Hide");
            isPasswordVisible = true;
        }
        if (etPassword.getText() != null) {
            etPassword.setSelection(etPassword.getText().length());
        }
    }

    private void performLogin() {
        String username = etUsername.getText() != null ? etUsername.getText().toString().trim() : "";
        String password = etPassword.getText() != null ? etPassword.getText().toString().trim() : "";

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Please enter your username");
            etUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }

        // Check against Demo User credentials
        if (username.equalsIgnoreCase(DEMO_USERNAME) && Objects.equals(password, DEMO_PASSWORD)) {
            Toast.makeText(this, "Login successful! Welcome to Student Portal.", Toast.LENGTH_SHORT).show();

            // Navigate to Student Page
            Intent intent = new Intent(MainActivity.this, activity_student_page.class);
            intent.putExtra("EXTRA_USERNAME", username);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid credentials. Demo UN: " + DEMO_USERNAME + " | PW: " + DEMO_PASSWORD, Toast.LENGTH_LONG).show();
            etPassword.setError("Invalid username or password");
        }
    }
}

