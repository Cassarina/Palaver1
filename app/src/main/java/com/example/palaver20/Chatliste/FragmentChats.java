package com.example.palaver20.Chatliste;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.palaver20.R;
import com.example.palaver20.UserLocalStore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FragmentChats extends Fragment {

    private View v;
    private RecyclerView myrecyclerview;
    UserLocalStore userLocalStore;
    private List<ChatObject> chatObjectList;
    private Gson gson;


    public FragmentChats() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gson = new Gson();
        userLocalStore = new UserLocalStore(getContext());
        final RecycleViewAdapterChats recycleViewAdapterChats = new RecycleViewAdapterChats(getChatListFromUserLocalStore());
        v = inflater.inflate(R.layout.fragment_chats, container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.rv_chats);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recycleViewAdapterChats);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    private void saveChatListToUserLocalStore(List<ChatObject> chatObjectList){

        String jsonChatList = gson.toJson(chatObjectList);
        userLocalStore.saveChatList(jsonChatList);
    }

    private List<ChatObject> getChatListFromUserLocalStore(){
        String jsonChatList = userLocalStore.getChatList();
        //String json[] = jsonChatList.split(",");
        //Type type = new TypeToken<List<ChatObject>>(){}.getType();
        //chatObjectList = gson.fromJson(jsonChatList, type);

        chatObjectList = new ArrayList<>();
        String chat [] = jsonChatList.split(",");
        for(int i = 0; i<chat.length; i++){
            chatObjectList.add(i, new ChatObject(chat[i],"blubb","12:00"));
        }
        if(chatObjectList == null){
            chatObjectList = new ArrayList<>();
        }
        return chatObjectList;
    }

}