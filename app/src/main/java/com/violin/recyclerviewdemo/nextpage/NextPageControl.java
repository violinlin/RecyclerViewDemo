package com.violin.recyclerviewdemo.nextpage;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.violin.recyclerviewdemo.RVExtension.HFRecyclerControl;

/**
 * Created by whl on 2017/8/10.
 */

public class NextPageControl {
    private boolean hasMore;
    private int pageNo;
    private boolean isRequestNexting;
    private HFRecyclerControl hfRecyclerControl;
    private RecyclerView recyclerView;
    private NextPageView nextPageView;


    public void linkHFRecycler(HFRecyclerControl hfRecyclerControl) {
        this.hfRecyclerControl = hfRecyclerControl;
        nextPageView = new NextPageView(hfRecyclerControl.getRecyclerView().getContext());
        hfRecyclerControl.addFooterView(nextPageView);
    }

    public void setUpWinthRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //            用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (!hasMore || isRequestNexting) {
                    return;
                }
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    parseNextPage(isSlidingToLast);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    isSlidingToLast = true;
                } else {
                    isSlidingToLast = false;
                }

            }

        });


    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            parseNextPage(true);
        }
    };

    public void parsePageData(int pageSize, int pageNo) {
        this.pageNo = pageNo;
        hasMore = pageSize > 0;
        isRequestNexting=false;
//        没有更多数据
        if (pageSize <= 0) {
            if (pageNo != 1) {
//                如果有数据则显示加载完毕
                nextPageView.onLastPage();


            } else {
//                如果没有任何数据隐藏加载view
                nextPageView.hide();
            }

        } else {
//            分页处理
            nextPageView.onLoading();
            nextPageView.postDelayed(runnable, 1000);
        }


    }

    private void parseNextPage(boolean isSlidingToLast) {
        if (isRequestNexting){
            return;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        int lastVisibleItem = 0, totalItemCount = 0;
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
            //获取最后一个完全显示的ItemPosition
//                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
            lastVisibleItem = manager.findLastVisibleItemPosition();
            totalItemCount = manager.getItemCount();

        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;

            int[] posi = manager.findLastVisibleItemPositions(null);
            for (int i = 0; i < posi.length; i++) {
                lastVisibleItem = posi[i] > lastVisibleItem ? posi[i] : lastVisibleItem;
            }
            totalItemCount = manager.getItemCount();
        }

        // 判断是否滚动到底部，并且是向下滚动
        if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
            //加载更多功能的代码
            if (listener != null) {
                isRequestNexting = true;
                listener.requestNextPageData(++pageNo);
            }
        }
    }

    public interface Listener {
        public void requestNextPageData(int pageNo);
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }


}
