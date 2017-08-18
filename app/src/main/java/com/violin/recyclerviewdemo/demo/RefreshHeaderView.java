package com.violin.recyclerviewdemo.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.violin.recyclerviewdemo.R;
import com.violin.recyclerviewdemo.kit.refresh.IRefreshStatus;

/**
 * Created by whl on 2017/8/15.
 */

public class RefreshHeaderView extends LinearLayout implements IRefreshStatus {

    private ImageView imageView;
    private TextView textView;

    private RotateAnimation rotateAnimation;

    private RotateAnimation rotateReverseAnimation;

    private RotateAnimation loadingAnimation;

    public RefreshHeaderView(Context context) {
        this(context, null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        inflate(context, R.layout.refresh_header_layout, this);
        initView();
        initAnimation();
    }

    private void initAnimation() {
        rotateAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(100);


        rotateReverseAnimation=new RotateAnimation(-180,0,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);

        rotateReverseAnimation.setFillAfter(true);
        rotateReverseAnimation.setRepeatCount(0);
        rotateReverseAnimation.setInterpolator(new LinearInterpolator());
        rotateReverseAnimation.setDuration(100);
        loadingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(getContext(), R.anim.loading);


    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.iv_imageview);

        textView = (TextView) findViewById(R.id.textview);

    }

    @Override
    public void reset() {
        textView.setText("下拉刷新");
        imageView.clearAnimation();
        imageView.setImageResource(R.drawable.refresh_arrow);
    }

    @Override
    public void refreshing() {
        textView.setText("正在刷新...");
        imageView.setImageResource(R.drawable.loading);
        imageView.startAnimation(loadingAnimation);

    }

    @Override
    public void refreshComplete() {
        textView.setText("刷新完成");
    }

    @Override
    public void pullToRefresh() {
        textView.setText("释放立即刷新");
        imageView.clearAnimation();
        imageView.setAnimation(rotateAnimation);
        imageView.startAnimation(rotateAnimation);
    }

    @Override
    public void releaseToRefresh() {
        textView.setText("下拉刷新");
        imageView.clearAnimation();
        imageView.setAnimation(rotateReverseAnimation);
        imageView.startAnimation(rotateReverseAnimation);

    }

    @Override
    public void pullProgress(float pullDistance, float pullProgress) {

    }
}
