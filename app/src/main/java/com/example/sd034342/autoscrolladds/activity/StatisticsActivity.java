package com.example.sd034342.autoscrolladds.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sd034342.autoscrolladds.R;
import com.example.sd034342.autoscrolladds.application.AutoScrollAddsApplication;
import com.example.sd034342.autoscrolladds.model.AddStatistics;

import java.util.Map;

public class StatisticsActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private TextView id;
    private TextView views;
    private TextView yesCount;
    private TextView avgYesTime;
    private TextView noCount;
    private TextView avgNoTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        tableLayout = (TableLayout) findViewById(R.id.statistics_table);
        populateTable();
    }

    private void populateTable() {
        Map<String, AddStatistics> map = AutoScrollAddsApplication.getInstance().getStatisticsMap();
        int i = 1;
        for (String key : map.keySet()) {
            AddStatistics current = map.get(key);
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            id = new TextView(this);
            views = new TextView(this);
            yesCount = new TextView(this);
            avgYesTime = new TextView(this);
            noCount = new TextView(this);
            avgNoTime = new TextView(this);

            id.setText(key);
            views.setText(String.valueOf(current.getNumberOfVisits()));
            yesCount.setText(String.valueOf(current.getNumberOfPositiveResponses()));
            noCount.setText(String.valueOf(current.getNumberOfNegativeResponses()));
            if (current.getNumberOfPositiveResponses() > 0) {
                double yesTime = (current.getTimeforPositiveResponses() / current.getNumberOfPositiveResponses()) / 1000;
                avgYesTime.setText(String.valueOf(yesTime) + " secs");
            } else {
                avgYesTime.setText("N/A");
            }

            if (current.getNumberOfNegativeResponses() > 0) {
                double noTime = (current.getTimeforNegativeResponses() / current.getNumberOfNegativeResponses()) / 1000;
                avgNoTime.setText(String.valueOf(noTime) + " secs");
            } else {
                avgNoTime.setText("N/A");
            }
            row.addView(id);
            row.addView(views);
            row.addView(yesCount);
            row.addView(avgYesTime);
            row.addView(noCount);
            row.addView(avgNoTime);
            tableLayout.addView(row, i);
            i++;
        }
    }
}
