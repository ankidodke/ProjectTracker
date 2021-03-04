package com.git.projecttracker;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.volley.Request;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Anurag on 4/3/21.
 */
class LoginNetworking extends AbstractNetworkingNew {
    private static final String URL = "https://city-ecom-logistics.herokuapp.com/User/loginUser";
    private static final int TYPE = Request.Method.POST;
    private final Handler handler;
    int resultCode;
    String error;

    public LoginNetworking(boolean isForeground, Context context, Handler handler) {
        super(isForeground, context, TYPE, URL);
        this.handler = handler;
    }

    @Override
    protected void setParams(Object object) throws JSONException {
        UserModel model = (UserModel) object;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", model.getUsername());
        jsonObject.put("password", model.getPassword());
        params = jsonObject;
    }

    @Override
    protected void parseJsonAndInsert(String response) throws Exception {
        JSONObject mainObject = new JSONObject(response);
        UserModel model = new UserModel();
        resultCode = mainObject.getInt("resultCode");
        error = mainObject.getString("error");

    }

    @Override
    public void onResponse(String response) {
        super.onResponse(response);
        Message message = handler.obtainMessage();
        Bundle bundle = message.getData();
        bundle.putInt("ResultCode", resultCode);
        bundle.putString("error", error);
        message.what = LoginActivity.SHOW_SUCCESS;
        handler.sendMessage(message);
    }

    @Override
    protected @Nullable Map<String, String> getChildHeaders() {
        return null;
    }
}
