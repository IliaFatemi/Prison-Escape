package com.group10.app.unit;

import com.group10.app.entity.nonStaticEntities.Inmate;
import com.group10.app.main.GamePanel;
import com.group10.app.main.GameStates;
import com.group10.app.main.KeyManager;
import com.group10.app.main.MouseManager;
import com.group10.app.menu.GameOverMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    GamePanel gp;

    @BeforeEach
    public void setUp(){
        gp = new GamePanel();
        gp.keyH = new KeyManager();
        gp.keyH.pressedUp = true;

        gp.setState(GameStates.GAME);

        gp.inmate = new Inmate(gp, gp.keyH);

        gp.inmate.setX(50);
        gp.inmate.setY(50);

        gp.inmate.setScore(200);
        gp.inmate.setTimer(100);

    }

//    @Test
//    public void GameOverMenuTest(){
//
//        gp.setState(GameStates.GAMEOVER);
//        ms.GameOverMenuControls(756, 342);
//        gp.mouseK.GameOverMenuControls(707, 433);
//
//
//        assertEquals(0, inmate.getScore());
//        assertEquals(100, inmate.getTimer());
//        assertEquals(0, inmate.getNumKeys());
//    }
}
