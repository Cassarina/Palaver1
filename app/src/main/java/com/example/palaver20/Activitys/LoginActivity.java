package com.example.palaver20.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.palaver20.R;
import com.example.palaver20.Server.ServerController;
import com.example.palaver20.UserLocalStore;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;
    ServerController s = new ServerController(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userLocalStore = new UserLocalStore(getApplicationContext());
        final ImageView pal = findViewById(R.id.la_palaver);
        final TextView username = findViewById(R.id.logA_name);
        final TextView passwort = findViewById(R.id.logA_passwort);
        final Button login = findViewById(R.id.btn_la_login);
        final Button signin = findViewById(R.id.btn_la_sigin);
        final JSONObject json = new JSONObject();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String url = "/api/user/validate";
                try{
                    json.put("Username", username.getText().toString());
                    json.put("Password", passwort.getText().toString());
                } catch (JSONException e){
                    e.printStackTrace();
                }
                s.sendUserdata(url, json);
                userLocalStore.setSessionDetails(username.getText().toString(), passwort.getText().toString());
                userLocalStore.createLoginSession(username.getText().toString(), passwort.getText().toString());
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}