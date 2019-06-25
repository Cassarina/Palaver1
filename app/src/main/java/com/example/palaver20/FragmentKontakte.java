package com.example.palaver20;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.palaver20.Activitys.MainActivity;
import com.example.palaver20.Server.RequestQueueSingleton;
import com.example.palaver20.Server.ServerController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentKontakte extends Fragment {

    View v;
    ServerController s;
    private RecyclerView myrecyclerview;
    public List<Kontakt> lstKontakt;
    Dialog dialog;

    public FragmentKontakte() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_kontakt, container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.rv_kontakte);
        final RecycleViewAdapterKontakte recycleViewAdapterKontakte = new RecycleViewAdapterKontakte(getContext(), lstKontakt);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recycleViewAdapterKontakte);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_add_kontakt);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                final TextView title = dialog.findViewById(R.id.dialog_add_title);
                final TextView namen = dialog.findViewById(R.id.dialog_add_namen);
                final Button add = dialog.findViewById(R.id.dialog_add_add);
                dialog.show();

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ServerController s = new ServerController(getActivity());
                        final JSONObject json = new JSONObject();
                        final RequestQueue requestQueue = RequestQueueSingleton.getInstance(getActivity()).getRequestQueue();
                        final UserLocalStore userLocalStore = new UserLocalStore(getActivity());

                        try{
                            json.put("Username", userLocalStore.getUser());
                            json.put("Password", userLocalStore.getPass());
                            json.put("Friend", namen.getText().toString());
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        s.addFriendRequest(json, requestQueue);
                        lstKontakt.add(new Kontakt(namen.getText().toString()));
                        dialog.cancel();
                        recycleViewAdapterKontakte.notifyItemInserted(lstKontakt.size()+1);
                    }
                });
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstKontakt = new ArrayList<>();
        s = new ServerController(getActivity());
        s.getFriendsListFromServer();
        final UserLocalStore userLocalStore = new UserLocalStore(getActivity());
        String response = userLocalStore.getKeyFriendslistResponse();
        lstKontakt = createKontaktList(response);
        Log.i("Gespeicherter Response", userLocalStore.getKeyFriendslistResponse());
    }

    public List<Kontakt> createKontaktList (String string){
        Log.i("String!", string);
        List<Kontakt> konList = new ArrayList<Kontakt>();
        String k[] = string.split(",");
        for(int i = 0; i < k.length; i++){
            konList.add(new Kontakt(k[i]));
        }
        Log.i("Liste:", konList.toString());

        return konList;
    }
}