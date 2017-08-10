package com.violin.recyclerviewdemo.RVExtension;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by whl on 2017/8/9.
 */

public class HFRecyclerControl {
    private RecyclerView recyclerView;
    private  HFRecyclerAdapter hfAdapter;

    public HFRecyclerControl(){
        hfAdapter=new HFRecyclerAdapter();
    }

    public void setAdapter(RecyclerView recyclerView,RecyclerView.Adapter adapter){
        this.recyclerView=recyclerView;
        hfAdapter.setAdapter(adapter);
        recyclerView.setAdapter(hfAdapter);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup lookup = gridLayoutManager.getSpanSizeLookup();
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (hfAdapter.isHeader(position) || hfAdapter.isFooter(position)) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return lookup.getSpanSize(position - hfAdapter.getHeaderViewsCount());
                }
            });
        }
    }

    public void addHeaderView(View header){
        hfAdapter.addHeaderView(header);
    }

    public void addFooterView(View footer){
        hfAdapter.addFooterView(footer);
    }

    public int getHeaderCount(){
        return hfAdapter.getHeaderViewsCount();
    }

    public int getFooterCount(){
        return hfAdapter.getFooterViewsCount();
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
