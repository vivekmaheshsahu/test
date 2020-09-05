package com.android.test.newModule.tab1;

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

import java.util.List;

public class tab1 extends Fragment implements Itab1_view
{
    PlayerListAdapter mlist;
    private RecyclerView mRecyclerView;
    private tab1_presentor  presentor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        presentor = new tab1_presentor();
        presentor.attachView(this);
        return inflater.inflate(R.layout.fragment_p_a_k, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 0));
        mRecyclerView.setLayoutManager(layoutManager);
        presentor.getListPlayer();
    }

    @Override
    public void setAdapter(List<PlayerList> lists) {
        mlist = new PlayerListAdapter(getContext(), lists);
        mRecyclerView.setAdapter(mlist);
    }
}