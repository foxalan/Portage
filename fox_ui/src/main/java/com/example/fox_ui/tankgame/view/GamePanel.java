package com.example.fox_ui.tankgame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.fox_ui.tankgame.model.EnemyTank;
import com.example.fox_ui.tankgame.model.HeroTank;
import com.example.fox_ui.tankgame.presenter.PresenterGameViewImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import static com.example.fox_ui.tankgame.constant.Constant.MSG_INVALIDATE;
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

public class GamePanel extends View implements ITankControlListener, IGameControlListener {

    private int mPanelWidth = 800;
    private int mPanelHeight = 800;
    private int mCount = 20;
    private int mRectLength = 40;

    private PresenterGameViewImpl mInitImpl;
    private Paint mPaint;
    private Paint mHeroPaint;
    private Paint mEnemyPaint;
    private HeroTank mHeroTank;
    private List<EnemyTank> mEnemyTankList;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG_INVALIDATE:
                    invalidate();
                    break;
                default:
                        break;
            }
        }
    };

    public GamePanel(Context context) {
        this(context, null);
    }

    public GamePanel(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
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
        initPaints();
        mInitImpl = new PresenterGameViewImpl();
        mPaint = new Paint();
        mHeroTank = new HeroTank();
        mEnemyTankList = new ArrayList<>();

        mInitImpl.initPaints(mPaint);
        mInitImpl.initHeroTank(mHeroTank);
        mInitImpl.initEnemyTanks(mEnemyTankList);
    }

    /**
     * 初始化画笔
     */
    private void initPaints() {
        mHeroPaint = new Paint();
        mHeroPaint.setColor(Color.BLACK);
        mHeroPaint.setStyle(Paint.Style.STROKE);
        mHeroPaint.setStrokeWidth(2);

        mEnemyPaint = new Paint();
        mEnemyPaint.setColor(Color.RED);
        mEnemyPaint.setStyle(Paint.Style.STROKE);
        mEnemyPaint.setStrokeWidth(2);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mPanelWidth, mPanelHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRect(canvas);
        mHeroTank.drawTank(canvas, mHeroPaint);
        drawEnemyTanks(canvas);
    }

    private void drawEnemyTanks(Canvas canvas) {
        for (EnemyTank enemyTank : mEnemyTankList) {
            enemyTank.drawTank(canvas, mEnemyPaint);
        }
    }

    /**
     * 画小块块
     *
     * @param canvas
     */
    private void drawRect(Canvas canvas) {
        for (int i = 0; i < mCount; i++) {
            for (int j = 0; j < mCount; j++) {
                Rect rect = new Rect(j * mRectLength, i * mRectLength, (j + 1) * mRectLength, (i + 1) * mRectLength);
                canvas.drawRect(rect, mPaint);
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
    }

    @Override
    public void moveDown() {
        mHeroTank.move(TANK_DIRECTION_DOWN);
        invalidate();
    }

    @Override
    public void moveLeft() {
        mHeroTank.move(TANK_DIRECTION_LEFT);
        invalidate();
    }

    @Override
    public void moveRight() {
        mHeroTank.move(TANK_DIRECTION_RIGHT);
        invalidate();
    }

    @Override
    public void shutBullet() {

    }

    /**
     *待优化
     * 不能在非UI线程中更新UI
     */
    @Override
    public void startGame() {
       mInitImpl.initEnemyTanksMove(mEnemyTankList,this,mHandler);

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void reStartGame() {

    }

    @Override
    public void stopGame() {

    }
}
