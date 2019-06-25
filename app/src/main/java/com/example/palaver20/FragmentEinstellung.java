package com.example.palaver20;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.palaver20.Activitys.LoginActivity;
import com.example.palaver20.Activitys.MainActivity;


public class FragmentEinstellung extends Fragment {

    UserLocalStore userLocalStore;
    public FragmentEinstellung() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit, container, false);
        Button changePw = v.findViewById(R.id.btn_change_pw);
        Button logout = v.findViewById(R.id.btn_logput);

        userLocalStore = new UserLocalStore(getContext());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLocalStore.logoutUser();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }

}