package com.android.test.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseManager {

    private static DatabaseManager databaseManagerInstance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private AtomicInteger mOpenCounter = new AtomicInteger();        //We have a counter which indicate how many times database is opened.
    private SQLiteDatabase mDatabase;

    private DatabaseManager() {
    }

    /**
     * This method is used to initialize a database openHelper class.
     *
     * @param openHelper
     */
    public static synchronized void initializeInstance(SQLiteOpenHelper openHelper) {
        if (databaseManagerInstance == null) {
            databaseManagerInstance = new DatabaseManager();
            mDatabaseHelper = openHelper;
        }
    }

    /**
     * This method returns singleton instance of this class.Wherever in code, database is used we
     * get it by using DatabaseManager instance.
     *
     * @return
     */
    public static synchronized DatabaseManager getInstance() {
        if (databaseManagerInstance == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " is not initialized, call initialize(..) method first.");
        }
        return databaseManagerInstance;
    }

    /**
     * Every time you need database you should call openDatabase() method of DatabaseManager class.
     * Inside this method, we have a counter, which indicate how many times database is opened.
     * If it equals to one, it means we need to create new database connection, if not, database
     * connection is already established.
     *
     * @return instance of SqLiteDatabase.
     */
    public synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    /**
     * Every time we call this method, counter is decreased, whenever it goes to zero, we are
     * closing database connection.
     */
    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            mDatabase.close();
        }
    }
}
