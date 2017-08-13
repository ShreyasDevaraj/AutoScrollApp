package com.example.sd034342.autoscrolladds.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sd034342.autoscrolladds.R;
import com.example.sd034342.autoscrolladds.application.AutoScrollAddsApplication;
import com.example.sd034342.autoscrolladds.model.AddStatistics;

import java.util.Map;

public class ImageDetailActivity extends AppCompatActivity {

    private String currentId;
    private long startTime;
    private Button yesButton;
    private Button noButton;
    private long endTime;
    private AddStatistics currentStatistics;
    private Map<String, AddStatistics> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        Intent receivedIntent = getIntent();
        currentId = receivedIntent.getStringExtra("id");
        startTime = System.currentTimeMillis();
        TextView textView = (TextView) findViewById(R.id.imageTextView);
        textView.setTextSize(20.0f);
        yesButton = (Button) findViewById(R.id.imageYes);
        noButton = (Button) findViewById(R.id.imageNo);
        yesButton.setOnClickListener(new ButtonClickListener());
        noButton.setOnClickListener(new ButtonClickListener());
        map = AutoScrollAddsApplication.getInstance().getStatisticsMap();
        if(!map.containsKey(currentId)){
            currentStatistics = new AddStatistics();
        }else {
            currentStatistics = map.get(currentId);
        }
        if(currentId.equals("1")){
            textView.setText("Hey thanks for viewing the add. You have 50 % off if you buy 2. You want to go for it ?");

        } else {
            textView.setText("Hey thanks for viewing the add. We have buy two get one free offer. You want to buy ?");
        }

        currentStatistics.setNumberOfVisits(currentStatistics.getNumberOfVisits()  + 1);
    }

    private class ButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.imageYes:
                    currentStatistics.setNumberOfPositiveResponses(currentStatistics.getNumberOfPositiveResponses() + 1);
                    endTime = System.currentTimeMillis();
                    currentStatistics.setTimeforPositiveResponses((endTime - startTime));
                    map.put(currentId, currentStatistics);
                    finish();
                    break;
                case R.id.imageNo:
                    currentStatistics.setNumberOfNegativeResponses(currentStatistics.getNumberOfNegativeResponses() + 1);
                    endTime = System.currentTimeMillis();
                    currentStatistics.setTimeforNegativeResponses((endTime - startTime));
                    finish();
                    map.put(currentId, currentStatistics);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        map.put(currentId, currentStatistics);
    }
}
