package com.coding.qzy.baselibrary.widget;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.coding.qzy.baselibrary.R;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/12/08
 * desc   :
 * version: 1.0
 */


public class IosToggleButton extends View {

    private static final String TAG = IosToggleButton.class.getSimpleName();

    //背景Paint
    private Paint mBackGroundPaint;

    //内部开关圆Paint
    private Paint mInnerCirclePaint;

    //内部开关圆环Paint
    private Paint mInnerRingPaint;

    //开关的宽度
    private int mWidth;
    //开关的高度
    private int mHeight;

    //两边半圆的半径
    private int mRadius;

    //开关的长度
    private int mToggleWidth;

    /**
     * 开关是否打开
     */
    private boolean mIsOpen = true;

    /**
     * 当前圆环与内部圆的x轴坐标
     */
    private float mCurrentXPosition = 0;

    private int mCurrentBackGroundColor;

    private int mStartBgColorR;
    private int mStartBgColorG;
    private int mStartBgColorB;

    private int mEndBgColorR;
    private int mEndBgColorG;
    private int mEndBgColorB;

    private int mBgCloseColor;
    private int mBgOpenColor;
    private int mCircleDotColor;
    private int mCircleRingWidth;
    private int mAnimationTime;


    public IosToggleButton(Context context) {
        this(context, null);
    }

    public IosToggleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IosToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(attrs);

        initViews();
        initColors();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.IosToggleButton);
        mBgCloseColor = array.getColor(R.styleable.IosToggleButton_close_bg_color, Color.GRAY);
        mBgOpenColor = array.getColor(R.styleable.IosToggleButton_open_bg_color, Color.GREEN);
        mCircleDotColor = array.getColor(R.styleable.IosToggleButton_circle_dot_color, Color.WHITE);

        mCircleRingWidth = (int) array.getDimension(R.styleable.IosToggleButton_circle_ring_width, dp2px(4));
        mAnimationTime = array.getInt(R.styleable.IosToggleButton_animation_time, 250);
    }

    private void initColors() {
        mStartBgColorR = Color.red(mBgCloseColor);
        mStartBgColorG = Color.green(mBgCloseColor);
        mStartBgColorB = Color.blue(mBgCloseColor);

        mEndBgColorR = Color.red(mBgOpenColor);
        mEndBgColorG = Color.green(mBgOpenColor);
        mEndBgColorB = Color.blue(mBgOpenColor);
    }

    private void initViews() {
        mBackGroundPaint = new Paint();
        mBackGroundPaint.setAntiAlias(true);
        mBackGroundPaint.setStyle(Paint.Style.FILL);

        mInnerCirclePaint = new Paint();
        mInnerCirclePaint.setStrokeWidth(mCircleRingWidth);
        mInnerCirclePaint.setAntiAlias(true);
        mInnerCirclePaint.setColor(mCircleDotColor);
        mInnerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mInnerRingPaint = new Paint();
        mInnerRingPaint.setStrokeWidth(mCircleRingWidth);
        mInnerCirclePaint.setAntiAlias(true);
        mInnerRingPaint.setStyle(Paint.Style.STROKE);

        if (mIsOpen) {
            mCurrentBackGroundColor = mBgOpenColor;
        } else {
            mCurrentBackGroundColor = mBgCloseColor;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec) ;
        int WidthMode = MeasureSpec.getMode(widthMeasureSpec) ;

        int heightSize = MeasureSpec.getSize(heightMeasureSpec) ;
        int heightMode =MeasureSpec.getMode(heightMeasureSpec) ;

        int width ;
        int height ;
        if(WidthMode == MeasureSpec.EXACTLY) {
            width = widthSize ;
        }else if(WidthMode == MeasureSpec.AT_MOST) {
            width  = Math.min(dp2px(120) , widthSize) ;
        }else {
            width = dp2px(120) ;
        }

        if(heightMode == MeasureSpec.EXACTLY) {
            height = heightSize ;
        }else if(heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(dp2px(60) , heightSize) ;
        }else {
            height = dp2px(60) ;
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
        this.mRadius = (mHeight - getPaddingTop() - getPaddingBottom()) / 2;
        this.mToggleWidth = (mWidth - getPaddingRight() - getPaddingLeft()) / 2;
        mCurrentXPosition = mToggleWidth / 2;
        Log.d(TAG, "mWidth:" + mWidth + ",mHeight:" + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);

        canvas.save();

        //1.画背景
        drawBackground(canvas);

        //2.画打开的状态
        drawState(canvas);
    }

    private void drawBackground(Canvas canvas) {
        mBackGroundPaint.setColor(mCurrentBackGroundColor);
        RectF recF = new RectF(mToggleWidth / 2 - mRadius, -mRadius, mToggleWidth / 2 + mRadius, mRadius);
        canvas.drawArc(recF, -90, 180, true, mBackGroundPaint);

        RectF tangle = new RectF(-mToggleWidth / 2, -mRadius, mToggleWidth / 2, mRadius);
        canvas.drawRect(tangle, mBackGroundPaint);

        RectF rectF = new RectF(-mToggleWidth / 2 - mRadius, -mRadius, -mToggleWidth / 2 + mRadius, mRadius);
        canvas.drawArc(rectF, 270, -180, true, mBackGroundPaint);

        canvas.restore();
    }

    private void drawState(Canvas canvas) {
        mInnerRingPaint.setColor(mCurrentBackGroundColor);
        canvas.drawCircle(mCurrentXPosition, 0, mRadius - mCircleRingWidth, mInnerRingPaint);
        canvas.drawCircle(mCurrentXPosition, 0, mRadius - 2 * mCircleRingWidth, mInnerCirclePaint);
    }

    public void setOpen(boolean isOpen) {
        if (mIsOpen != isOpen) {
            mIsOpen = isOpen;
            showAnimation(mIsOpen);
        }
    }

    private void showAnimation(final boolean isOpen) {
        final float start = isOpen ? -mToggleWidth / 2 : mToggleWidth / 2;
        float end = isOpen ? mToggleWidth / 2 : -mToggleWidth / 2;

        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.setDuration(mAnimationTime);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentXPosition = (float) valueAnimator.getAnimatedValue();
                setCurrentColor();
                invalidate();
            }
        });
        animator.start();

    }

    private void setCurrentColor() {
        float percent = (mCurrentXPosition + mToggleWidth / 2) * 1.0f / mToggleWidth;
        int currentR = (int) ((mEndBgColorR - mStartBgColorR) * percent + mStartBgColorR);
        int currentG = (int) ((mEndBgColorG - mStartBgColorG) * percent + mStartBgColorG);
        int currentB = (int) ((mEndBgColorB - mStartBgColorB) * percent + mStartBgColorB);
        mCurrentBackGroundColor = Color.rgb(currentR, currentG, currentB);
    }

    public boolean ToggleIsOpen() {
        return mIsOpen;
    }

    private int dp2px(int dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
