package com.example.fox_ui.tankgame.model.Obstacle;

import com.example.fox_ui.tankgame.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Alan
 * Date 2018/4/27 0027
 * Function
 * Issue
 */

public class ObstacleCreater {

    public static List<ObstacleWood> create(){
        List<ObstacleWood> obstacleWoods = new ArrayList<>();
        for(int i = 30;i<35;i++){
            for(int j = 30 ; j< 45;j++){
                ObstacleWood wood = new ObstacleWood();
                wood.setAlive(true);
                wood.setPositionX(i);
                wood.setPositionY(j);
                wood.setType(Constant.OBSTACLE_TYPE_EASY_DES);
                obstacleWoods.add(wood);
            }
        }

        return obstacleWoods;
    }
}
