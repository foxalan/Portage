package com.example.fox_ui.tankgame.view;

/**
 * @Author Alan
 * Date 2018/4/26 0026
 * Function
 * Issue
 */

public interface IGameControlListener {

    /**
     * 开始游戏
     */
    void startGame();

    /**
     * 暂停游戏
     */
    void pauseGame();

    /**
     * 接着游戏
     */
    void reStartGame();

    /**
     * 结束游戏
     */
    void stopGame();
}
