package com.example.fox_ui.tankgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.example.fox_core.util.L;
import com.example.fox_ui.tankgame.constant.Constant;
import com.example.fox_ui.tankgame.model.HeroTank;
import com.example.fox_ui.tankgame.presenter.PresenterGameViewImpl;

import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_DOWN;
import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_LEFT;
import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_RIGHT;
import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_UP;

/**
 * @Author Alan
 * Date 2018/4/22 0022
 * Function
 * Issue
 */

public class GamePanel extends View implements ITankControlListener{

    private int mPanelWidth = 800;
    private int mPanelHeight = 800;
    private int mCount = 20;
    private int mRectLength = 40;

    private PresenterGameViewImpl mInitImpl;
    private Paint mPaint;
    private HeroTank mHeroTank;

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
        mHeroTank = new HeroTank();

        mInitImpl.initPaints(mPaint);
        mInitImpl.initHeroTank(mHeroTank);
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
        mHeroTank.drawTank(canvas);

    }

    /**
     * 画小块块
     * @param canvas
     */
    private void drawRect(Canvas canvas) {
        for(int i =0;i<mCount;i++){
            for(int j =0;j<mCount;j++){
               Rect rect = new Rect(j*mRectLength,i*mRectLength,(j+1)*mRectLength,(i+1)*mRectLength);
               canvas.drawRect(rect,mPaint);
            }
        }
    }

    /**
     * 设置坦克的移动
     */
    @Override
    public void moveUp() {
        mHeroTank.move(TANK_DIRECTION_UP);
        invalidate();
        L.e("tank move up");
    }

    @Override
    public void moveDown() {
        mHeroTank.move(TANK_DIRECTION_DOWN);
        L.e("tank move down");
    }

    @Override
    public void moveLeft() {
        mHeroTank.move(TANK_DIRECTION_LEFT);
        L.e("tank move left");
    }

    @Override
    public void moveRight() {
        mHeroTank.move(TANK_DIRECTION_RIGHT);
        L.e("tank move right");
    }

    @Override
    public void shutBullet() {

    }
}
