package com.example.palaver20.Activitys;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.widget.Toolbar;


import com.example.palaver20.Chatliste.FragmentChats;
import com.example.palaver20.FragmentEinstellung;
import com.example.palaver20.Kontaktliste.FragmentKontakte;
import com.example.palaver20.PagerAdapter;
import com.example.palaver20.R;
import com.example.palaver20.Server.ServerController;
import com.example.palaver20.UserLocalStore;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    UserLocalStore userLocalStore;
    ServerController serverController;
    Boolean logged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        serverController = new ServerController(MainActivity.this);
        serverController.getFriendsListFromServer();
        userLocalStore = new UserLocalStore(getApplicationContext());
        logged = userLocalStore.isLoggedIn();
        Log.i("Nutzer", userLocalStore.getUserDetails().toString());
        if(!logged){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Chats"));
        tabLayout.addTab(tabLayout.newTab().setText("Kontakte"));
        tabLayout.addTab(tabLayout.newTab().setText("Einstellungen"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        adapter.addFragment(new FragmentChats(), "Chats");
        adapter.addFragment(new FragmentKontakte(), "Kontakte");
        adapter.addFragment(new FragmentEinstellung(), "Einstellungen");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}