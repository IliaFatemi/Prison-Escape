package com.group10.app.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import com.group10.app.SavedData.SaveGame;
import com.group10.app.main.GamePanel.STATE;
import com.group10.app.App;

/**
 * Mouse manager will be the control for the mouse on the screen. MouseManager implements MouseListener. This class is used only for the Menu screens.
 * @author Ilia Fatemi
 */
public class MouseManager implements MouseListener{

    /**
     * Using the objects in GamePanel
     */
    GamePanel gb;

    /**
     * Using the saveGame when quitting or saving
     */
    SaveGame saveGame;

    /**
     * The MouseManager constructor will activate the mouse controls
     * @param gb the GamePanel object
     */
    public MouseManager(GamePanel gb){
        this.gb = gb;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * <p>The mouseReleased method will be control all the Menu screens. The mouseReleased can be used in the game to only displaye the pixel and column/row location on the console terminal</p>
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println(mouseX + ", " + mouseY);
        System.out.println("col: "+mouseX/gb.cellSize + ", " + "row: "+mouseY/gb.cellSize);

        //Mouse control works only in main menu
        if(GamePanel.state != STATE.GAME && GamePanel.state != STATE.PAUSED && GamePanel.state != STATE.GAMEWON && GamePanel.state != STATE.GAMEOVER){
            MainMenuControls(mouseX, mouseY);
        }

        //Mouse control works only in pause menu
        if(GamePanel.state != STATE.GAME && GamePanel.state != STATE.MENU && GamePanel.state != STATE.GAMEWON && GamePanel.state != STATE.GAMEOVER){
            PauseMenuControls(mouseX, mouseY);
        }

        //Mouse control for game won menu
        if(GamePanel.state != STATE.GAME && GamePanel.state != STATE.MENU && GamePanel.state != STATE.GAMEOVER && GamePanel.state != STATE.PAUSED){
            GameWonMenuControls(mouseX, mouseY);
        }

        //Mouse control for game over menu
        if(GamePanel.state != STATE.GAME && GamePanel.state != STATE.MENU && GamePanel.state != STATE.GAMEWON && GamePanel.state != STATE.PAUSED){
            GameOverMenuControls(mouseX, mouseY);
        }
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    /**
     * <p>MainMenuControls will only work on the main menu screen if the state of the game is GAME</p>
     * @param mouseX integer location for mouse X position
     * @param mouseY integer location for mouse Y position
     */
    public void MainMenuControls(int mouseX, int mouseY){

        //new game button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2-300 && mouseY <= gb.screenHeight/2-230){
                System.out.println("Starting new game");
                gb.levelCheck();
                gb.inmate.resetKeys();
                gb.inmate.resetScore();
                gb.inmate.setTimer(100);
                System.out.println("Resetting number of keys");
                GamePanel.state = GamePanel.STATE.GAME;
            }
        }

        //Continue button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 200 && mouseY <= gb.screenHeight/2 - 130){
                System.out.println("Continuing game");
                gb.load.loadData();
                GamePanel.GAME_LEVEL = gb.load.loadLevel();
                gb.inmate.setPos(gb.load.loadPlayerX(), gb.load.loadPlayerY());
                gb.inmate.setTimer(gb.load.loadTimer());
                gb.inmate.setScore(gb.load.loadScore());
                gb.inmate.setNumKeys(gb.load.loadNumKeys());
                GamePanel.state = STATE.GAME;
                System.out.println("loading Complete");
                gb.levelCheck();
            }
        }

        //Exit game button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 100 && mouseY <= gb.screenHeight/2 - 30){
                //Close the screen
                System.out.println("Exiting game");
                App.window.dispatchEvent(new WindowEvent(App.window, WindowEvent.WINDOW_CLOSING));
            }
        }
    }

    /**
     * <p>PauseMenuControls will only work on the pause menu screen if the state of the game is PAUSE</p>
     * @param mouseX integer location for mouse X position
     * @param mouseY integer location for mouse Y position
     */
    public void PauseMenuControls(int mouseX, int mouseY){
        //Resume button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(103)){
            if(mouseY >= gb.screenHeight/2 - 130 && mouseY <= gb.screenHeight/2-60){
                System.out.println("resuming game");
                GamePanel.state = GamePanel.STATE.GAME;
            }
        }
        
        //Save game controls
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(103)){
            if(mouseY >= gb.screenHeight/2 -30 && mouseY <= gb.screenHeight/2 + 40){
                gb.saveGame.save(GamePanel.GAME_LEVEL,(int) gb.inmate.getX(),(int) gb.inmate.getY(), gb.inmate.getTimer(), gb.inmate.getScore(), gb.inmate.getNumKeys(), 0, 0);   
                System.out.println("Saving game");
            }
        }

        //return to main menu controls
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(103)){
            if(mouseY >= gb.screenHeight/2 + 70 && mouseY <= gb.screenHeight/2 + 140){
                gb.saveGame.save(GamePanel.GAME_LEVEL,(int) gb.inmate.getX(),(int) gb.inmate.getY(), gb.inmate.getTimer(), gb.inmate.getScore(), gb.inmate.getNumKeys(), 0, 0);       
                System.out.println("returning to Main menu");
                GamePanel.state = STATE.MENU;
            }
        }
    }

    /**
     * <p>GameWonMenuControls will only work on the Winning menu screen if the state of the game is GAMEWON</p>
     * @param mouseX integer location for mouse X position
     * @param mouseY integer location for mouse Y position
     */
    public void GameWonMenuControls(int mouseX, int mouseY){
        //next level button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2-130 && mouseY <= gb.screenHeight/2-60){
                System.out.println("Next level");
                GamePanel.GAME_LEVEL++;
                System.out.println("(Update) Level: "+ GamePanel.GAME_LEVEL);
                if(GamePanel.GAME_LEVEL > 3){
                    GamePanel.GAME_LEVEL = 1;
                }
                gb.levelCheck();
            }
        }

        //return to main menu button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 30 && mouseY <= gb.screenHeight/2 + 40){
                GamePanel.GAME_LEVEL++;
                System.out.println("(Update) Level: "+GamePanel.GAME_LEVEL);
                gb.saveGame.save(GamePanel.GAME_LEVEL,(int) gb.inmate.getX(),(int) gb.inmate.getY(), gb.inmate.getTimer(), gb.inmate.getScore(), gb.inmate.getNumKeys(), 0, 0);   
                System.out.println(mouseX + " "+ mouseY + ": returning to Main menu");
                GamePanel.state = STATE.MENU;
            }
        }
    }

    /**
     * <p>GameOverMenuControls will only work on the gamae over menu screen if the state of the game is GAMEOVER</p>
     * @param mouseX integer location for mouse X position
     * @param mouseY integer location for mouse Y position
     */
    public void GameOverMenuControls(int mouseX, int mouseY){
        //Retry level button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2-130 && mouseY <= gb.screenHeight/2-60){
                System.out.println("Retry Level");
                gb.levelCheck();
                GamePanel.state = STATE.GAME;
            }
        }

        //return to main menu button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 30 && mouseY <= gb.screenHeight/2 + 40){
                System.out.println("returning to Main menu");
                gb.inmate.resetInmate();
                GamePanel.state = STATE.MENU;
            }   
        }
    }
}