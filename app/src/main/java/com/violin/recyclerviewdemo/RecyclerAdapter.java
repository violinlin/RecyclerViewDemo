package com.violin.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whl on 2017/8/9.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RViewHolder> {


    private List<String> datas;

    public RecyclerAdapter() {
        datas = new ArrayList<>();
    }

    public void setDatas(List<String> list) {
        datas.clear();
        datas.addAll(list);
        notifyDataSetChanged();
    }

    public void addDatas(List<String> list) {
        datas.addAll(list);
        notifyDataSetChanged();
    }

    public List<String> getDatas() {
        return datas;
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemView itemView = new ItemView(parent.getContext());
        return new RViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RViewHolder holder, int position) {
        ItemView itemView = (ItemView) holder.itemView;
        itemView.setData(datas.get(position));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class RViewHolder extends RecyclerView.ViewHolder {

        public RViewHolder(View itemView) {
            super(itemView);
        }
    }
}
