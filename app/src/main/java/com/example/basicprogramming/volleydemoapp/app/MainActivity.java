package com.example.basicprogramming.volleydemoapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.basicprogramming.volleydemoapp.R;
import com.example.basicprogramming.volleydemoapp.adapter.UserAdapter;
import com.example.basicprogramming.volleydemoapp.configs.AppConfig;
import com.example.basicprogramming.volleydemoapp.configs.Constants;
import com.example.basicprogramming.volleydemoapp.model.Users;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private UserAdapter adapter;
    private ArrayList<Users> usersArrayList;
    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);

        recyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fab.setOnClickListener(this);
        getUserList();
    }

    private void getUserList() {

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
                                users.setFirstName(jsonObject.getString(Constants.KEY_FIRST_NAME));
                                users.setLastName(jsonObject.getString(Constants.KEY_LAST_NAME));
                                users.setEmailId(jsonObject.getString(Constants.KEY_EMAIL_ID));
                                users.setAge(jsonObject.getInt(Constants.KEY_AGE));
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

                    }
                }
        );

        AppConfig.getInstance(this).addToRequestQueue(objectRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this,
                        "Setting Menu Clicked By User",
                        Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        startActivity(new Intent(MainActivity.this, AddActivity.class));
    }
}
