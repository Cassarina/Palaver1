package com.example.palaver20;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RecycleViewAdapterChats extends RecyclerView.Adapter<RecycleViewAdapterChats.ViewHolderMy> {

    Context context;
    List<Chat> mData;

    public RecycleViewAdapterChats(Context context, List<Chat> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecycleViewAdapterChats.ViewHolderMy onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.item_chats, parent, false);
        final ViewHolderMy vHolder = new ViewHolderMy(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapterChats.ViewHolderMy viewHolderMy, int i) {

        viewHolderMy.tv_name.setText(mData.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolderMy extends RecyclerView.ViewHolder{

        private LinearLayout item_chats;
        private TextView tv_name;
        private TextView tv_nachricht;
        private TextView tv_uhrzeit;
        private ImageView img;

        public ViewHolderMy(@NonNull View itemView) {
            super(itemView);
            item_chats = (LinearLayout) itemView.findViewById(R.id.chat_item);
            tv_name = (TextView) itemView.findViewById(R.id.name_chat);
            tv_nachricht = (TextView) itemView.findViewById(R.id.nachricht_chat);
            tv_uhrzeit = (TextView) itemView.findViewById(R.id.zeit_chat);
            img = (ImageView) itemView.findViewById(R.id.img_chat);
        }
    }
}
