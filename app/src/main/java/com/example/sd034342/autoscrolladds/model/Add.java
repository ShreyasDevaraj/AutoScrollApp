package com.example.sd034342.autoscrolladds.model;

public abstract class Add {
    protected String id;

    public Add(String addId){
        this.id = addId;
    }

    public String getId(){
        return id;
    }
}
