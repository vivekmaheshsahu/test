package com.android.test.newModule.tab1;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.test.*;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.android.test.data.model.*;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {
    private Context mContext;
    private List<PlayerList> player;

    public PlayerListAdapter(Context mContext, List<PlayerList> List) {
        this.mContext = mContext;
        this.player = List;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_player_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.bindData(player.get(i));
    }

    @Override
    public int getItemCount() {
        return player.size();
    }

    public void swapDataList(List<PlayerList> womenList) {
        this.player = womenList;
    }

    public interface OnItemClickListener {
        void onItemClick(String uniqueId, int form_id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, incompleteVisitlabel;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textview_name);
        }

        private void bindData(final PlayerList listModel) {
            if (listModel != null) {
                textViewName.setText(listModel.getPlayerName());
            }
        }
    }
}
