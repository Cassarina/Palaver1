package com.example.palaver20;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private View tv1, tv2;
    UserLocalStore userLocalStore;
    Boolean logged;
    boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                tv1 = findViewById(R.id.fr1);
                tv2 = findViewById(R.id.fr2);

                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addFragment(new FragmentOne(),false,"one");
                    }
                });

                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addFragment(new FragmentTwo(),false,"two");
                    }
                });
            }

        }).start();



        userLocalStore = new UserLocalStore(getApplicationContext());

        logged = userLocalStore.isLoggedIn();
        if (running)
        {
            View view = findViewById(R.id.friendListButton);
            final Intent intentFriendList = new Intent(MainActivity.this, FriendListActivity.class);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(v.getId()){
                        case R.id.friendListButton:
                            startActivity(intentFriendList);

                    }
                }

            });
        }

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

    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.frame_container, fragment, tag);
        ft.commitAllowingStateLoss();
    }
}

