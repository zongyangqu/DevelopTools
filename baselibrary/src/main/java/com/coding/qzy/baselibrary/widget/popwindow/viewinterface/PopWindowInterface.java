package com.coding.qzy.baselibrary.widget.popwindow.viewinterface;

import android.view.View;

import com.coding.qzy.baselibrary.widget.popwindow.PopItemAction;


public interface PopWindowInterface {

    void setView(View view);

    void addContentView(View view);

    void addItemAction(PopItemAction popItemAction);

    void setIsShowLine(boolean isShowLine);

    void setIsShowCircleBackground(boolean isShow);

    void setPopWindowMargins(int leftMargin, int topMargin, int rightMargin, int bottomMargin);

    interface OnStartShowListener {
        void onStartShow(PopWindowInterface popWindowInterface);
    }

    interface OnStartDismissListener {
        void onStartDismiss(PopWindowInterface popWindowInterface);
    }
}
