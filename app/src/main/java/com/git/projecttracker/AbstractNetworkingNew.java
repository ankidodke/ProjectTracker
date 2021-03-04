package com.git.projecttracker;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


/**
 * Created by user on 18/4/16.
 */
public abstract class AbstractNetworkingNew implements Response.ErrorListener, Response.Listener<String> {

//    protected static final String BASE_URL  = "http://192.168.0.51:804/";
//    protected static final String BASE_URL  = "http://10.0.2.33:804/";
//    public static final boolean isLive = true;
//    test;

// for stagging
//    protected static final String BASE_URL  = "http://192.168.0.51:804/";
    // for live
//    protected static final String BASE_URL  = "http://54.169.14.5:8085/XBAdminMobileAPIServices/";

    //public static final boolean isLive = true;
//    public static final boolean isLive = false;


    public static final String KEY_AUTH_KEY = "authkey";
    protected static final String RETURN_MSG = "ReturnMessage";
    private static final String CONTENT_TYPE = "application/json";
    private static final String TAG = "abs networking";
    private static final int SHORT_TIMEOUT = 10000;
    private static final int MAX_RETRIRES = 3;

    private final String progressMessage;


    protected JSONObject params;
    protected boolean isForeground;
    protected boolean isSnackBar;
    protected Context context;
    protected int type;
    protected String url;
    protected boolean hasError;
    private ProgressDialog dialog;
    private boolean isShortTimeRetry;

    public AbstractNetworkingNew(boolean isForeground, Context context, int type, String url) {
        this.isForeground = isForeground;
        this.context = context;
        this.type = type;
        this.url = url;
        this.progressMessage = null;
    }

    public AbstractNetworkingNew(boolean isSnackBar, boolean isForeground, Context context, int type, String url) {
        this.isForeground = isForeground;
        this.isSnackBar = isSnackBar;
        this.context = context;
        this.type = type;
        this.url = url;
        this.progressMessage = null;
    }

    public AbstractNetworkingNew(boolean isForeground, Context context, int type, String url, boolean isShortTimeRetry) {
        this.isForeground = isForeground;
        this.context = context;
        this.type = type;
        this.url = url;
        this.progressMessage = null;
        this.isShortTimeRetry = isShortTimeRetry;
    }

    protected abstract void setParams(Object object) throws JSONException;

    public void makeRequestAndInsert(Object obj) throws JSONException {
        setParams(obj);
        if (isForeground)
            if (context instanceof Activity && !((Activity) context).isFinishing())
                dialog = ProgressDialog.show(context, "Loading",
                        progressMessage == null ? "Please wait" : progressMessage, true, false);
        StringRequest request = buildRequest(this, type, url);

        VolleySingleton.getInstance(this.context).addToRequestQueue(request);

    }

    protected abstract void parseJsonAndInsert(String response) throws Exception;

    @Nullable
    protected abstract Map<String, String> getChildHeaders();

   /* //get headers and child headers for device tracking
    private  Map<String, String> getMainHeaders() {
        Map<String, String> headers = new HashMap<>();

        headers.put("IMEI_No",  AppUtils.getIMEI(context));
        headers.put("MAC_address", AppUtils.getMacAddress(context));
        headers.put("Advertising_id", AppUtils.getMobileAdsId(context)); //advertising ID
        headers.put("Secure_device_id", AppUtils.getMobileSecureId(context));
        headers.putAll(getChildHeaders());

        return headers;
    }
*/

    protected StringRequest buildRequest(Response.Listener<String> listner, int type, String url) {
        if (params != null) {
            Log.d("params", params.toString());
        }

        StringRequest request = new StringRequest(type, url, listner, this) {
            @Override
            public byte[] getBody() throws AuthFailureError {
                super.getBody();
                return params.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                super.getBodyContentType();
                return CONTENT_TYPE;
            }


        };

        if (isShortTimeRetry) {
            request.setRetryPolicy(new DefaultRetryPolicy(
                    SHORT_TIMEOUT,
                    MAX_RETRIRES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        } else {
            request.setRetryPolicy(new DefaultRetryPolicy(
                    SHORT_TIMEOUT,
                    MAX_RETRIRES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }

        return request;
    }

    @Override
    public void onResponse(String response) {
        if (params != null) {

            Log.d("response", url + "\n ------------- \n" + params.toString() + "\n ------------- \n" + response);
        }

        try {
            dialog.dismiss();
            JSONObject jsonObject = new JSONObject(response);
            int tokenCode = jsonObject.optInt("tokencode");
            parseJsonAndInsert(response);
        } catch (JSONException ex) {
            try {
                parseJsonAndInsert(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        int errorCode = 0;
        if (error.networkResponse != null)
            errorCode = error.networkResponse.statusCode;
        Log.d("error", "error = " + error);
        if (isForeground) {
            Log.d(TAG, "onErrorResponse: " + error.getLocalizedMessage());
        }


        String mError = "";
        if (error != null)
            mError = error.toString();


        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    protected void disableProgress() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();

    }
}
/*
//frature device traking
aapp util ge mobile id slash sceen
//google*/
