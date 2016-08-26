package com.lhk.travel.travel.map;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2016/5/16.
 */
public class MapViewManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        super.onLayoutChildren(recycler, state);
        detachAndScrapAttachedViews(recycler);// 分离所有的itemView
    }
}
