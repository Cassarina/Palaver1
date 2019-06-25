package com.example.palaver20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ContentHandler;
import java.util.ArrayList;

public class FriendListActivity extends AppCompatActivity {
    UserLocalStore userLocalStore;
    private RequestQueue requestQueue;
    ServerController s = new ServerController(FriendListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final LoginActivity loginActivity;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        final TextView friendName = findViewById(R.id.friend_name);
        final TextView test = findViewById(R.id.test1);
        final Button adden = findViewById(R.id.add_button);
        final JSONObject json = new JSONObject();
        final JSONObject json2 = new JSONObject();
        final ArrayList<String> freunde = new ArrayList<>();
       // userLocalStore = new UserLocalStore(getApplicationContext());


        requestQueue = RequestQueueSingleton.getInstance(this.getApplicationContext()).getRequestQueue();

           /* public void onClick(View v) {
                final String url = "/api/friends/add";
                try {
                    json.put("Username", UserLocalStore.user.toString());
                    json.put("Passwort", UserLocalStore.pass.toString());
                    json.put("Friend", friendName.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                s.sendRequest(url, json, requestQueue);
            }*/
            adden.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String url = "/api/friends/add";
                    try{
                        json.put("Username", UserLocalStore.user);
                        json.put("Password", UserLocalStore.pass);
                        json.put("Friend", friendName.getText().toString());
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                    s.sendRequest(url, json, requestQueue);

                }


                });


    }
}
