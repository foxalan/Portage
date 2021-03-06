package com.example.fox_ui.tankgame;

import android.view.View;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_ui.R;
import com.example.fox_ui.tankgame.view.GamePanel;

/**
 * @Author Alan
 * Date 2018/4/22 0022
 * Function
 * Issue :1线程冲突，导致两个线程修改一个变量
 */

public class TankFragment extends LatteDelegate implements View.OnClickListener {

    private GamePanel gamePanel;

    @Override
    public Object getLayout() {
        return R.layout.fragment_tankgame;
    }

    @Override
    public void onBindView(View rootView) {

        gamePanel = rootView.findViewById(R.id.gamePanel);
        rootView.findViewById(R.id.btn_move_up).setOnClickListener(this);
        rootView.findViewById(R.id.btn_move_down).setOnClickListener(this);
        rootView.findViewById(R.id.btn_move_left).setOnClickListener(this);
        rootView.findViewById(R.id.btn_move_right).setOnClickListener(this);
        rootView.findViewById(R.id.btn_bullet).setOnClickListener(this);
        rootView.findViewById(R.id.btn_start_game).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_move_up) {
            gamePanel.moveUp();
        } else if (i == R.id.btn_move_down) {
            gamePanel.moveDown();
        } else if (i == R.id.btn_move_left) {
            gamePanel.moveLeft();
        } else if (i == R.id.btn_move_right) {
            gamePanel.moveRight();
        } else if (i == R.id.btn_bullet) {
            gamePanel.shutBullet();
        } else if (i == R.id.btn_start_game) {
            gamePanel.startGame();
        }
    }
}
