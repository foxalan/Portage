package com.example.fox_ui.tankgame.model.Obstacle;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import static com.example.fox_ui.tankgame.constant.Constant.RECT_LENGTH;

/**
 * @Author Alan
 * Date 2018/4/27 0027
 * Function
 * Issue
 */

public class ObstacleWood extends Obstacle{


    public ObstacleWood(boolean isAlive, int mPositionX, int mPositionY, int mType) {
        super(isAlive, mPositionX, mPositionY, mType);
    }

    public ObstacleWood(){

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if (isAlive()){
            int x = getPositionX();
            int y = getPositionY();
            Rect rect = new Rect(x * RECT_LENGTH, y * RECT_LENGTH, (x + 1) * RECT_LENGTH, (y + 1) * RECT_LENGTH);
            canvas.drawRect(rect, paint);
        }
    }
}
