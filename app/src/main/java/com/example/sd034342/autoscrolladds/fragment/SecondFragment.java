package com.example.sd034342.autoscrolladds.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import com.example.sd034342.autoscrolladds.R;
import com.example.sd034342.autoscrolladds.activity.VideoDetailActivity;
import com.example.sd034342.autoscrolladds.model.Add;
import com.example.sd034342.autoscrolladds.model.VideoAdd;


public class SecondFragment extends Fragment {
    VideoView videoView;
    VideoAdd currentVideo;
    String URL;
    public static Fragment newInstance(Add addName){
        SecondFragment videoFragment = new SecondFragment();
        videoFragment.currentVideo = (VideoAdd) addName;
        videoFragment.URL = videoFragment.currentVideo.getURL();
        return videoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUserVisibleHint(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.second_fragment_layout, container, false);
        videoView = rootView.findViewById(R.id.videoView);
        videoView.setOnTouchListener(new VideoTouchListener());
        return rootView;
    }

    private class VideoTouchListener implements View.OnTouchListener{
        private int CLICK_ACTION_THRESHOLD = 200;
        private float startX;
        private float startY;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = event.getX();
                    startY = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    float endX = event.getX();
                    float endY = event.getY();
                    if (isAClick(startX, endX, startY, endY)) {
                        if (videoView != null)
                            videoView.stopPlayback();
                        Intent intent = new Intent(getActivity(), VideoDetailActivity.class);
                        intent.putExtra("id", currentVideo.getId());
                        getContext().startActivity(intent);
                    }
                    break;
            }
           return true;
        }
        private boolean isAClick(float startX, float endX, float startY, float endY) {
            float differenceX = Math.abs(startX - endX);
            float differenceY = Math.abs(startY - endY);
            return !(differenceX > CLICK_ACTION_THRESHOLD|| differenceY > CLICK_ACTION_THRESHOLD);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(videoView != null && getUserVisibleHint()){
            Uri uri= Uri.parse(URL);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if(videoView != null){
            videoView.pause();
        }
    }
    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible) {
            Uri uri= Uri.parse(URL);
            if(videoView != null){
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();
            }
        } else {
            if (videoView != null)
                videoView.pause();
        }
    }

}
