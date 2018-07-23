package com.example.pieter.pixeldrawer.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.pieter.pixeldrawer.R;
import com.google.gson.Gson;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener {

    private Button color_picker_button;
    private LinearLayout layout;
    private GridView gridView;
    private Gson gson = new Gson();

    int selectedColor = (int) Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initGrid();
        initOnClick();

        if(savedInstanceState != null)
        {
            selectedColor = savedInstanceState.getInt("color");
            int[][] retrievedColors = gson.fromJson(savedInstanceState.getString("jsonColors"),int[][].class);
            gridView.setColors(retrievedColors);
        }

        onColorSelected(0,selectedColor);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
    }

    /**
     * inflates the options menu of the actionbar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * persists all data of variables that need to be pertained during
     * screen rotation or navigation to a new activity
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt("color", selectedColor);

        int[][] colors = gridView.getColors();
        String colorsStringified = gson.toJson(colors);
        outState.putString("jsonColors",colorsStringified);

        super.onSaveInstanceState(outState);
    }

    /**
     * assigns the necessary buttons and views to their respective layouts
     */
    public void initGrid() {
        color_picker_button = (Button) findViewById(R.id.btnChoose);
        layout = (LinearLayout) findViewById(R.id.layout);
        this.gridView = findViewById(R.id.gridView);

    }

    /**
     * initialises the OnClickListener for showing the color picking menu
     */
    public void initOnClick() {
        color_picker_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPicker();
            }
        });
    }

    /**
     * shows the color picking menu
     */
    public void showColorPicker() {
        ColorPickerDialog.newBuilder().setColor(selectedColor).show(this);
    }

    /**
     * saves the selected color in a variable
     * @param dialogId
     * @param color
     */
    @Override
    public void onColorSelected(int dialogId, int color) {
        selectedColor = color;
        gridView.setColor(color);
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }


}
