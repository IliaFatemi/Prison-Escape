package com.group10.app.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import com.group10.app.SavedData.SaveGame;
import com.group10.app.App;

import static com.group10.app.main.GameStates.*;

/**
 * Mouse manager will be the control for the mouse on the screen. MouseManager implements MouseListener. This class is used only for the Menu screens.
 * @author Ilia Fatemi
 */
public class MouseManager implements MouseListener{

    /**
     * Using the objects in GamePanel
     */
    GamePanel gp;

    /**
     * Using the saveGame when quitting or saving
     */
    SaveGame saveGame;

    /**
     * The MouseManager constructor will activate the mouse controls
     * @param gp the GamePanel object
     */
    public MouseManager(GamePanel gp){
        this.gp = gp;
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
        System.out.println("col: "+mouseX/gp.cellSize + ", " + "row: "+mouseY/gp.cellSize);

        //Mouse control works only in main menu
        if(GamePanel.state != GAME && GamePanel.state != PAUSED && GamePanel.state != GAMEWON && GamePanel.state != GAMEOVER){
            MainMenuControls(mouseX, mouseY);
        }

        //Mouse control works only in pause menu
        if(GamePanel.state != GAME && GamePanel.state != MENU && GamePanel.state != GAMEWON && GamePanel.state != GAMEOVER){
            PauseMenuControls(mouseX, mouseY);
        }

        //Mouse control for game won menu
        if(GamePanel.state != GAME && GamePanel.state != MENU && GamePanel.state != GAMEOVER && GamePanel.state != PAUSED){
            GameWonMenuControls(mouseX, mouseY);
        }

        //Mouse control for game over menu
        if(GamePanel.state != GAME && GamePanel.state != MENU && GamePanel.state != GAMEWON && GamePanel.state != PAUSED){
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
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2-300 && mouseY <= gp.screenHeight/2-230){
                System.out.println("Starting new game");
                gp.levelCheck();
                System.out.println("Resetting number of keys");
                GamePanel.state = GAME;
            }
        }

        //Continue button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 - 200 && mouseY <= gp.screenHeight/2 - 130){
                System.out.println("Continuing game");
                GamePanel.state = GAME;
                System.out.println("loading Complete");
                gp.tileManage.loadMap("/levels/Level" + GamePanel.GAME_LEVEL + ".txt");
            }
        }

        //Exit game button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 - 100 && mouseY <= gp.screenHeight/2 - 30){
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
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(103)){
            if(mouseY >= gp.screenHeight/2 - 130 && mouseY <= gp.screenHeight/2-60){
                System.out.println("resuming game");
                GamePanel.state = PAUSED;
            }
        }
        //return to main menu controls
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(103)){
            if(mouseY >= gp.screenHeight/2 + 70 && mouseY <= gp.screenHeight/2 + 140){
                gp.saveGame.save(GamePanel.GAME_LEVEL, gp.inmate.x, gp.inmate.y,
                        gp.inmate.time, gp.inmate.score, gp.inmate.hasKey,
                        gp.obj, gp.guard);
                System.out.println("returning to Main menu");
                GamePanel.state = MENU;
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
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2-130 && mouseY <= gp.screenHeight/2-60){
                System.out.println("Next level");
                GamePanel.GAME_LEVEL++;
                System.out.println("(Update) Level: "+ GamePanel.GAME_LEVEL);
                if(GamePanel.GAME_LEVEL > 3){
                    GamePanel.GAME_LEVEL = 1;
                    GamePanel.state = MENU;
                }else{
                    gp.levelCheck();
                }
            }
        }

        //return to main menu button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 - 30 && mouseY <= gp.screenHeight/2 + 40){
                GamePanel.GAME_LEVEL++;
                System.out.println("(Update) Level: "+GamePanel.GAME_LEVEL);
                gp.saveGame.save(GamePanel.GAME_LEVEL,(int) gp.inmate.getX(),(int) gp.inmate.getY(), gp.inmate.getTimer(), gp.inmate.getScore(), gp.inmate.getNumKeys(), gp.obj, gp.guard);   
                System.out.println(mouseX + " "+ mouseY + ": returning to Main menu");
                GamePanel.state = MENU;
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
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2-130 && mouseY <= gp.screenHeight/2-60){
                System.out.println("Retry Level");
                gp.levelCheck();
                GamePanel.state = GAME;
            }
        }

        //return to main menu button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 - 30 && mouseY <= gp.screenHeight/2 + 40){
                System.out.println("returning to Main menu");
                gp.inmate.resetInmate();
                GamePanel.state = MENU;
            }   
        }
    }
}