package com.example.android.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitContract.LocationEntry;

/**
 * Created by bplewis5 on 7/4/16.
 */
public class HabitDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "habit.db";
    private static final int DATABASE_VERSION = 2;
    Context mContext;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE " + LocationEntry.TABLE_NAME + " (" +
                LocationEntry._ID + " INTEGER PRIMARY KEY," +
                LocationEntry.COLUMN_VENUE + " TEXT NOT NULL, " +
                LocationEntry.COLUMN_CITY + " TEXT NOT NULL, " +
                LocationEntry.COLUMN_COUNTY + " TEXT NOT NULL" +
                " );";

        final String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" +
                HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, " +
                HabitEntry.COLUMN_LOC_KEY + " INTEGER NOT NULL, " +
                HabitEntry.COLUMN_TIME_SPENT + " REAL NOT NULL, " +
                " FOREIGN KEY (" + HabitEntry.COLUMN_LOC_KEY + ") REFERENCES " +
                LocationEntry.TABLE_NAME + " (" + LocationEntry._ID + "));";

        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        mContext.deleteDatabase(DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

    // for location entries
    public long insertLocationEntry(String venue, String city, String county) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LocationEntry.COLUMN_VENUE, venue);
        values.put(LocationEntry.COLUMN_CITY, city);
        values.put(LocationEntry.COLUMN_COUNTY, county);
        return db.insert(LocationEntry.TABLE_NAME, null, values);
    }

    // for habit entries
    public long insertHabitEntry(String habitName, int locationKey, double timeSpent) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, habitName);
        values.put(HabitEntry.COLUMN_LOC_KEY, locationKey);
        values.put(HabitEntry.COLUMN_TIME_SPENT, timeSpent);
        return db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    public Cursor readData(String tableName, String[] columnNames) {
        SQLiteDatabase db = getWritableDatabase();
        return db.query(tableName, columnNames, null, null, null, null, null);
    }

    public void deleteTable(String tableName) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public void updateEntry(String table, String columnName, String value, String idName, int idNumber) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + table +
                "SET " + columnName + " = " + value +
                "WHERE " + idName + " = " + idNumber);
    }

}
