package com.violin.recyclerviewdemo.nextpage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.violin.recyclerviewdemo.R;


/**
 */
public class NextPageView extends FrameLayout {

    private ProgressBar progressBar;
    private View lastLayout;
    private TextView promptText;

    public NextPageView(Context context) {
        this(context, null);
    }

    public NextPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(context, 50)));
        View.inflate(context, R.layout.kit_nextpage_view, this);

        lastLayout = findViewById(R.id.lastLayout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        progressBar.setIndeterminateDrawable(new LoadingDrawable(context));
        promptText = (TextView) findViewById(R.id.promptText);
    }

    public void onLoading() {
        lastLayout.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
    }

    public void onLastPage() {
        promptText.setText("已显示全部内容~");
        progressBar.setVisibility(GONE);
        lastLayout.setVisibility(VISIBLE);
    }

    public void onError() {
        promptText.setText("网络异常，请重试");
        progressBar.setVisibility(GONE);
        lastLayout.setVisibility(VISIBLE);
    }

    public void hide() {
        lastLayout.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
