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
    MouseManager ms;
    Inmate inmate;
    KeyManager key;

    @BeforeEach
    public void setUp(){
        gp = new GamePanel();
        ms = new MouseManager(gp);
        inmate = new Inmate(gp, key);

        inmate.setScore(250);
        inmate.setTimer(250);
        inmate.setNumKeys(5);


    }

    @Test
    public void GameOverMenuTest(){

        gp.setState(GameStates.GAMEOVER);
        ms.GameOverMenuControls(756, 342);


        assertEquals(0, inmate.getScore());
        assertEquals(100, inmate.getTimer());
        assertEquals(0, inmate.getNumKeys());
    }
}
