# Volley Android
Volley automatically schedule all network requests. It means that Volley will be taking care of all the network requests your app executes for fetching response or image from web. Volley provides transparent disk and memory caching. Volley provides powerful cancellation request API

## Volley Dependency
Volley to your project is to add the following dependency to your app's build.gradle file

```build.gradle

    implementation 'com.android.volley:volley:1.1.1'
    
```

## Use Singleton Pattern Configration Volley AppConfig.java
    
```java 

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


```
