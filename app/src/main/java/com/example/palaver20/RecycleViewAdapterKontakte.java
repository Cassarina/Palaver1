package com.example.palaver20;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.palaver20.Activitys.MainActivity;
import com.example.palaver20.Server.RequestQueueSingleton;
import com.example.palaver20.Server.ServerController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterKontakte extends RecyclerView.Adapter<RecycleViewAdapterKontakte.MyViewHolder> {

    Context context;
    List<Kontakt> mData;
    Dialog dialog;
    Dialog dialog2;

    public RecycleViewAdapterKontakte(Context context, List<Kontakt> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_kontakte, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_kontakt);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_kontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialog_name_tv = (TextView) dialog.findViewById(R.id.dialog_name);
                Button dialog_nachricht_btn = (Button) dialog.findViewById(R.id.dialog_nachricht);
                Button dialog_löschen_btn = (Button) dialog.findViewById(R.id.dialog_löschen);
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialog.show();

                dialog_löschen_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "/api/friends/remove";

                        final ServerController s = new ServerController((Activity) context);
                        final JSONObject json = new JSONObject();
                        final RequestQueue requestQueue = RequestQueueSingleton.getInstance(context).getRequestQueue();
                        final UserLocalStore userLocalStore = new UserLocalStore(context);

                        try{
                            json.put("Username", userLocalStore.getUser());
                            json.put("Password", userLocalStore.getPass());
                            json.put("Friend", mData.get(vHolder.getAdapterPosition()).getName());
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        s.deleteFriend(url, json, requestQueue);
                        dialog.cancel();
                        notifyItemRemoved(vHolder.getAdapterPosition());
                    }
                });
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapterKontakte.MyViewHolder myViewHolder, int i) {

        myViewHolder.tv_name.setText(mData.get(i).getName());
    }

    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }else
            return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_kontakt;
        private TextView tv_name;
        private ImageView img;
        private Button add;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_kontakt = (LinearLayout) itemView.findViewById(R.id.kontakt_item);
            tv_name = (TextView) itemView.findViewById(R.id.name_kontakt);
            img = (ImageView) itemView.findViewById(R.id.img_kontakt);
            add = (Button) itemView.findViewById(R.id.addFriend);
        }
    }

}
