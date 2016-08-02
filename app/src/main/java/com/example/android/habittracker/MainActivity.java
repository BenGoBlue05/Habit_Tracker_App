package com.example.android.habittracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HabitDbHelper dbHelper = new HabitDbHelper(this);
        dbHelper.insertHabitEntry("Basketball", 4, 60.2);
        dbHelper.insertLocationEntry("Gym", "birmingham", "oakland");

    }

}