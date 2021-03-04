package com.git.projecttracker;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by user on 18/4/16.
 */
public class VolleySingleton {
    private static com.git.projecttracker.VolleySingleton mInstance;
    private static Context mCtx;
    private RequestQueue mRequestQueue;

    private VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }


    public static synchronized com.git.projecttracker.VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new com.git.projecttracker.VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setShouldCache(false);
        if (getRequestQueue().getCache() != null)
            getRequestQueue().getCache().clear();
        getRequestQueue().add(req);
    }
}