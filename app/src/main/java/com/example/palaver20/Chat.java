package com.example.palaver20;

public class Chat {

    String name;
    String nachricht;

    public Chat(String name){
        this.name = name;
        nachricht = "hallo";
    }

    public String getName(){
        return name;
    }

    public String getNachricht(){
        return nachricht;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setNachricht(String nachricht){
        this.nachricht = nachricht;
    }
}
