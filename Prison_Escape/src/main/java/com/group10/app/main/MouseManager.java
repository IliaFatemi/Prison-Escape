package com.group10.app.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import com.group10.app.App;
import com.group10.app.SavedData.SaveGame;

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
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

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
        if(GamePanel.state != GAME && GamePanel.state != PAUSED && GamePanel.state != GAMEWON && GamePanel.state != GAMEOVER && GamePanel.state != HELP_MENU && GamePanel.state != CREDITS_MENU){
            MainMenuControls(mouseX, mouseY);
        }

        //Mouse control works only in help menu
        if(GamePanel.state != GAME && GamePanel.state != MENU && GamePanel.state != GAMEWON && GamePanel.state != GAMEOVER && GamePanel.state != PAUSED && GamePanel.state != CREDITS_MENU){
            HelpMenuControls(mouseX, mouseY);
        }

        //Mouse control works only in credits menu
        if(GamePanel.state != GAME && GamePanel.state != MENU && GamePanel.state != GAMEWON && GamePanel.state != GAMEOVER && GamePanel.state != PAUSED && GamePanel.state != HELP_MENU){
            HelpMenuControls(mouseX, mouseY);
        }

        //Mouse control works only in pause menu
        if(GamePanel.state != GAME && GamePanel.state != MENU && GamePanel.state != GAMEWON && GamePanel.state != GAMEOVER && GamePanel.state != HELP_MENU && GamePanel.state != CREDITS_MENU){
            PauseMenuControls(mouseX, mouseY);
        }

        //Mouse control for game won menu
        if(GamePanel.state != GAME && GamePanel.state != MENU && GamePanel.state != GAMEOVER && GamePanel.state != PAUSED && GamePanel.state != HELP_MENU && GamePanel.state != CREDITS_MENU){
            GameWonMenuControls(mouseX, mouseY);
        }

        //Mouse control for game over menu
        if(GamePanel.state != GAME && GamePanel.state != MENU && GamePanel.state != GAMEWON && GamePanel.state != PAUSED && GamePanel.state != HELP_MENU && GamePanel.state != CREDITS_MENU){
            GameOverMenuControls(mouseX, mouseY);
        }
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * <p>MainMenuControls will only work on the main menu screen if the state of the game is GAME</p>
     * @param mouseX integer location for mouse X position
     * @param mouseY integer location for mouse Y position
     */
    public void MainMenuControls(int mouseX, int mouseY){

        //new game button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 - 200 && mouseY <= gp.screenHeight/2 - 130){
                System.out.println("Starting new game");
                GamePanel.GAME_LEVEL = 1;
                gp.levelCheck();
                System.out.println("Resetting number of keys");
                GamePanel.state = GAME;
            }
        }

        //Continue button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 - 100 && mouseY <= gp.screenHeight/2 - 30){
                if (gp.loadGame.isEmpty("saveFile.txt") && GamePanel.GAME_LEVEL != 1) {
                    GamePanel.state = HELP_MENU;
                }
                else {
                    System.out.println("Continuing game");
                    GamePanel.state = GAME;
                    gp.loadGame.loadData("saveFile.txt");
                    System.out.println("loading Complete");

                    gp.music.playMusic(8);
                }

            }
        }

        //Help button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 && mouseY <= gp.screenHeight/2 + 70){
                System.out.println("Help display");
                GamePanel.state = HELP_MENU;
            }
        }

        //Credits Button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 + 100 && mouseY <= gp.screenHeight/2 + 170){
                System.out.println("Credits display");
                gp.setState(CREDITS_MENU);
            }
        }

        //Exit game button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 + 200 && mouseY <= gp.screenHeight/2 + 270){
                //Close the screen
                System.out.println("Exiting game");
                App.window.dispatchEvent(new WindowEvent(App.window, WindowEvent.WINDOW_CLOSING));
            }
        }
    }

    /**
     * <p>HelpMenuControls will only work on the main menu screen if the state of the game is MENU</p>
     * @param mouseX integer location for mouse X position
     * @param mouseY integer location for mouse Y position
     */
    public void HelpMenuControls(int mouseX, int mouseY){
        //Return to menu button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 + 350 && mouseY <= gp.screenHeight/2 + 398){
                //Close the screen
                System.out.println("return to main menu");
                GamePanel.state = MENU;
            }
        }
    }

    /**
     * <p>HelpMenuControls will only work on the main menu screen if the state of the game is MENU</p>
     * @param mouseX integer location for mouse X position
     * @param mouseY integer location for mouse Y position
     */
    public void CreditsMenuControls(int mouseX, int mouseY){
        //Return to menu button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 + 350 && mouseY <= gp.screenHeight/2 + 398){
                //Close the screen
                System.out.println("return to main menu");
                GamePanel.state = MENU;
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
                GamePanel.state = GAME;
            }
        }
        //return to main menu controls
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(103)){
            if(mouseY >= gp.screenHeight/2 + 70 && mouseY <= gp.screenHeight/2 + 140){
                gp.saveGame.mySave("saveFile.txt");
                System.out.println("returning to Main menu");
                GamePanel.state = MENU;

                gp.music.playMusic(0);
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
                    GamePanel.state = CREDITS_MENU;
                }else{
                    gp.levelCheck();
                }

                gp.music.playMusic(8);
            }
        }

        //return to main menu button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 - 30 && mouseY <= gp.screenHeight/2 + 40){
                GamePanel.GAME_LEVEL++;
                System.out.println("(Update) Level: "+GamePanel.GAME_LEVEL);
                gp.levelCheck();
                System.out.println(mouseX + " "+ mouseY + ": returning to Main menu");
                GamePanel.state = MENU;

                gp.music.playMusic(0);
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

                gp.music.playMusic(8);
            }
        }

        //return to main menu button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 - 30 && mouseY <= gp.screenHeight/2 + 40){
                System.out.println("returning to Main menu");
                gp.levelCheck();
                gp.saveGame.mySave("saveFile.txt");
                GamePanel.state = MENU;

                gp.music.playMusic(0);
            }   
        }
    }
}