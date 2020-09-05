package com.android.test.newModule.tab1;

import android.content.Context;
import com.android.test.data.model.PlayerList;
import java.util.List;

public interface Itab1_view {

    void setAdapter(List<PlayerList> playerLists);

    Context getContext();


}
