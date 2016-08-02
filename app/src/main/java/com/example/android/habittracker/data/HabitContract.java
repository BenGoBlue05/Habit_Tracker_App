package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by bplewis5 on 7/4/16.
 */
public class HabitContract {

//  Inner class for location table
    public static final class LocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "location";
        public static final String COLUMN_VENUE = "venue";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_COUNTY = "county";
    }

//     Inner class for habit table
    public static final class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habit";
        public static final String COLUMN_LOC_KEY = "location_id";
        public static final String COLUMN_HABIT_NAME = "habit_name";
        public static final String COLUMN_TIME_SPENT = "time_spent";
    }
}

