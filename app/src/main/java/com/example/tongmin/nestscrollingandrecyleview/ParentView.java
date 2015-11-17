package com.example.tongmin.nestscrollingandrecyleview;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 */
public class ParentView extends LinearLayout implements NestedScrollingParent {
    private NestedScrollingParentHelper parentHelper;
    private LayoutInflater inflater;
    private View headerView;

    public ParentView(Context context) {
        super(context);
        init(context);
    }

    public ParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        this.setOrientation(LinearLayout.VERTICAL);
        parentHelper = new NestedScrollingParentHelper(this);
        inflater = LayoutInflater.from(context);
        headerView = inflater.inflate(R.layout.headerview, null, false);
        this.addView(headerView);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        parentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onStopNestedScroll(View child) {
        parentHelper.onStopNestedScroll(child);
    }

    private boolean addHeight;

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        //处理子view传上来的事件
        //头部高度
        int headerHeight = headerView.getHeight();

        if (dy > 0) {
            //向上滑动

//                    Math.abs(  this.getTop() - dy )
            if ( Math.abs(  this.getTop() - dy ) <= headerHeight) {
                //header 在向上滑动的过程
                this.layout(this.getLeft(), this.getTop() - dy, this.getRight(), this.getBottom() - dy);
                if (!addHeight) {
                    addHeight = true;
                    ViewGroup.LayoutParams params = this.getLayoutParams();
                    params.height = headerHeight+this.getHeight();
                    this.setLayoutParams(params);
                    requestLayout();
                }
                consumed[1] += dy;
            }
        }
        if (dy < 0) {
            //向下滑动
            if ((this.getTop() + Math.abs(dy)) <= 0) {
                //header在向下滑动的过程
                //this.gettop是负数dy也是负数所以需要+dy的绝对值
                this.layout(this.getLeft(), this.getTop() + Math.abs(dy), this.getRight(), this.getBottom() + Math.abs(dy));
                consumed[1] += dy;
            }

        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
//        return super.onNestedFling(target, velocityX, velocityY, consumed);
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
//        return super.onNestedPreFling(target, velocityX, velocityY);
    }
}
