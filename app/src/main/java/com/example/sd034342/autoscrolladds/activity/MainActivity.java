package com.example.sd034342.autoscrolladds.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sd034342.autoscrolladds.R;
import com.example.sd034342.autoscrolladds.adapter.ViewPagerAdapter;
import com.example.sd034342.autoscrolladds.model.Add;
import com.example.sd034342.autoscrolladds.model.HtmlAdd;
import com.example.sd034342.autoscrolladds.model.ImageAdd;
import com.example.sd034342.autoscrolladds.model.VideoAdd;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView textView;
    private static long dataResetTime = 2 * 60 * 1000;
    private static long pageRefreshTime = (2 * 60 * 1000) / 3;
    private List<List<Add>> dataset = new ArrayList<>();
    private Handler mHandler;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        textView = (TextView) findViewById(R.id.dataset);
        fetchData();
        mHandler = new Handler();
        startDisplay();
        startDataRestTimer();
    }

    private void startDisplay() {
        scrollViewPager.run();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    private void startDataRestTimer() {
        mStatusChecker.run();
    }

    Runnable scrollViewPager = new Runnable() {
        @Override
        public void run() {
            try {
                if (viewPager.getCurrentItem() == 0) {
                    viewPager.setCurrentItem(1, true);
                } else if (viewPager.getCurrentItem() == 1) {
                    viewPager.setCurrentItem(2, true);
                } else {
                    viewPager.setCurrentItem(0, true);
                }
            } finally {
                mHandler.postDelayed(scrollViewPager, pageRefreshTime);
            }
        }
    };

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                count ++;
                Log.d("TAG", "current data set " + count % 2);
                int pageIndex = (count%2) +1;
                textView.setText("Add set : " + pageIndex);
                viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), dataset.get(count % 2)));
                viewPager.getAdapter().notifyDataSetChanged();
                mHandler.removeCallbacks(scrollViewPager);
            } finally {
                mHandler.postDelayed(mStatusChecker, dataResetTime);
                mHandler.postDelayed(scrollViewPager, pageRefreshTime);
            }
        }
    };

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
        mHandler.removeCallbacks(scrollViewPager);
    }


    private void fetchData() {
        ArrayList<Add> dataOne = new ArrayList<>();
        Add image = new ImageAdd("1", R.drawable.coke_ad);
        dataOne.add(image);
        Add video = new VideoAdd("2","rtsp://r2---sn-vgqsrnes.googlevideo.com/Cj0LENy73wIaNAkfv11iNBZsJBMYDSANFC1HFpJZMOCoAUIASARgltWj-dSezZ5ZigELZ05VYWxvYWhKT1EM/AECFD7AAB00C341D1F53A88FAF318BB28E42E146.A7451B240B8AEF7BE199EECCDFEB7D639E2E692A/yt6/1/video.3gp");
        dataOne.add(video);
        Add html = new HtmlAdd("3", "file:///android_asset/sampleOne.html");
        dataOne.add(html);

        ArrayList<Add> dataTwo = new ArrayList<>();
        Add image1 = new ImageAdd("4", R.drawable.pepsi);
        dataTwo.add(image1);
        Add video1 = new VideoAdd("5","rtsp://r16---sn-vgqs7nek.googlevideo.com/Cj0LENy73wIaNAlbPag5jOVcPRMYDSANFC2YFZJZMOCoAUIASARgltWj-dSezZ5ZigELZ05VYWxvYWhKT1EM/CD07138A81C2215E01241E825748A7DCCD3F5198.393F84A9BC8F54FD286EF4F3045C4FD55E8089C5/yt6/1/video.3gp");
        dataTwo.add(video1);
        Add html1 = new HtmlAdd("6", "file:///android_asset/sample.html");
        dataTwo.add(html1);

        dataset.add(dataOne);
        dataset.add(dataTwo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.statistics:
                Intent intent = new Intent(this, StatisticsActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
