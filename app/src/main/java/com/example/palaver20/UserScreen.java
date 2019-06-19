package com.example.palaver20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserScreen extends AppCompatActivity {

    //
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        final Button logout = findViewById(R.id.btn_logout);
        userLocalStore = new UserLocalStore(getApplicationContext());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLocalStore.logoutUser();
                Log.i("Eingeloggt", userLocalStore.getUserDetails().toString());
                Intent intent = new Intent(UserScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
