package com.group10.app.unit;

import com.group10.app.entity.nonStaticEntities.Inmate;
import com.group10.app.main.GamePanel;
import com.group10.app.main.GameStates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ScoreTest {
    private GamePanel gp;

    @BeforeEach
    void setUp(){
        gp = new GamePanel();
        gp.inmate = new Inmate(gp, gp.keyH);
    }

    @Test
    public void scoreNewGame(){
        gp.setState(GameStates.MENU);
        gp.mouseK.MainMenuControls(700,  168);
        assertEquals(0, gp.inmate.getScore());
    }

    @Test
    public void scoreInGame(){
        gp.setState(GameStates.GAME);
        gp.mouseK.PauseMenuControls(719,  335);
        assertEquals(0, gp.inmate.getScore());
    }

    @Test
    public void scoreGameWon(){
        gp.setState(GameStates.GAMEWON);
        gp.mouseK.GameOverMenuControls(711,  336);
        assertEquals(0, gp.inmate.getScore());
    }

    @Test
    public void scoreGameOver(){
        gp.setState(GameStates.GAMEOVER);
        gp.mouseK.GameWonMenuControls(700,  168);
        assertEquals(0, gp.inmate.getScore());
    }





}
