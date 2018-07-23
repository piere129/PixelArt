package com.example.pieter.pixeldrawer.Models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

public class Pixel{

    private int color;

    public Pixel(int color)
    {
        this.color = color;
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }
}
