package com.git.projecttracker;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = SignupActivity.this;
    EditText edtName, edtUserName, edtPassword;
    Button btnSignup;
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
        btnSignup.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        if (view == btnSignup) {
            validateUser();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void validateUser() {
        if (!validation.isInputEditTextFilled(edtName, getString(R.string.error_name))) {
            return;
        }
        if (!validation.isInputEditTextFilled(edtUserName, getString(R.string.error_username))) {
            return;
        }
        if (!validation.isInputEditTextMatches(edtPassword, getString(R.string.error_message_password))) {
            return;
        }
        //
        else {
            Toast.makeText(activity, "No record found.", Toast.LENGTH_LONG).show();
        }
    }
}