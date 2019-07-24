package com.tianxing_li.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tianxing_li.expense.loginSys.Login;


public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnBack;
    private Button btnSignUp;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        etEmail = findViewById(R.id.et_login_email);
        etPassword = findViewById(R.id.et_login_password);

        btnLogin = findViewById(R.id.btn_login_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("wd4", "logging in...");
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String result = new Login().login(email, password);
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });

        btnBack = findViewById(R.id.btn_login_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSignUp = findViewById(R.id.btn_login_goto_sign_up);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
