package com.example.palaver20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.android.volley.RequestQueue;


public class MainActivity extends AppCompatActivity {

    /*
    Der hier auskommentierte Code kannst du ignorieren. Der gehört zu den beiden Klassen UserLocalStore
    und User
     */
    private RequestQueue requestQueue;
    ServerController s = new ServerController(MainActivity.this);
    //UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView username = findViewById(R.id.log_name);
        final TextView passwort = findViewById(R.id.log_passwort);
        final ImageView pal = findViewById(R.id.palaver);
        final Button login = findViewById(R.id.bt_login);
        final Button signin = findViewById(R.id.bt_signin);
        final JSONObject json = new JSONObject();

        //userLocalStore = new UserLocalStore(this);
        requestQueue = RequestQueueSingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = "/api/user/validate";
                boolean success = false;
                try{
                    json.put("Username", username.getText().toString());
                    json.put("Password", passwort.getText().toString());
                } catch (JSONException e){
                    e.printStackTrace();
                }

                s.sendRequest(url, json, requestQueue);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /*@Override
    public void onStart(){
        Button login = findViewById(R.id.bt_login);
        super.onStart();
        if(authenticate()==true){
            displayUserDetails();
        }
    }
    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails(){
       TextView username = findViewById(R.id.log_name);
       TextView passwort = findViewById(R.id.log_passwort);

       User user = userLocalStore.getLoggedInUser();
       username.setText(user.name);
       passwort.setText(user.passwort);
    } */
}
