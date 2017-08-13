package com.example.sd034342.autoscrolladds.model;

public class HtmlAdd extends Add{
    private String pathName;

    public HtmlAdd(String id, String path){
        super(id);
        pathName = path;
    }

    public String getPathName(){
        return pathName;
    }
}
