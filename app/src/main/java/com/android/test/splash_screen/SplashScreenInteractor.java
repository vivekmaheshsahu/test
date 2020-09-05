package com.android.test.splash_screen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.android.test.database.DatabaseContract;
import com.android.test.utility.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class SplashScreenInteractor implements ISplashScreenInteractor {

    private Context mContext;
    private SplashScreenPresenter mSplashScreenPresenter;

    public SplashScreenInteractor(Context mContext, SplashScreenPresenter mSplashScreenPresenter) {
        this.mContext = mContext;
        this.mSplashScreenPresenter = mSplashScreenPresenter;
    }

    @Override
    public boolean checkPermissions() {
        return false;
    }

    @Override
    public boolean storeData(JSONObject jsonObject) {
        ContentValues values = new ContentValues();
        ContentValues player_values = new ContentValues();
        try {
            JSONObject info = jsonObject.getJSONObject("Teams");
            Iterator<String> T_id = info.keys();
            while( T_id.hasNext() ) {
                String i = T_id.next();
                JSONObject team = info.getJSONObject(""+i);
                values.put(DatabaseContract.COLUMN_ID1,i);
                values.put(DatabaseContract.COLUMN_SHORT_NAME, team.optString("Name_Short"));
                values.put(DatabaseContract.COLUMN_TEAM_NAME, team.optString("Name_Full"));
                Utility.getDatabase().insert(DatabaseContract.TABLE_NAME1, null, values);

                JSONObject player = team.getJSONObject("Players");
                Iterator<String> ids = player.keys();
                while( ids.hasNext() ) {
                    String key = ids.next();
                    JSONObject player_details = player.getJSONObject(key);
                    player_values.put(DatabaseContract.COLUMN_ID3,key);
                    player_values.put(DatabaseContract.COLUMN_TEAM_ID,i);
                    player_values.put(DatabaseContract.COLUMN_PLAYER_POSTION,player_details.optString("Position"));
                    player_values.put(DatabaseContract.COLUMN_PLAYER_NAME,player_details.optString("Name_Full"));
                    player_values.put(DatabaseContract.COLUMN_PLAYER_ISCAPTION,player_details.optString("Iscaptain"));
                    player_values.put(DatabaseContract.COLUMN_ISKEEPER,player_details.optString("Iskeeper"));
                    JSONObject batting_details = player_details.getJSONObject("Batting");
                    player_values.put(DatabaseContract.COLUMN_PLAYER_BATTING_STYLE,batting_details.optString("Style"));
                    player_values.put(DatabaseContract.COLUMN_PLAYER_BATTING_AVERAGE,batting_details.optString("Average"));
                    player_values.put(DatabaseContract.COLUMN_PLAYER_BATTING_STRIKERATE,batting_details.optString("Strikerate"));
                    player_values.put(DatabaseContract.COLUMN_PLAYER_BATTING_RUNS,batting_details.optString("Runs"));
                    JSONObject Bowling_details = player_details.getJSONObject("Bowling");
                    player_values.put(DatabaseContract.COLUMN_PLAYER_BOWLING_STYLE,Bowling_details.optString("Style"));
                    player_values.put(DatabaseContract.COLUMN_PLAYER_BOWLING_AVERAGE,Bowling_details.optString("Average"));
                    player_values.put(DatabaseContract.COLUMN_PLAYER_BOWLING_ECONOMYRATE,Bowling_details.optString("Economyrate"));
                    player_values.put(DatabaseContract.COLUMN_PLAYER_BOWLING_WICKET,Bowling_details.optString("Wickets"));
                    Utility.getDatabase().insert(DatabaseContract.TABLE_NAME3, null, player_values);
                }
            }

            return true;
        }
        catch(JSONException e)
        {
            Log.d("ERROR",e.toString());
            return false;
        }
    }

    @Override
    public boolean userAlreadyLoggedIn() {
        Cursor cursor = Utility.getDatabase().rawQuery("SELECT * FROM "
                + DatabaseContract.TABLE_NAME3, null);
        return cursor.moveToFirst();
    }

}
