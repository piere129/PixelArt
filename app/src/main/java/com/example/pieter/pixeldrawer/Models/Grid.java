package com.example.pieter.pixeldrawer.Models;

import android.graphics.Color;
import android.util.Log;

public class Grid {

    private int color = Color.GRAY;
    private int size = 20;
    private Pixel[][] grid;

    public Grid(int size, int color)
    {
        this.color = color;
        this.size = size;
        initGrid();
    }

    private void initGrid(){
        this.grid = new Pixel[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                grid[i][j] = new Pixel(color);
            }
        }

    }

    public Pixel getChildAt(int x, int y)
    {
        return grid[x][y];
    }

    public void setColorAtIndex(int x, int y)
    {
        Pixel p = getChildAt(x,y);
        p.setColor(color);
        grid[x][y] = p;
    }

    public int getColor()
    {
        return this.color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public int getSize()
    {
        return this.size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

}
