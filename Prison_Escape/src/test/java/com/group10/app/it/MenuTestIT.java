package com.group10.app.it;


import com.group10.app.main.GamePanel;
import com.group10.app.main.GameStates;
import com.group10.app.main.MouseManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.MouseEvent;

import static com.group10.app.main.GameStates.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MenuTestIT {
    private GamePanel gp;
    MouseEvent e;

    @BeforeEach
    void setUp(){
        gp = new GamePanel();
        gp.mouseK = new MouseManager(gp);

    }

    @Test
    public void mainMenuButton(){
        assertEquals(GameStates.MENU, gp.getState());

        gp.mouseK.MainMenuControls(681, 277);
        assertEquals(GameStates.GAME, gp.getState());

        gp.setState(GameStates.MENU);
        gp.mouseK.MainMenuControls(735,371);
        assertEquals(GameStates.ERROR_MENU, gp.getState());

        gp.setState(GameStates.MENU);
        gp.mouseK.MainMenuControls(712,462);
        assertEquals(GameStates.HELP_MENU, gp.getState());

        gp.setState(GameStates.MENU);
        gp.mouseK.MainMenuControls(716,567);
        assertEquals(GameStates.CREDITS_MENU, gp.getState());

        gp.setState(GameStates.MENU);
    }

    @Test
    public void pauseMenuButton(){
        gp.setState(PAUSED);
        assertEquals(PAUSED, gp.getState());

        gp.mouseK.PauseMenuControls(700,333);
        assertEquals(GameStates.GAME, gp.getState());

        gp.setState(PAUSED);
        gp.mouseK.PauseMenuControls(734, 419);
        assertEquals(GameStates.MENU, gp.getState());
    }

    @Test
    public void gameOverMenuButton(){
        gp.setState(GAMEOVER);
        assertEquals(GAMEOVER, gp.getState());

        gp.mouseK.GameOverMenuControls(700,333);
        assertEquals(GameStates.GAME, gp.getState());

        gp.setState(GAMEOVER);
        gp.mouseK.GameOverMenuControls(700,433);
        assertEquals(GameStates.MENU, gp.getState());
    }

    @Test
    public void gameWonMenuButton(){
        gp.setState(GAMEWON);
        assertEquals(GAMEWON, gp.getState());

        gp.mouseK.GameWonMenuControls(700,333);
        assertEquals(GameStates.GAME, gp.getState());

        gp.setState(GAMEWON);
        gp.mouseK.GameWonMenuControls(700,433);
        assertEquals(GameStates.MENU, gp.getState());
    }
}
