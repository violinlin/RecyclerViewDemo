package com.violin.recyclerviewdemo.demo;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by whl on 2017/8/14.
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration{
    private int space;

    public RecyclerItemDecoration(Context context, int space) {
        this.space = dip2px(context, space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = space;

    }

    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



}
