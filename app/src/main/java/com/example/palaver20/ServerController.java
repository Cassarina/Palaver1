package com.example.palaver20;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerController {

    private static ServerController sInstance;
    static RequestQueue requestQueue;
    static final String url = "http://palaver.se.paluno.uni-due.de";
    private Activity context;

    public ServerController(Activity Context){
        this.context = Context;
    }
    public static ServerController getsInstance() {
        return sInstance;
    }

    public void sendRequest(String uRl, JSONObject json, RequestQueue requestQueue) {

        RequestQueue req = requestQueue;
        String adr = url+uRl;
        final JsonObjectRequest joreq = new JsonObjectRequest(Request.Method.POST,
                adr, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Antwort:", response.toString());
                        try {
                            if(response.get("MsgType").equals(1)){
                                Intent intent = new Intent(context, UserScreen.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Antwort: ", error.toString());
                    }
                });
        RequestQueueSingleton.getInstance().addToRequestQueue(joreq);

    }
}
