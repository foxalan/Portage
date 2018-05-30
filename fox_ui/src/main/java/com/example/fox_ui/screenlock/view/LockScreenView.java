package com.example.fox_ui.screenlock.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.fox_core.util.dimen.DimenUtil;

/**
 * @Author Alan
 * Date 2018/5/22 0022
 * Function :
 * Issue :
 */

public class LockScreenView extends View {

    private int mWidth;
    private int mHeight;


    private Paint mPaintPoint;
    private Paint mPaintLine;

    public LockScreenView(Context context) {
        this(context, null);
    }

    public LockScreenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LockScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaints();
    }

    /**
     * 初始化画笔
     */
    private void initPaints() {
        mPaintPoint = new Paint();
        mPaintPoint.setStyle(Paint.Style.STROKE);
        mPaintPoint.setStrokeWidth(2);
        mPaintPoint.setColor(Color.RED);

        mPaintLine = new Paint();
        mPaintLine.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintLine.setStrokeWidth(2);
        mPaintLine.setColor(Color.GREEN);
    }


    /**
     * 1.设置高度和宽度
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = DimenUtil.getScreenWidth() * 3 / 4;
        mHeight = mWidth;

        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPoints(canvas);

    }

    /**
     * 画圆
     *
     * @param canvas
     */
    private void drawPoints(Canvas canvas) {


    }

    /**
     * 监听移动
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:


                break;
            case MotionEvent.ACTION_UP:
                //检查是否正确

                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    /**
     * 检查
     */
    public void checkLockResult(){



    }

}
