package com.violin.recyclerviewdemo.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.violin.recyclerviewdemo.R;
import com.violin.recyclerviewdemo.RVExtension.HFRecyclerControl;
import com.violin.recyclerviewdemo.RecyclerAdapter;
import com.violin.recyclerviewdemo.kit.refresh.RecyclerRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private HFRecyclerControl hfRecyclerControl;


    public static void launch(Context context){
        Intent intent=new Intent(context,GridActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        initView();
    }
    private void initView() {

        final RecyclerRefreshLayout refreshLayout= (RecyclerRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },2000);

            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerAdapter mAdapter = new RecyclerAdapter();
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("item" + i);
        }
        mAdapter.setDatas(datas);

        GridLayoutManager manager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(mAdapter);

        hfRecyclerControl = new HFRecyclerControl();

        hfRecyclerControl.setAdapter(recyclerView,mAdapter);


        Button addHeader = (Button) findViewById(R.id.bt_header);
        addHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView=new TextView(v.getContext());
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER);
                int count= hfRecyclerControl.getHeaderCount()+1;
                textView.setText("headerview"+count);
                hfRecyclerControl.addHeaderView(textView);
            }
        });

        Button addFooter = (Button) findViewById(R.id.bt_footer);
        addFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView=new TextView(v.getContext());
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER);
                int count= hfRecyclerControl.getFooterCount()+1;
                textView.setText("footerView"+count);
                hfRecyclerControl.addFooterView(textView);
            }
        });


    }
}
