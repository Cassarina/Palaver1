package com.example.palaver20.Server;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.palaver20.Activitys.MainActivity;
import com.example.palaver20.UserLocalStore;

import org.json.JSONException;
import org.json.JSONObject;


public class ServerController {

    UserLocalStore userLocalStore;
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

    public void sendUserdata(String uRl, JSONObject json) {
        String adr = url+uRl;
        final JsonObjectRequest joreq = new JsonObjectRequest(Request.Method.POST,
                adr, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Antwort:", response.toString());
                        try {
                            if(response.get("MsgType").equals(1)&&response.get("Info").equals("Benutzer erfolgreich validiert")){
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                            if(response.get("MsgType").equals(1)&&response.get("Info").equals("Benutzer erfolgreich angelegt")){
                                Intent intent = new Intent(context, MainActivity.class);
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

    public void addFriendRequest(final JSONObject json, final String namen){
        String adr = url+ "/api/friends/add";
        final JsonObjectRequest joreq = new JsonObjectRequest(Request.Method.POST,
                adr, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Antwort:", response.toString());
                        try {
                            friendAdd(response, namen);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            if(response.get("MsgType").equals(1)&&response.get("Info").equals("Freund hinzugefügt")) {
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                String temp = "";
                                Log.i("Freund", response.toString());
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

    public void deleteFriend(String uRl, JSONObject json){
        String adr = url+uRl;
        final JsonObjectRequest joreq = new JsonObjectRequest(Request.Method.POST,
                adr, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Antwort:", response.toString());
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

    public void getFriendsListFromServer() {

        userLocalStore = new UserLocalStore(context);
        final JSONObject json = new JSONObject();
        try {
            json.put("Username", userLocalStore.getUser());
            json.put("Password", userLocalStore.getPass());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue = RequestQueueSingleton.getInstance(context).getRequestQueue();
        String adr = url + "/api/friends/get ";
        final JsonObjectRequest joreq = new JsonObjectRequest(Request.Method.POST, adr, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            userLocalStore.sharedResponse(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (response.get("MsgType").equals(1) && response.get("Info").equals("Freunde aufgelistet")) {
                                String temp = response.toString();
                                //temp.substring(temp.indexOf("(") + 1);
                                temp = temp.substring(temp.indexOf("[") + 1, temp.indexOf("]"));
                                temp.split(",");
                                Log.i("Freundesliste:", temp);
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

    public void sendMessage(JSONObject json){
        String adr = url + "/api/message/send ";
        final JsonObjectRequest joreq = new JsonObjectRequest(Request.Method.POST, adr, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Antwort",response.toString());
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

    public void getAllMessages(JSONObject json){
        String adr = url + "/api/message/get ";
        final JsonObjectRequest joreq = new JsonObjectRequest(Request.Method.POST, adr, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Antwort",response.toString());
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

    public void friendAdd(JSONObject jsonObject, String string) throws JSONException {
        userLocalStore = new UserLocalStore(context);
        if(jsonObject.get("MsgType").equals(1)&& jsonObject.get("Info").equals("Freund hinzugefügt")) {
            userLocalStore.updateKontaktList(string);
        }
    }

    public void updatePushtoken(JSONObject json) {
        String adr = url + "/api/user/pushtoken";
        final JsonObjectRequest joreq = new JsonObjectRequest(Request.Method.POST, adr, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Antwort", response.toString());
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.i("Antwort", error.toString());
                    }
                });
        RequestQueueSingleton.getInstance().addToRequestQueue(joreq);
    }

}