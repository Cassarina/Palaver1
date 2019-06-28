package com.example.palaver20.Chatliste;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.palaver20.Activitys.Chatroom;
import com.example.palaver20.R;
import com.example.palaver20.UserLocalStore;

import java.util.List;

public class RecycleViewAdapterChats extends RecyclerView.Adapter<RecycleViewAdapterChats.ViewHolderMy> {

    private List<ChatObject> mData;
    UserLocalStore userLocalStore;

    public RecycleViewAdapterChats(List<ChatObject> mData) {
        this.mData = mData;

    }

    @NonNull
    @Override
    public RecycleViewAdapterChats.ViewHolderMy onCreateViewHolder(@NonNull final ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chats, parent, false);
        final ViewHolderMy vHolder = new ViewHolderMy(v);
        userLocalStore = new UserLocalStore(parent.getContext());
        vHolder.item_chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), Chatroom.class);
                intent.putExtra("Name", vHolder.tv_name.getText());
                parent.getContext().startActivity(intent);
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapterChats.ViewHolderMy viewHolderMy, int i) {

        ChatObject chat = mData.get(i);
        viewHolderMy.tv_name.setText(chat.getName());
        viewHolderMy.tv_nachricht.setText(chat.getLastMessage());
        viewHolderMy.tv_uhrzeit.setText(chat.getTime());
    }

    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }else
            return mData.size();
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
