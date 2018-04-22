package com.example.fox_ui.tankgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import com.example.fox_ui.tankgame.model.HeroTank;
import com.example.fox_ui.tankgame.presenter.PresenterGameViewImpl;

/**
 * @Author Alan
 * Date 2018/4/22 0022
 * Function
 * Issue
 */

public class GamePanel extends View {

    private int mPanelWidth = 600;
    private int mPanelHeight = 600;
    private int mCount = 30;
    private int mRectLength = 20;

    private PresenterGameViewImpl mInitImpl;
    private Paint mPaint;
    private HeroTank mTank;

    public GamePanel(Context context) {
        this(context,null);
    }

    public GamePanel(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GamePanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置获取焦点
        setFocusable(true);
        init();
    }

    /**
     * 数据初始化
     */
    private void init() {
        mInitImpl = new PresenterGameViewImpl();
        mPaint = new Paint();

        mInitImpl.initPaints(mPaint);
        mInitImpl.initHeroTank(mTank);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mPanelWidth,mPanelHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRect(canvas);
    }

    /**
     * 画小块块
     * @param canvas
     */
    private void drawRect(Canvas canvas) {
        for(int i =0;i<mCount;i++){
            for(int j =0;j<mCount;j++){
                Rect rect = new Rect();
                rect.contains(i*mRectLength,j*mRectLength,(i+1)*mRectLength,(j+1)*mRectLength);
                canvas.drawRect(rect,mPaint);
            }
        }
    }

    /**
     * 设置键盘监听事件
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }
}
