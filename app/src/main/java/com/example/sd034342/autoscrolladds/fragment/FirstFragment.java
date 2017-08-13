package com.example.sd034342.autoscrolladds.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sd034342.autoscrolladds.R;
import com.example.sd034342.autoscrolladds.activity.ImageDetailActivity;
import com.example.sd034342.autoscrolladds.model.Add;
import com.example.sd034342.autoscrolladds.model.ImageAdd;


public class FirstFragment extends Fragment {

    private ImageView imageView;
    private ImageAdd currentAdd;

    public static Fragment newInstance(Add addName){
        FirstFragment imageFragment = new FirstFragment();
        Log.d("TAG", addName.toString());
        imageFragment.currentAdd = (ImageAdd) addName;
        return imageFragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.first_fragment_layout, container, false);
        imageView = rootView.findViewById(R.id.firstImage);
        if(currentAdd != null)
        imageView.setImageResource(currentAdd.getImageResourceId());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ImageDetailActivity.class);
                intent.putExtra("id", currentAdd.getId());
                getContext().startActivity(intent);
            }
        });
        return rootView;
    }

}
