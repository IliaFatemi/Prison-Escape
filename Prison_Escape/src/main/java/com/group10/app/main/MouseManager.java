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
        if(gp.getState() != GAME && gp.getState() != PAUSED && gp.getState() != GAMEWON && gp.getState() != GAMEOVER && gp.getState() != HELP_MENU && gp.getState() != CREDITS_MENU && gp.getState() != ERROR_MENU){
            MainMenuControls(mouseX, mouseY);
        }

        //Mouse control works only in help menu
        if(gp.getState() != GAME && gp.getState() != MENU && gp.getState() != GAMEWON && gp.getState() != GAMEOVER && gp.getState() != PAUSED && gp.getState() != CREDITS_MENU && gp.getState() != ERROR_MENU){
            HelpMenuControls(mouseX, mouseY);
        }

        //Mouse control works only in credits menu
        if(gp.getState() != GAME && gp.getState() != MENU && gp.getState() != GAMEWON && gp.getState() != GAMEOVER && gp.getState() != PAUSED && gp.getState() != HELP_MENU && gp.getState() != ERROR_MENU){
            HelpMenuControls(mouseX, mouseY);
        }

        //Mouse control works only in pause menu
        if(gp.getState() != GAME && gp.getState() != MENU && gp.getState() != GAMEWON && gp.getState() != GAMEOVER && gp.getState() != HELP_MENU && gp.getState() != CREDITS_MENU && gp.getState() != ERROR_MENU){
            PauseMenuControls(mouseX, mouseY);
        }

        //Mouse control for game won menu
        if(gp.getState() != GAME && gp.getState() != MENU && gp.getState() != GAMEOVER && gp.getState() != PAUSED && gp.getState() != HELP_MENU && gp.getState() != CREDITS_MENU && gp.getState() != ERROR_MENU){
            GameWonMenuControls(mouseX, mouseY);
        }

        //Mouse control for game over menu
        if(gp.getState() != GAME && gp.getState() != MENU && gp.getState() != GAMEWON && gp.getState() != PAUSED && gp.getState() != HELP_MENU && gp.getState() != CREDITS_MENU && gp.getState() != ERROR_MENU){
            GameOverMenuControls(mouseX, mouseY);
        }

        //Mouse controls for error menu
        if(gp.getState() != GAME && gp.getState() != MENU && gp.getState() != GAMEWON && gp.getState() != PAUSED && gp.getState() != HELP_MENU && gp.getState() != CREDITS_MENU && gp.getState() != GAMEOVER){
            ErrorMenuControls(mouseX, mouseY);
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
                gp.setGameLevel(1);
                gp.levelCheck();
                System.out.println("Resetting number of keys");
                gp.setState(GAME);
            }
        }

        //Continue button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 - 100 && mouseY <= gp.screenHeight/2 - 30){
                if (gp.loadGame.isEmpty("saveFile.txt")) {
                    gp.setState(ERROR_MENU);
                }
                else {
                    System.out.println("Continuing game");
                    gp.setState(GAME);
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
                gp.setState(HELP_MENU);
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
                gp.setState(MENU);
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
                gp.setState(MENU);
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
                gp.setState(GAME);
            }
        }
        //return to main menu controls
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(103)){
            if(mouseY >= gp.screenHeight/2 - 30 && mouseY <= gp.screenHeight/2 + 30){
                gp.saveGame.saveData("saveFile.txt");
                System.out.println("returning to Main menu");
                gp.setState(MENU);
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
                gp.increaseLevel();
                System.out.println("(Update) Level: "+ gp.getGameLevel());
                if(gp.getGameLevel() > 3){
                    gp.loadGame.resetFile("saveFile.txt");
                    gp.setGameLevel(1);;
                    gp.setState(CREDITS_MENU);
                }else{
                    gp.levelCheck();
                }
                gp.music.playMusic(8);
            }
        }

        //return to main menu button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 - 30 && mouseY <= gp.screenHeight/2 + 40){
                gp.increaseLevel();
                System.out.println("(Update) Level: "+ gp.getGameLevel());
                gp.levelCheck();
                System.out.println(mouseX + " "+ mouseY + ": returning to Main menu");
                gp.setState(MENU);
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
                gp.setState(GAME);
                gp.music.playMusic(8);
            }
        }

        //return to main menu button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 - 30 && mouseY <= gp.screenHeight/2 + 40){
                System.out.println("returning to Main menu");
                gp.levelCheck();
                gp.saveGame.saveData("saveFile.txt");
                gp.setState(MENU);
                gp.music.playMusic(0);
            }   
        }
    }

    /**
     * <p>ErrorMenuControls will only work on the main menu screen if the state of the game is ERROR_MENU</p>
     * @param mouseX integer location for mouse X position
     * @param mouseY integer location for mouse Y position
     */
    public void ErrorMenuControls(int mouseX, int mouseY){
        //Return to menu button
        if(mouseX >= gp.screenWidth/2-103 && mouseX <= gp.screenWidth/2+(106)){
            if(mouseY >= gp.screenHeight/2 + 350 && mouseY <= gp.screenHeight/2 + 398){
                //Close the screen
                System.out.println("return to main menu");
                gp.setState(MENU);
            }
        }
    }
}