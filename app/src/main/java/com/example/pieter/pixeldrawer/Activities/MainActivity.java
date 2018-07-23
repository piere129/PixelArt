package com.example.pieter.pixeldrawer.Activities;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.pieter.pixeldrawer.R;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener {

    private Button color_picker_button;
    private LinearLayout layout;
    private GridView gridView;

    int selectedColor = (int) Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if(savedInstanceState != null)
        {
            selectedColor = savedInstanceState.getInt("color");
        }
        initGrid();
        initOnClick();
        onColorSelected(0,selectedColor);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("color", selectedColor);
        super.onSaveInstanceState(outState);
    }

    public void initGrid() {
        color_picker_button = (Button) findViewById(R.id.btnChoose);
        layout = (LinearLayout) findViewById(R.id.layout);
        this.gridView = findViewById(R.id.gridView);
    }

    public void initOnClick() {
        color_picker_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPicker();
            }
        });
    }

    public void showColorPicker() {
        ColorPickerDialog.newBuilder().setColor(Color.BLACK).show(this);
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        selectedColor = color;
        findViewById(R.id.layout).setBackgroundColor(selectedColor);
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }


}
