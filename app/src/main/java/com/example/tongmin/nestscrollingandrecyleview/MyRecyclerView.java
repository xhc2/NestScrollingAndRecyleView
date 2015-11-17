package com.example.tongmin.nestscrollingandrecyleview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by TongMin on 2015/11/17.
 */
public class MyRecyclerView extends RecyclerView {


    private LinearLayoutManager layoutManager;

    @Override
    public void setLayoutManager(LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        this.layoutManager = (LinearLayoutManager)layoutManager;
    }

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    private float dX,dY ;
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            dX = e.getX();
            dY = e.getY();
        }
        if(e.getAction() == MotionEvent.ACTION_MOVE){
            Log.e("xhc","position "+layoutManager.findFirstCompletelyVisibleItemPosition());
            if(layoutManager.findFirstCompletelyVisibleItemPosition() == 0){
                //滑动到了顶部
                Log.e("xhc","滑动到了顶部");
                return super.onTouchEvent(e);
            }
            else{
               e.offsetLocation(e.getX() - dX,e.getY() - dY);
            }

        }

        return super.onTouchEvent(e);
    }
}
