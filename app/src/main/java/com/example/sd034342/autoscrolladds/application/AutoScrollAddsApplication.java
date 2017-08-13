package com.example.sd034342.autoscrolladds.application;

import android.app.Application;
import com.example.sd034342.autoscrolladds.model.AddStatistics;

import java.util.HashMap;
import java.util.Map;


public class AutoScrollAddsApplication extends Application {
    private static AutoScrollAddsApplication instance;
    private Map<String, AddStatistics> statisticsMap = new HashMap<>();

    public static AutoScrollAddsApplication getInstance(){
        if(instance == null){
            instance = new AutoScrollAddsApplication();
        }
        return instance;
    }

    public Map<String, AddStatistics> getStatisticsMap(){
        return statisticsMap;
    }

}
