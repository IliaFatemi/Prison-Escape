package com.group10.app.it;

import com.group10.app.entity.nonStaticEntities.Inmate;
import com.group10.app.entity.staticEntities.Chicken;
import com.group10.app.main.GamePanel;
import com.group10.app.main.GameStates;
import com.group10.app.main.KeyManager;
import com.group10.app.main.MouseManager;
import com.group10.app.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.security.Key;

import static com.group10.app.main.GameStates.*;
import static java.awt.event.KeyEvent.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTestIT {
    private GamePanel gp;

    @BeforeEach
    void setUp(){
        gp = new GamePanel();
        gp.mouseK = new MouseManager(gp);

    }

    @Test
    public void mainMenuButton(){
        assertEquals(GameStates.MENU, gp.getState());

        gp.mouseK.MainMenuControls(700,168);
        assertEquals(GameStates.GAME, gp.getState());

        gp.setState(GameStates.MENU);
        gp.mouseK.MainMenuControls(700,268);
        assertEquals(GameStates.GAME, gp.getState());

        gp.setState(GameStates.MENU);
//        gp.mouseK.MainMenuControls(700,368);
//        assertEquals(null, gp);
    }

    @Test
    public void pauseMenuButton(){
        gp.setState(PAUSED);
        assertEquals(PAUSED, gp.getState());

        gp.mouseK.PauseMenuControls(700,333);
        assertEquals(GameStates.GAME, gp.getState());

        gp.setState(PAUSED);
        gp.mouseK.PauseMenuControls(700,533);
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
