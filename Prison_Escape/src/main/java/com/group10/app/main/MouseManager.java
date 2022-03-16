package com.group10.app.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import com.group10.app.SavedData.SaveGame;
import com.group10.app.main.GamePanel.STATE;
import com.group10.app.App;

public class MouseManager implements MouseListener{

    GamePanel gb;
    SaveGame saveGame;

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

    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println(mouseX + ", " + mouseY);

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

    //Main menu control
    public void MainMenuControls(int mouseX, int mouseY){

        //new game button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2-300 && mouseY <= gb.screenHeight/2-230){
                System.out.println(mouseX + " "+ mouseY + ": Starting new game");
                GamePanel.state = GamePanel.STATE.GAME;
                gb.inmate.resetInmate();
                gb.inmate.resetInmate();
                gb.inmate.resetKeys();
            }
        }

        //Continue button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 200 && mouseY <= gb.screenHeight/2 - 130){
                System.out.println(mouseX + " "+ mouseY + ": Continuing game");
                gb.load.loadData();
                gb.inmate.setPos(gb.load.loadPlayerX(), gb.load.loadPlayerY());
                gb.inmate.setTimer(gb.load.loadTimer());
                gb.inmate.setScore(gb.load.loadScore());
                gb.inmate.setNumKeys(gb.load.loadNumKeys());
                GamePanel.state = STATE.GAME;
            }
        }

        //Exit game button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 100 && mouseY <= gb.screenHeight/2 - 30){
                //Close the screen
                System.out.println(mouseX + " "+ mouseY + ": Exiting game");
                App.window.dispatchEvent(new WindowEvent(App.window, WindowEvent.WINDOW_CLOSING));
            }
        }
    }

    public void PauseMenuControls(int mouseX, int mouseY){
        //Resume button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(103)){
            if(mouseY >= gb.screenHeight/2 - 130 && mouseY <= gb.screenHeight/2-60){
                System.out.println(mouseX + " "+ mouseY + ": resuming game");
                GamePanel.state = GamePanel.STATE.GAME;
            }
        }
        
        //Save game controls
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(103)){
            if(mouseY >= gb.screenHeight/2 -30 && mouseY <= gb.screenHeight/2 + 40){
            }
        }

        //return to main menu controls
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(103)){
            if(mouseY >= gb.screenHeight/2 + 70 && mouseY <= gb.screenHeight/2 + 140){
                gb.saveGame.save(1,(int) gb.inmate.getX(),(int) gb.inmate.getY(), gb.inmate.getTimer(), gb.inmate.getScore(), gb.inmate.getNumKeys(), 0, 0);       
                System.out.println(mouseX + " "+ mouseY + ": returning to Main menu");
                GamePanel.state = STATE.MENU;
            }
        }
    }

    public void GameWonMenuControls(int mouseX, int mouseY){
        
        //next level button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2-130 && mouseY <= gb.screenHeight/2-60){
                System.out.println(mouseX + " "+ mouseY + ": Next level");
            }
        }

        //return to main menu button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 30 && mouseY <= gb.screenHeight/2 + 40){
                gb.saveGame.save(1,(int) gb.inmate.getX(),(int) gb.inmate.getY(), gb.inmate.getTimer(), gb.inmate.getScore(), gb.inmate.getNumKeys(), 0, 0);   
                System.out.println(mouseX + " "+ mouseY + ": returning to Main menu");
                GamePanel.state = STATE.MENU;
            }
        }
    }

    public void GameOverMenuControls(int mouseX, int mouseY){
        //next level button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2-130 && mouseY <= gb.screenHeight/2-60){
                System.out.println(mouseX + " "+ mouseY + ": Retry Level");
            }
        }

        //return to main menu button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 30 && mouseY <= gb.screenHeight/2 + 40){
                System.out.println(mouseX + " "+ mouseY + ": returning to Main menu");
                gb.inmate.resetInmate();
                GamePanel.state = STATE.MENU;
            }   
        }
    }


}