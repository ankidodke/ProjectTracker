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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;
    EditText edtUserName, edtPassword;
    Button btnLogin;
    TextView txtSignUp;
    InputValidation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        validation = new InputValidation(activity);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignUp = findViewById(R.id.txtSignup);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin.setOnClickListener(this);
        txtSignUp.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            validateUser();
        }
        if (view == txtSignUp) {
            startActivity(new Intent(activity, SignupActivity.class));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void validateUser() {
        if (!validation.isInputEditTextUserName(edtUserName, getString(R.string.error_username))) {
            return;
        }
        if (!validation.isInputEditTextMatches(edtPassword, getString(R.string.error_message_password))) {
            return;
        }
        Toast.makeText(activity, "No record found.", Toast.LENGTH_LONG).show();
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));

    }
}