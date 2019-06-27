package com.example.palaver20;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.palaver20.Activitys.LoginActivity;
import com.example.palaver20.Server.ServerController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserLocalStore {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context ctx;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidHivePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORT = "passwort";
    public static final String KEY_FRIENDSLIST_RESPONSE = "response";
    public static final String KEY_KONTAKT_LISTE ="kontaktliste";
    public static String user;
    public static String pass;
    ServerController s;
    Gson gson = new Gson();
    String json;

    public UserLocalStore(Context context){
        this.ctx = context;
        pref = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String passwort){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_PASSWORT, passwort);
        editor.putString(KEY_USERNAME, name);
        editor.putString(KEY_KONTAKT_LISTE, json);
        editor.commit();
    }

    public void  setSessionDetails (String name, String passwort){
        user=name;
        pass=passwort;
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
        //kontaktliste.clear();
    }

    public String getUser(){
        return pref.getString(KEY_USERNAME, null);
    }

    public String getKeyFriendslistResponse(){
        return pref.getString(KEY_FRIENDSLIST_RESPONSE, null);
    }

    public String getPass(){
        return pref.getString(KEY_PASSWORT, null);
    }

    public void sharedResponse(JSONObject jsonObject) throws JSONException {
        String temp = jsonObject.getString("Data");
        //temp.substring(temp.indexOf("(") + 1);
        temp = temp.replace("[","");
        temp = temp.replaceAll("\"", "");
        Log.i("Zu Speichern", temp);
        json = gson.toJson(temp);
        editor.putString(KEY_FRIENDSLIST_RESPONSE, temp);
        editor.commit();
    }

    public List<String> createKontaktListe(){
        String kontakte [] = pref.getString(KEY_FRIENDSLIST_RESPONSE, "").split(",");
        List<String> kontaktliste = new ArrayList<>();
        for(int i = 0; i < kontakte.length; i++){
            kontaktliste.add(i, kontakte[i]);
            Log.i("Kontakt "+ i,kontaktliste.get(i));
        }
        return kontaktliste;
    }

    public void updateList(String namen){
        String s = KEY_FRIENDSLIST_RESPONSE;
        String neu = s +", " + namen;
        editor.remove(KEY_FRIENDSLIST_RESPONSE);
        editor.putString(KEY_FRIENDSLIST_RESPONSE, neu);
        editor.commit();
    }



}