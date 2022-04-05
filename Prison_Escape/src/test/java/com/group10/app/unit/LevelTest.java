package com.group10.app.unit;

import com.group10.app.main.GamePanel;
import com.group10.app.main.GameStates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LevelTest {
    private GamePanel gp;

    @BeforeEach
    void setUp(){
        gp = new GamePanel();
    }

    @Test
    public void firstToSecondLevelTest(){
        gp.setState(GameStates.GAMEWON);
        gp.setGameLevel(1);
        gp.mouseK.GameWonMenuControls(711, 336);
        assertEquals(2, gp.getGameLevel());

    }

    @Test
    public void secondToFirstLevelTest(){
        gp.setState(GameStates.GAMEWON);
        gp.setGameLevel(2);
        gp.mouseK.GameWonMenuControls(711, 336);
        assertEquals(3, gp.getGameLevel());

    }

    @Test
    public void thirdToFirstLevelTest(){
        gp.setState(GameStates.GAMEWON);
        gp.setGameLevel(3);
        gp.mouseK.GameWonMenuControls(711, 336);
        assertEquals(1, gp.getGameLevel());
    }

    @Test
    public void firstToFirstLevelGameOverStateTest(){
        gp.setState(GameStates.GAMEOVER);
        gp.setGameLevel(1);
        gp.mouseK.GameOverMenuControls(719, 335);
        assertEquals(1, gp.getGameLevel());
    }

    @Test
    public void secondToSecondLevelGameOverStateTest(){
        gp.setState(GameStates.GAMEOVER);
        gp.setGameLevel(2);
        gp.mouseK.GameOverMenuControls(719, 335);
        assertEquals(2, gp.getGameLevel());
    }

    @Test
    public void thirdToThirdLevelGameOverStateTest(){
        gp.setState(GameStates.GAMEOVER);
        gp.setGameLevel(3);
        gp.mouseK.GameOverMenuControls(719, 335);
        assertEquals(3, gp.getGameLevel());
    }

    @Test
    public void newGameLevelTest(){
        gp.setState(GameStates.MENU);
        gp.setGameLevel(1);
        gp.mouseK.MainMenuControls(700,  168);
        assertEquals(1, gp.getGameLevel());
    }

    @Test
    public void firstToFirstLevelGameStateTest(){
        gp.setState(GameStates.GAME);
        gp.setGameLevel(1);
        gp.mouseK.PauseMenuControls(719, 335);
        assertEquals(1, gp.getGameLevel());
    }

    @Test
    public void secondToSecondLevelGameStateTest(){
        gp.setState(GameStates.GAME);
        gp.setGameLevel(2);
        gp.mouseK.PauseMenuControls(719, 335);
        assertEquals(2, gp.getGameLevel());
    }

    @Test
    public void thirdToThirdLevelGameStateTest(){
        gp.setState(GameStates.GAME);
        gp.setGameLevel(3);
        gp.mouseK.PauseMenuControls(719, 335);
        assertEquals(3, gp.getGameLevel());
    }

}
