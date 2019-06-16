package com.example.palaver20;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {

    /*
    Klasse kannst man erstmal ignorieren!! Habe versucht damit die UserStory bzgl. dem angemeldet bleiben
    zu realisieren. Hat aber noch nicht so richtig funktioniert :D
     */
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("username", user.name);
        spEditor.putString("passwort", user.passwort);
        spEditor.commit();
    }

    public User getLoggedInUser(){
        String username = userLocalDatabase.getString("username", "");
        String passwort = userLocalDatabase.getString("passwort", "");

        User storedUser = new User(username, passwort);

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("loggedIn", false)== true){
            return true;
        }else{
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}