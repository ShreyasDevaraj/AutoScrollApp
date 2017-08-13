package com.example.sd034342.autoscrolladds.model;

public class ImageAdd extends Add{
    private int resourceId;

    public ImageAdd(String id, int resId){
        super(id);
        resourceId = resId;
    }

    public int getImageResourceId(){
        return resourceId;
    }
}
