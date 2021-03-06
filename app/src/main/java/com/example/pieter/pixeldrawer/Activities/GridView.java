package com.example.pieter.pixeldrawer.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridLayout;

import com.example.pieter.pixeldrawer.Models.Grid;

public class GridView extends GridLayout {

    private Grid grid;

    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGridView(context);
    }

    /**
     * Make the gridview
     * <p>
     * Subtracts the marginWidth (converted to pixels) with the screen width,
     * to allow the pixels to be divided into equal widths. It also checks on screen
     * orientation to determine whether or not the statusbar should be accounted for
     * during the calculation.
     */
    private void initGridView(final Context context) {

        int width;
        grid = new Grid(20,Color.LTGRAY);

        //check to determine whether height or width should be used as measurement
        boolean isPortrait = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT;

        //delete margin from calculation pixelsize
        int marginWidth = (int) pxFromDp(context, 40);
        width = calculateScreenSize(isPortrait) - marginWidth;
        int pixelwidth = width / grid.getSize();
        this.setRowCount(grid.getSize());
        this.setColumnCount(grid.getSize());

        //create pixelviews and set their backgroundcolor
        for (int i = 0; i < grid.getSize(); i++)
            for (int j = 0; j < grid.getSize(); j++) {
                PixelView p = new PixelView(context);
                final int x = i;
                final int y = j;
                p.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View view){
                        view.setBackgroundColor(grid.getColor());
                        grid.setColorAtIndex(x,y);
                    }
                });
                p.setBackgroundColor(grid.getColor());
                addView(p, pixelwidth, pixelwidth);
            }

    }


    /**
     * Calculates the screensize, and runs differently based upon
     * the orientation of the screen. If it is portrait, it will use
     * the width of the device to determine pixelsize. Otherwise, it
     * will use the height of the screen, and subtract the height of the
     * statusbar from it.
     */
    public int calculateScreenSize(boolean isPortrait) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        if (isPortrait) {
            return displayMetrics.widthPixels;
        }
        return displayMetrics.heightPixels - getStatusBarHeight();
    }

    /**
     * Calculates the status bar height
     *
     * @return
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * Necessary to convert the left & right margins to pixels to avoid overflow of gridview,
     * while still allowing the xml file to use dp for the margins.
     */
    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public int getColor()
    {
        return grid.getColor();
    }

    public void setColor(int color)
    {
        grid.setColor(color);
    }

    public int getSize()
    {
       return grid.getSize();
    }

    public void setSize(int size)
    {
        grid.setSize(size);
        initGridView(getContext());
    }

    /**
     * returns a 2-dimensional array of all color values of each pixelview
     * in the grid. Used to persist the colors during screen rotation
     * @return
     */
    public int[][] getColors(){

        int[][] array = new int[getSize()][getSize()];
        for(int i = 0;i < getSize();i++)
        {
            for(int j = 0;j < getSize(); j++)
            {
                array[i][j] = this.grid.getChildAt(i,j).getColor();
            }
        }
        int[][] array2 = array;
        return array;
    }

    /**
     * sets a 2-dimensional array of all color values to each pixelview
     * in the grid. Used to persist the colors during screen rotation
     * @return
     */
    public void setColors(int[][] colors)
    {
        int counter=0;
        for(int i = 0;i < getSize();i++)
        {
            for(int j = 0;j < getSize(); j++)
            {
               this.getChildAt(counter).setBackgroundColor(colors[i][j]);
               grid.getChildAt(i,j).setColor(colors[i][j]);
               counter++;
            }
        }
    }

}

