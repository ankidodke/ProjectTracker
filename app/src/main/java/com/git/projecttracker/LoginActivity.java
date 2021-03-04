package com.git.projecttracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int SHOW_SUCCESS = 1;
    private final AppCompatActivity activity = LoginActivity.this;
    EditText edtUserName, edtPassword;
    Button btnLogin;
    TextView txtSignUp;
    InputValidation validation;
    String username, password, error;
    int resultCode;
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == SHOW_SUCCESS) {
                Bundle bundle = msg.getData();
                resultCode = bundle.getInt("ResultCode");
                error = bundle.getString("error");
                if (resultCode == -1) {
                    Toast.makeText(LoginActivity.this, "Error " + resultCode + "\n" + error, Toast.LENGTH_LONG).show();
                } else if (resultCode == 100) {
                    Toast.makeText(LoginActivity.this, "Success " + resultCode, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(activity, DashboardActivity.class));
                }

            }
        }
    };
    UserModel userModel;

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

    private void json() {
        LoginNetworking weatherNetworking = new LoginNetworking(true, LoginActivity.this, handler);
        try {
            weatherNetworking.makeRequestAndInsert(userModel);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
        username = edtUserName.getText().toString().trim();
        password = edtPassword.getText().toString().trim();

        userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(password);

        json();

    }
}