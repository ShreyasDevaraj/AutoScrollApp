package com.example.sd034342.autoscrolladds.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.sd034342.autoscrolladds.fragment.FirstFragment;
import com.example.sd034342.autoscrolladds.fragment.SecondFragment;
import com.example.sd034342.autoscrolladds.fragment.ThirdFragment;
import com.example.sd034342.autoscrolladds.model.Add;
import com.example.sd034342.autoscrolladds.model.HtmlAdd;
import com.example.sd034342.autoscrolladds.model.ImageAdd;
import com.example.sd034342.autoscrolladds.model.VideoAdd;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Add> currentData;

    public ViewPagerAdapter(FragmentManager fm, List<Add> data) {
        super(fm);
        currentData = data;
    }

    @Override
    public Fragment getItem(int position) {
        final Add currentAdd = currentData.get(position);

        switch (position){
            case 0:
                if(currentAdd instanceof ImageAdd){
                    return FirstFragment.newInstance(currentAdd);
                }else if(currentAdd instanceof HtmlAdd){
                    return ThirdFragment.newInstance(currentAdd);
                }else if(currentAdd instanceof VideoAdd){
                    return SecondFragment.newInstance(currentAdd);
                }

            case 1:
                if(currentAdd instanceof ImageAdd){
                    return FirstFragment.newInstance(currentAdd);
                }else if(currentAdd instanceof HtmlAdd){
                    return ThirdFragment.newInstance(currentAdd);
                }else if(currentAdd instanceof VideoAdd){
                    return SecondFragment.newInstance(currentAdd);
                }
            case 2:
                if(currentAdd instanceof ImageAdd){
                    return FirstFragment.newInstance(currentAdd);
                }else if(currentAdd instanceof HtmlAdd){
                    return ThirdFragment.newInstance(currentAdd);
                }else if(currentAdd instanceof VideoAdd){
                    return SecondFragment.newInstance(currentAdd);
                }
            default:
                Log.e("TAG", "unknown index " + position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return currentData.size();
    }

    @Override
    public int getItemPosition(Object item) {
        Log.d("TAG", "notifydata called");
        return POSITION_NONE;
    }

}
