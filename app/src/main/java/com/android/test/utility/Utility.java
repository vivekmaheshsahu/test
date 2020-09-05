package com.android.test.utility;

import android.database.sqlite.SQLiteDatabase;

import com.android.test.database.DatabaseManager;

public class Utility {
    public static String TAG = Utility.class.getName();

    public static SQLiteDatabase getDatabase() {
        return DatabaseManager.getInstance().openDatabase();
    }

}
