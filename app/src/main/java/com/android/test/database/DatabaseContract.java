package com.android.test.database;

import android.os.Environment;

public class DatabaseContract {

    public static final String DATABASE_NAME = "test.sr";
    public static final int DATABASE_VERSION = 10;
    public static final String DB_LOCATION = Environment.getExternalStorageDirectory() + "/TEST";
    public static final String TEXT_TYPE = " TEXT";
    public static final String BLOB_TYPE = " BLOB";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    public static final String TABLE_NAME1="team";
    public static final String COLUMN_ID1="team_id";
    public static final String COLUMN_TEAM_NAME="team_name ";
    public static final String COLUMN_SHORT_NAME="short_name";

    public static final String TABLE_NAME3="player_details";
    public static final String COLUMN_SR_NO="sr_no";
    public static final String COLUMN_ID3="player_id";
    public static final String COLUMN_TEAM_ID="team_id";
    public static final String COLUMN_PLAYER_POSTION="player_postion";
    public static final String COLUMN_PLAYER_NAME="player_name";
    public static final String COLUMN_PLAYER_ISCAPTION="player_Iscaption";
    public static final String COLUMN_ISKEEPER="player_Iskeeper";
    public static final String COLUMN_PLAYER_BATTING_STYLE="player_batting_style";
    public static final String COLUMN_PLAYER_BATTING_AVERAGE="player_batting_average";
    public static final String COLUMN_PLAYER_BATTING_STRIKERATE="player_batting_strikerate";
    public static final String COLUMN_PLAYER_BATTING_RUNS="player_batting_runs";
    public static final String COLUMN_PLAYER_BOWLING_STYLE="player_bowling_style";
    public static final String COLUMN_PLAYER_BOWLING_AVERAGE="player_bowling_average";
    public static final String COLUMN_PLAYER_BOWLING_ECONOMYRATE="player_bowling_economyrate";
    public static final String COLUMN_PLAYER_BOWLING_WICKET="player_bowling_wicket";

    public static final class ImageDetails{

        public static final String CREATE_TABLE ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 +
                "("+
                COLUMN_ID1 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_SHORT_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_TEAM_NAME + TEXT_TYPE + ")";

        public static final String CREATE_TABLE3 ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME3 +
                "("+
                COLUMN_SR_NO + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                COLUMN_ID3 + INTEGER_TYPE + COMMA_SEP +
                COLUMN_TEAM_ID + INTEGER_TYPE + COMMA_SEP +
                COLUMN_PLAYER_POSTION + INTEGER_TYPE + COMMA_SEP +
                COLUMN_PLAYER_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_PLAYER_ISCAPTION + TEXT_TYPE + COMMA_SEP +
                COLUMN_ISKEEPER + TEXT_TYPE + COMMA_SEP +
                COLUMN_PLAYER_BATTING_STYLE + TEXT_TYPE + COMMA_SEP +
                COLUMN_PLAYER_BATTING_AVERAGE + INTEGER_TYPE + COMMA_SEP +
                COLUMN_PLAYER_BATTING_STRIKERATE + INTEGER_TYPE + COMMA_SEP +
                COLUMN_PLAYER_BATTING_RUNS + INTEGER_TYPE + COMMA_SEP +
                COLUMN_PLAYER_BOWLING_STYLE + TEXT_TYPE + COMMA_SEP +
                COLUMN_PLAYER_BOWLING_AVERAGE + INTEGER_TYPE + COMMA_SEP +
                COLUMN_PLAYER_BOWLING_ECONOMYRATE + INTEGER_TYPE + COMMA_SEP +
                COLUMN_PLAYER_BOWLING_WICKET + INTEGER_TYPE +
                ")";
    }
}

