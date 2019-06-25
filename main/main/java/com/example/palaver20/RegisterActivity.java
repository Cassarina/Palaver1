package com.example.palaver20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    ServerController s = new ServerController(RegisterActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final TextView username = findViewById(R.id.regis_name);
        final TextView passwort = findViewById(R.id.regis_passwort);
        final ImageView pal = findViewById(R.id.imageView);
        final Button signin = findViewById(R.id.bt_regis);
        final JSONObject json = new JSONObject();

        requestQueue = RequestQueueSingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = "/api/user/register";
                try{
                    json.put("Username", username.getText().toString());
                    json.put("Password", passwort.getText().toString());
                } catch (JSONException e){
                    e.printStackTrace();
                }

                s.sendRequest(url, json, requestQueue);


            }
        });
    }
}
