package com.quzy.coding.util.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Created by 博 on 2017/6/25.
 */
public class MyDivider extends RecyclerView.ItemDecoration {

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(view.getLayoutParams() instanceof  StaggeredGridLayoutManager.LayoutParams){
            StaggeredGridLayoutManager.LayoutParams params =
                    (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            /**
             * 根据params.getSpanIndex()来判断左右边确定分割线
             * 第一列设置左边距为space，右边距为space/2  （第二列反之）
             */
            outRect.top = 30;
            if (params.getSpanIndex() % 2 == 0) {
                outRect.left = 30;
                outRect.right = 30 / 2;
            } else {
                outRect.left = 30 / 2;
                outRect.right = 30;
            }
        }
    }
}
