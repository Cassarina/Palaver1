package com.example.palaver20.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.palaver20.R;
import com.example.palaver20.Server.ServerController;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

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

                s.sendUserdata(url, json);


            }
        });
    }
}