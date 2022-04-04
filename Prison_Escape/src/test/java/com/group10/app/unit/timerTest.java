package com.group10.app.unit;

import com.group10.app.entity.nonStaticEntities.Inmate;
import com.group10.app.main.GamePanel;
import com.group10.app.main.GameStates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class timerTest {
    private GamePanel gp;

    @BeforeEach
    void setUp(){
        gp = new GamePanel();
        gp.inmate = new Inmate(gp, gp.keyH);
    }

    @Test
    public void timerNewGame(){
        gp.setState(GameStates.MENU);
        gp.mouseK.MainMenuControls(700,  168);
        assertEquals(100, gp.inmate.getTimer());
    }

    @Test
    public void timerInGame(){
        gp.setState(GameStates.GAME);
        gp.mouseK.PauseMenuControls(719,  335);
        assertEquals(100, gp.inmate.getTimer());
    }

    @Test
    public void timerGameWon(){
        gp.setState(GameStates.GAMEWON);
        gp.mouseK.GameOverMenuControls(711,  336);
        assertEquals(100, gp.inmate.getTimer());
    }

    @Test
    public void timerGameOver(){
        gp.setState(GameStates.GAMEOVER);
        gp.mouseK.GameWonMenuControls(700,  168);
        assertEquals(100, gp.inmate.getTimer());
    }



}
