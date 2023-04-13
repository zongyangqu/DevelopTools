package com.quzy.coding.util.recycler;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.OverScroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;


public class NestedPublicRecyclerView extends RecyclerView {

    public static final String TAG = "NestedPublicRecycler";

    private String mTag = TAG;
    private int totalScrollY = 0;
    private final OnScrollListener listener =
            new OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalScrollY += dy;
                    if (callback != null) {
                        callback.onRecyclerScroll(recyclerView, totalScrollY);
                    }
                }
            };
    private @Nullable NestedRecyclerScrollListener callback;
    /**
     * ViVo部分机型偶现报错 java.lang.ArrayIndexOutOfBoundsException: length=101; index=-2147483648 at
     * android.widget.OverScroller$SplineOverScroller.update(OverScroller.java:1690) at
     * android.widget.OverScroller.computeScrollOffset(OverScroller.java:776) at
     * androidx.recyclerview.widget.RecyclerView$ViewFlinger.run(RecyclerView.java:5273) at
     * android.view.Choreographer$CallbackRecord.run(Choreographer.java:1137)
     */
//    class MyViewFlinger extends ViewFlinger {
//
//        @Override
//        public void run() {
//            try {
//                super.run();
//            } catch (ArrayIndexOutOfBoundsException e) {
//            }
//        }
//    }

    public NestedPublicRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public NestedPublicRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NestedPublicRecyclerView(
            @NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void scrollToTop() {
        scrollToPosition(0);
        totalScrollY = 0;
    }

    public void setCallback(@Nullable NestedRecyclerScrollListener callback) {
        this.callback = callback;
        addOnScrollListener(listener);
    }

    private void init() {
        mTag = TAG + "@" + Integer.toHexString(hashCode());
        try {
            Field field = RecyclerView.class.getDeclaredField("mViewFlinger");
            field.setAccessible(true);
           // field.set(this, new MyViewFlinger());
        } catch (Exception e) {
        }
    }


//    @Override
//    void scrollStep(int dx, int dy, @Nullable int[] consumed) {
//        super.scrollStep(dx, dy, consumed);
//    }

    public void doScrollConsumed(int dx, int dy, @NonNull int[] consumed) {
//        consumed[0] = 0;
//        consumed[1] = 1;
//        scrollStep(dx, dy, consumed);
//        int consumedX = consumed[0];
//        int consumedY = consumed[1];
//        if (consumedX != 0 || consumedY != 0) {
//            // 分发滚动状态
//            dispatchOnScrolled(consumedX, consumedY);
//        }
    }

    @Nullable
    public OverScroller getFlingOverScroll() {
        return null;//mViewFlinger.mOverScroller;
    }

    /** 是否正在Fling */
    public boolean isFling() {
        OverScroller overScroller = getFlingOverScroll();
        return overScroller != null && !overScroller.isFinished();
    }

    /** 是否使用自带的Fling */
    public boolean enableOverScrollFling() {
        return getFlingOverScroll() != null;
    }

    /**
     * 更新滚动状态
     *
     * @param scrollState 滚动状态
     */
    public void updateScrollState(int scrollState) {
        //setScrollState(scrollState);
    }

    /** 停止滚动，但不更新状态 */
    public void stopScrollWithoutState() {
//        mViewFlinger.stop();
//        LayoutManager layout = getLayoutManager();
//        if (layout != null) {
//            layout.stopSmoothScroller();
//        }
    }

    /**
     * Fling到边缘时回调
     *
     * @param velocityX x加速度
     * @param velocityY y加速度
     */
//    @Override
//    void absorbGlows(int velocityX, int velocityY) {
//        // super.absorbGlows(velocityX, velocityY);
//        onFlingEnd(velocityX, velocityY);
//    }

    /**
     * Fling到边缘时回调
     *
     * @param velocityX x加速度
     * @param velocityY y加速度
     */
    protected void onFlingEnd(int velocityX, int velocityY) {}

    @Override
    public boolean canScrollVertically(int direction) {
        final int offset = computeVerticalScrollOffset();
        final int range = computeVerticalScrollRange() - computeVerticalScrollExtent();
        if (range == 0) {
            return false;
        }
        if (direction < 0) {
            return offset > 0;
        } else {
            // tory fix: 这一个像素最导致慢滑动时判断是否滑动到顶部判断出错
            // return offset < range - 1;
            return offset < range;
        }
    }
}
