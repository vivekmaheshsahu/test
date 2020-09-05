package com.android.test.newModule.tab2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.test.R;
import com.android.test.data.model.PlayerList;
import com.android.test.newModule.tab1.PlayerListAdapter;
import com.android.test.newModule.tab1.tab1_presentor;

import com.android.test.database.*;
import java.util.ArrayList;
import java.util.List;

public class Tab2 extends Fragment
{
    PlayerListAdapter mlist;
    private RecyclerView mRecyclerView;
    private tab1_presentor presentor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_s_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 0));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);
        fetchData();
    }

    public void setAdapter(List<PlayerList> lists) {
        mlist = new PlayerListAdapter(getContext(), lists);
        mRecyclerView.setAdapter(mlist);
    }

    public void fetchData()
    {
        DbHelper dbHelper = new DbHelper(getContext());
        List<PlayerList> lists = new ArrayList<>();
        Cursor cursor = dbHelper.getPlayerList(7);
        if (cursor != null && cursor.moveToFirst())
               do {
                String name = cursor.getString(cursor.getColumnIndex("player_name"));
                String cap_flag = cursor.getString(cursor.getColumnIndex("player_Iskeeper"));
                String keep_flag = cursor.getString(cursor.getColumnIndex("player_Iscaption"));
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
      
        setAdapter(lists);
    }
}