package com.example.basicprogramming.volleydemoapp.configs;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AppConfig {

    private static AppConfig mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private AppConfig(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized AppConfig getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AppConfig(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
