package com.example.mifans.recycleviewtest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;


public class NestedScrollParentView extends LinearLayout {
    private static final String TAG = "NestedScrollParentView";
    private View mHeader;
    private int mHeaderHeight;
    private View textview;
    private View recycleview;

    public NestedScrollParentView(Context context) {
        super(context);
    }

    public NestedScrollParentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollParentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
        //上滑收起头部view
        boolean headerScrollUp = dy > 0 && getScrollY() < mHeaderHeight;
        //下滑展开头部view
        boolean headerScrollDown = dy < 0 && getScrollY() > 0 && !target.canScrollVertically(-1);
        if (headerScrollUp || headerScrollDown) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            mHeader = getChildAt(0);
            textview = getChildAt(1);
            recycleview = getChildAt(2);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeaderHeight = mHeader.getMeasuredHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //先走一遍measure流程，把childview的height测量出来
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        //上拉将头部view隐藏后，在最底部的recycleview下方会出现空白
        //加大recycleview的height，填补空白
        LayoutParams layoutParams = (LayoutParams) recycleview.getLayoutParams();
        layoutParams.height = getMeasuredHeight()-textview.getMeasuredHeight();
        //重新测量childview
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);


    }

    //重写该方法，在onNestedPreScroll方法中使用了scrollby方法移动头部view
    // 而scrollby是由scrollto实现的，防止头部view上下拉距离超过自身长度
    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        } else if (y > mHeaderHeight) {
            y = mHeaderHeight;
        }

        super.scrollTo(x, y);
    }

}
