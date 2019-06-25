package com.example.palaver20;

import java.util.List;

public class Kontakt {

    private String name;
    List<String> mData;

    public Kontakt(){

    }

    public Kontakt(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addKontakt(String name){
        mData.add(name);
    }
}
