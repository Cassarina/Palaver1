package com.example.palaver20.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.palaver20.Chatliste.ChatObject;
import com.example.palaver20.R;
import com.example.palaver20.Server.ServerController;
import com.example.palaver20.UserLocalStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Chatroom extends AppCompatActivity {

    private EditText editText;
    private ImageButton send;
    UserLocalStore userLocalStore;
    ServerController s;
    Intent intent;
    String name;
    List<ChatObject> chatliste = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        intent = getIntent();
        name = getIntent().getStringExtra("Name");
        userLocalStore = new UserLocalStore(this);
        s = new ServerController(this);
        editText = findViewById(R.id.editText);
        send = findViewById(R.id.btn_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendMessage(v);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendMessage(View view) throws JSONException {
        String message = editText.getText().toString();
        JSONObject jsonObject = new JSONObject();
        if(message.length() > 0){
            jsonObject.put("username",userLocalStore.getUser());
            jsonObject.put("password", userLocalStore.getPass());
            jsonObject.put("Recipient", name);
            jsonObject.put("Mimetype", "text/plain");
            jsonObject.put("Data", message);
            s.sendMessage(jsonObject);
            editText.getText().clear();
        }
    }


}
