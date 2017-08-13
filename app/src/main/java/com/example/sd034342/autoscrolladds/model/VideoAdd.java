package com.example.sd034342.autoscrolladds.model;


public class VideoAdd extends Add{
    private String URL;

    public VideoAdd(String id, String url){
        super(id);
        URL = url;
    }

    public String getURL(){
        return URL;
    }
}
