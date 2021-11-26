package com.example.assignment5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText etUserName;
    private TextInputEditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        btnLogin.setOnClickListener(view -> authenticateUser());
    }

    private void authenticateUser() {
        getCurrentFocus().clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        if (TextUtils.isEmpty(etUserName.getText())) {
            Toast.makeText(this, "UserName cannot be empty!!!", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(etPassword.getText())) {
            Toast.makeText(this, "Password cannot be empty!!!", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (Objects.requireNonNull(etUserName.getText()).toString().trim()
                .equalsIgnoreCase("admin") &&
                Objects.requireNonNull(etPassword.getText()).toString().trim()
                        .equalsIgnoreCase("admin")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Invalid Username or password!!!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void initViews() {
        etUserName = findViewById(R.id.et_user_name);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
    }
}