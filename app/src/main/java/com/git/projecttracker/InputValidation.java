package com.git.projecttracker;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

/**
 * Created by Anurag on 23/2/21.
 */
public class InputValidation {
    private final Context context;

    public InputValidation(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isInputEditTextFilled(EditText editText, String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty()) {
            editText.setError(message);
            hideKeyboardFrom(editText);
            return false;
        }
        return true;
    }

    /**
     * method to check InputEditText has valid email .
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isInputEditTextUserName(EditText editText, String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            editText.setError(message);
            hideKeyboardFrom(editText);
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isInputEditTextMatches(EditText editText, String message) {
        String value1 = editText.getText().toString().trim();
        if (value1 == null) {
            editText.setError("Please enter valid password");
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}
