package com.git.projecttracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = SignupActivity.this;
    EditText edtName, edtUserName, edtPassword;
    Button btnSignup;
    TextView txtLogin;
    InputValidation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        validation = new InputValidation(activity);
        btnSignup = findViewById(R.id.btnSignUp);
        edtName = findViewById(R.id.edtSName);
        edtUserName = findViewById(R.id.edtSignUserName);
        edtPassword = findViewById(R.id.edtSignPassword);
        txtLogin = findViewById(R.id.txtLogin);

        btnSignup.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        if (view == btnSignup) {
            validateUser();
        }
        if (view == txtLogin) {
            startActivity(new Intent(activity, LoginActivity.class));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void validateUser() {
        if (validation.isInputEditTextFilled(edtName, getString(R.string.error_name))) {
            return;
        }
        if (!validation.isInputEditTextUserName(edtUserName, getString(R.string.error_username))) {
            return;
        }
        if (!validation.isInputEditTextMatches(edtPassword, getString(R.string.error_message_password))) {
            return;
        }
        //
        else {
            Toast.makeText(activity, "No record found.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(activity, LoginActivity.class));
        }
    }
}