package com.example.palaver20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    UserLocalStore userLocalStore;
    Boolean logged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLocalStore = new UserLocalStore(getApplicationContext());

        logged = userLocalStore.isLoggedIn();

        if(logged){
            Intent intent = new Intent(MainActivity.this, UserScreen.class);
            startActivity(intent);
        }
        else {
            //SplashScreen anzeigen lassen!!!
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
