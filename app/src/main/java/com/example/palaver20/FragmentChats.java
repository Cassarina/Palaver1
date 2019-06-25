package com.example.palaver20;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentChats extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    Kontakt kontakt;
    private List<Chat> lstChats;

    public FragmentChats() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_chats, container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.rv_chats);
        RecycleViewAdapterChats recycleViewAdapter = new RecycleViewAdapterChats(getContext(), lstChats);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recycleViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        lstChats = new ArrayList<>();
        lstChats.add(new Chat("Alexandra"));

    }

}