package com.example.pieter.pixeldrawer.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.pieter.pixeldrawer.Models.Pixel;
import com.example.pieter.pixeldrawer.R;

public class PixelView extends FrameLayout {

    Pixel pixel;

    public PixelView( Context context) {
        super(context);
        initPixelView();
    }

    public void initPixelView(){

            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if(inflater != null){
                inflater.inflate(R.layout.pixel, this,true);
            }
    }
}
