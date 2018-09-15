# Volley Android
Volley automatically schedule all network requests. It means that Volley will be taking care of all the network requests your app executes for fetching response or image from web. Volley provides transparent disk and memory caching. Volley provides powerful cancellation request API

## Volley Dependency
Volley to your project is to add the following dependency to your app's build.gradle file

```build.gradle

    implementation 'com.android.volley:volley:1.1.1'
    
```

## App Images

<p align="center">

  <img src="https://github.com/apppath/volleydemoapp/blob/master/device-home-activity.png" width="250"/>
  <img src="https://github.com/apppath/volleydemoapp/blob/master/device-insert-activity.png" width="250"/>
  <img src="https://github.com/apppath/volleydemoapp/blob/master/device-valid-activity.png" width="250"/>

</p>


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

## Send a simple request

```java
  
StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.BASE_REGISTER_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(AddActivity.this,
                                "Successfully User Saved",
                                Toast.LENGTH_SHORT).show;
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getMessage();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put(Key,Value);
                return params;
            }
        };

        AppConfig.getInstance(this).addToRequestQueue(stringRequest);
```
## Request JSON

```java

JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, Constants.BASE_USER_LIST_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            usersArrayList = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray(Constants.KEY_JSON_ARRAY_ROOT_ITEM);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                users = new Users();

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                users.setFirstName(jsonObject.getString(JSON_KEY));
                                users.setAge(jsonObject.getInt(JSON_KEY));
                                usersArrayList.add(users);

                            }

                            adapter = new UserAdapter(MainActivity.this, usersArrayList);
                            recyclerView.setAdapter(adapter);

                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     error.getMessage();
                    }
                }
        );

        AppConfig.getInstance(this).addToRequestQueue(objectRequest);

```
