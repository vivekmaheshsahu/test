package com.android.test.newModule.tab1;

import android.content.Context;
import android.database.Cursor;
import com.android.test.database.*;

public class tab1_interactor implements Itab1_interactor {

    private Context mContext;
    private DbHelper dbHelper;

    tab1_interactor(Context context) {
        this.mContext = context;
        dbHelper = new DbHelper(context);
    }

    @Override
    public Cursor fetchListPlayer() {
        return dbHelper.getPlayerList(6);
    }
}
