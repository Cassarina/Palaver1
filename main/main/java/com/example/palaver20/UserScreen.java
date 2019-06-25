package com.example.palaver20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserScreen extends AppCompatActivity {

    final JSONObject json = new JSONObject();
    private RequestQueue requestQueue;
    ServerController s = new ServerController(UserScreen.this);

    //
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        final Button logout = findViewById(R.id.btn_logout);
        final TextView friend = findViewById(R.id.friends);
        userLocalStore = new UserLocalStore(getApplicationContext());
        ArrayList<String> friendlist = new ArrayList<String>();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLocalStore.logoutUser();
                Log.i("Eingeloggt", userLocalStore.getUserDetails().toString());
                Intent intent = new Intent(UserScreen.this, MainActivity.class);
                startActivity(intent);



            }



        });
        final Button friendListAkt = findViewById(R.id.friendListAkt);
        friendListAkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = "/api/friends/get";
                try {
                    json.put("Username", UserLocalStore.user);
                    json.put("Password", UserLocalStore.pass);
                } catch (JSONException e) {
                    e.printStackTrace();

                }
                s.sendRequest(url, json, requestQueue);

            }
        });

    }
}
