package com.example.palaver20;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class UserLocalStore {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context ctx;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidHivePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORT = "passwort";
    public static final String KEY_FRIEND = "friend";
    public static String user;
    public static String pass;

    public UserLocalStore(Context context){
        this.ctx = context;
        pref = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession( String name, String passwort){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_PASSWORT, passwort);
        editor.putString(KEY_USERNAME, name);
        editor.commit();
    }

    public void  setSessionDetails (String name, String passwort){
        user=name;
        pass=passwort;
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(ctx,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);
        }
    }

    public HashMap<String,String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        user.put(KEY_PASSWORT, pref.getString(KEY_PASSWORT, null));
        return user;
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser(){
        editor.putBoolean(IS_LOGIN, false);
        editor.clear();
        editor.commit();
    }


}
