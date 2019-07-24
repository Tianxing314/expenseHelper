package com.tianxing_li.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    private Button btnBack;
    private Button btnSignUp;
    private EditText email;
    private EditText password;
    private EditText verifyPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
    }

    private void init() {
        btnBack = findViewById(R.id.btn_signup_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        verifyPassword = findViewById(R.id.passwordVerify);
        btnSignUp = findViewById(R.id.btn_signup_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString();
                String passwordInput = password.getText().toString();
                String passwordVerifyInput = verifyPassword.getText().toString();
                String result = signUp(emailInput, passwordInput, passwordVerifyInput);
            }
        });
    }

    private String signUp(String emailInput, String passwordInput, String passwordVerifyInput) {
        //TODO
        return null;
    }
}
