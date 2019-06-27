package com.example.palaver20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Chatroom extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        editText = findViewById(R.id.editText);
    }

    public void sendMessage(View view){
        String message = editText.getText().toString();
        if(message.length() > 0){
            //do something
            editText.getText().clear();
        }
    }
}
