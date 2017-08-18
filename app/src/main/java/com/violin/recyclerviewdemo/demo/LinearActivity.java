package com.violin.recyclerviewdemo.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.violin.recyclerviewdemo.kit.nextpage.NextPageControl;
import com.violin.recyclerviewdemo.kit.refresh.RecyclerRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LinearActivity extends AppCompatActivity {

    private HFRecyclerControl hfRecyclerControl;
    private Timer timer;
    private NextPageControl nextPageControl;
    private RecyclerAdapter mAdapter;

    public int pageSize = 2;
    private RecyclerRefreshLayout refreshLayout;


    public static void launch(Context context) {
        Intent intent = new Intent(context, LinearActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        timer = new Timer();
        initView();

        loadNextPage(1);
    }

    private void initView() {

        refreshLayout = (RecyclerRefreshLayout) findViewById(R.id.refresh_layout);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        refreshLayout.setRefreshView(new RefreshHeaderView(this),params);
        refreshLayout.setRefreshStyle(RecyclerRefreshLayout.RefreshStyle.NORMAL);

        refreshLayout.setDragDistanceConverter(new RefreshDiatanceConverter());

        refreshLayout.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNextPage(0);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new RecyclerAdapter();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(mAdapter);
//recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayout.VERTICAL));
        recyclerView.addItemDecoration(new RecyclerItemDecoration(this, 5));

        hfRecyclerControl = new HFRecyclerControl();

        hfRecyclerControl.setAdapter(recyclerView, mAdapter);

        nextPageControl = new NextPageControl();
        nextPageControl.setUpWinthRecyclerView(recyclerView);

        nextPageControl.linkHFRecycler(hfRecyclerControl);

        nextPageControl.setListener(new NextPageControl.Listener() {
            @Override
            public void requestNextPageData(int pageNo) {
                loadNextPage(pageNo);

            }
        });


        Button addHeader = (Button) findViewById(R.id.bt_header);
        addHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(v.getContext());
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER);
                int count = hfRecyclerControl.getHeaderCount() + 1;
                textView.setText("headerview" + count);
                hfRecyclerControl.addHeaderView(textView);
            }
        });

        Button addFooter = (Button) findViewById(R.id.bt_footer);
        addFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = new TextView(v.getContext());
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setGravity(Gravity.CENTER);
                int count = hfRecyclerControl.getFooterCount() + 1;
                textView.setText("footerView" + count);
                hfRecyclerControl.addFooterView(textView);
            }
        });


        findViewById(R.id.bt_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.insertItem(2, "insert");
            }
        });


    }

    private void loadNextPage(final int page) {

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 10; i++) {
                            list.add("item" + (mAdapter.getDatas().size() + i) + "position");
                        }
                        mAdapter.addDatas(list);
                        --pageSize;
//                        if (page==0){
                        refreshLayout.setRefreshing(false);
//                        }
                        nextPageControl.parsePageData(pageSize, page);

                    }
                });


            }
        };

        timer.schedule(task, 2000);


    }
}
