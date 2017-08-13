package com.example.sd034342.autoscrolladds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.sd034342.autoscrolladds.R;
import com.example.sd034342.autoscrolladds.activity.HtmlDetailActivity;
import com.example.sd034342.autoscrolladds.activity.VideoDetailActivity;
import com.example.sd034342.autoscrolladds.model.Add;
import com.example.sd034342.autoscrolladds.model.HtmlAdd;

public class ThirdFragment extends Fragment {

    private WebView webView;
    private HtmlAdd currentHtml;
    private String htmlContent;

    public static Fragment newInstance(Add name){
        ThirdFragment htmlFragment = new ThirdFragment();
        htmlFragment.currentHtml = (HtmlAdd) name;
        htmlFragment.htmlContent = htmlFragment.currentHtml.getPathName();
        return htmlFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.third_fragment_layout, container, false);
        webView = rootView.findViewById(R.id.webView);
        webView.loadUrl(htmlContent);
        webView.setOnTouchListener(new HtmlPageTouchListener());
        return rootView;
    }

    private class HtmlPageTouchListener implements View.OnTouchListener{
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
                        Intent intent = new Intent(getActivity(), HtmlDetailActivity.class);
                        intent.putExtra("id", currentHtml.getId());
                        getContext().startActivity(intent);
                    }
                    break;
            }
            return true;
        }
        private boolean isAClick(float startX, float endX, float startY, float endY) {
            float differenceX = Math.abs(startX - endX);
            float differenceY = Math.abs(startY - endY);
            return !(differenceX > CLICK_ACTION_THRESHOLD || differenceY > CLICK_ACTION_THRESHOLD);
        }
    }

}
