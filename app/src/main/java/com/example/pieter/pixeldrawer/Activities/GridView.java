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
import com.example.pieter.pixeldrawer.Models.Pixel;

public class GridView extends GridLayout {

    private int size = 20;
    private PixelClickListener pixelClickListener;

    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGridView(context);
    }

    public GridView(Context context, AttributeSet attrs, int size) {
        super(context, attrs);
        this.size = size;
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
        Grid grid;

        //check to determine whether height or width should be used as measurement
        boolean isPortrait = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT;

        //delete margin from calculation pixelsize
        int marginWidth = (int) pxFromDp(context, 40);
        width = calculateScreenSize(isPortrait) - marginWidth;
        int pixelwidth = width / size;
        this.setRowCount(size);
        this.setColumnCount(size);

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                PixelView p = new PixelView(context);
                if (i % 2 == 0) {
                    p.setBackgroundColor(Color.RED);
                } else {
                    p.setBackgroundColor(Color.BLUE);
                }
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

    public interface PixelClickListener {

        void onItemClick(PixelView view);

    }
}

