package com.violin.recyclerviewdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    public void removeItem(int position) {
        position=datas.size()>position?position:datas.size()-1;
        datas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, datas.size()-position);
    }

    public void insertItem(int position, String data) {
        position=datas.size()>position?position:datas.size()-1;
        datas.add(position, data);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, datas.size()-position);

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
        itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class RViewHolder extends RecyclerView.ViewHolder {

        public RViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
                    Toast.makeText(v.getContext(), "click" + datas.get(position), Toast.LENGTH_SHORT).show();

                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int position = (int) v.getTag();
                    new AlertDialog.Builder(v.getContext()).setTitle("温馨提示")
                            .setMessage("删除" + datas.get(position) + "?")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    removeItem(position);

                                }
                            }).setNegativeButton("取消", null).show();

                    return false;
                }
            });
        }
    }
}
