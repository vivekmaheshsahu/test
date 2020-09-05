package com.android.test.newModule.tab1;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import com.android.test.data.model.PlayerList;

public class tab1_presentor implements Itab1_presenter<tab1> {

    tab1 t1;
    tab1_interactor t_interactor;

    @Override
    public void getListPlayer() {
        List<PlayerList> lists = new ArrayList<>();
        Cursor cursor = t_interactor.fetchListPlayer();
        if (cursor != null && cursor.moveToFirst())
            do {
                String cap_flag = cursor.getString(cursor.getColumnIndex("player_Iskeeper"));
                String keep_flag = cursor.getString(cursor.getColumnIndex("player_Iscaption"));
                String name = cursor.getString(cursor.getColumnIndex("player_name"));
                if((cap_flag.equalsIgnoreCase("true"))&&(keep_flag.equalsIgnoreCase("true")))
                {
                    name = name + "(C)"+" (wk)";
                }
                else if(cap_flag.equalsIgnoreCase("true"))
                {
                    name = name + "(C) ";
                }
                else if(keep_flag.equalsIgnoreCase("true"))
                {
                    name = name +" (wk)";
                }
                lists.add(new PlayerList(name));
            }
        while (cursor.moveToNext());
        lists.add(0,(new PlayerList("Players")));
        t1.setAdapter(lists);

    }

    @Override
    public void attachView(tab1 view) {
       this.t1 = view;
        t_interactor = new tab1_interactor(view.getContext());

    }

    @Override
    public void detachView() {
        t1 = null;
    }
}
