package com.example.basicprogramming.volleydemoapp.app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.basicprogramming.volleydemoapp.R;
import com.example.basicprogramming.volleydemoapp.configs.AppConfig;
import com.example.basicprogramming.volleydemoapp.configs.Constants;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private EditText first_name, last_name, email_id, age;
    private MDToast mdToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        first_name = findViewById(R.id.first_name_edit_text);
        last_name = findViewById(R.id.last_name_edit_text);
        email_id = findViewById(R.id.email_id_edit_text);
        age = findViewById(R.id.age_edit_text);

        fab.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addUsers() {

        final String f_name = first_name.getText().toString().trim();
        final String l_name = last_name.getText().toString().trim();
        final String email = email_id.getText().toString().trim();
        final String u_age = age.getText().toString().trim();

        if (f_name.isEmpty()) {
            mdToast = MDToast.makeText(AddActivity.this,
                    "first name is require",
                    MDToast.LENGTH_SHORT, MDToast.TYPE_INFO);
            mdToast.show();
            return;
        }

        if (l_name.isEmpty()) {
            mdToast = MDToast.makeText(AddActivity.this,
                    "last name is require",
                    MDToast.LENGTH_SHORT, MDToast.TYPE_INFO);
            mdToast.show();
            return;
        }

        if (email.isEmpty()) {
            mdToast = MDToast.makeText(AddActivity.this,
                    "email id is require",
                    MDToast.LENGTH_SHORT, MDToast.TYPE_INFO);
            mdToast.show();
            return;
        }

        if (u_age.isEmpty()) {
            mdToast = MDToast.makeText(AddActivity.this,
                    "user age is require",
                    MDToast.LENGTH_SHORT, MDToast.TYPE_INFO);
            mdToast.show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.BASE_REGISTER_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        mdToast = MDToast.makeText(AddActivity.this,
                                "Successfully User Saved",
                                MDToast.LENGTH_SHORT,
                                MDToast.TYPE_SUCCESS);
                        mdToast.show();
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.getMessage();
                        mdToast = MDToast.makeText(AddActivity.this,
                                "Error Info Here",
                                MDToast.LENGTH_SHORT,
                                MDToast.TYPE_ERROR);
                        mdToast.show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put("first_name", f_name);
                params.put("last_name", l_name);
                params.put("email_id", email);
                params.put("age", u_age);

                return params;
            }
        };

        AppConfig.getInstance(this).addToRequestQueue(stringRequest);

        first_name.setText("");
        last_name.setText("");
        email_id.setText("");
        age.setText("");
    }

    @Override
    public void onClick(View v) {
        addUsers();
    }
}
