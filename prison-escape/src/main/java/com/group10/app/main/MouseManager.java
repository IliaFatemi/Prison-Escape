package com.group10.app.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import com.group10.app.SavedData.SaveGame;
import com.group10.app.entity.Inmate;
import com.group10.app.main.GamePanel.STATE;

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
        /**
        g2.drawImage(newGame,  gp.screenWidth/2-103, gp.screenHeight/2 - 300, 206, 70, null);
        g2.drawImage(continue_, gp.screenWidth/2-103, gp.screenHeight/2 - 200, 206, 70, null);
        g2.drawImage(quitGame, gp.screenWidth/2-103, gp.screenHeight/2 - 100, 206, 70, null); */

        //new game button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2-300 && mouseY <= gb.screenHeight/2-230){
                GamePanel.state = GamePanel.STATE.GAME;
                gb.inmate.resetInmate();
            }
        }

        //Continue button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 200 && mouseY <= gb.screenHeight/2 - 130){
                GamePanel.state = STATE.GAME;
                gb.inmate.setPos(gb.load.loadPlayerX(), gb.load.loadPlayerY());
                gb.inmate.setTimer(gb.load.loadTimer());
                gb.inmate.setScore(gb.load.loadScore());
                gb.inmate.setNumKeys(gb.load.loadNumKeys());
            }
        }

        //Exit game button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 100 && mouseY <= gb.screenHeight/2 - 30){
                //Close the screen
                GameLauncher.window.dispatchEvent(new WindowEvent(GameLauncher.window, WindowEvent.WINDOW_CLOSING));
            }
        }
    }

    public void PauseMenuControls(int mouseX, int mouseY){
        //Resume button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(103)){
            if(mouseY >= gb.screenHeight/2 - 130 && mouseY <= gb.screenHeight/2-60){
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
                GamePanel.state = STATE.MENU;
                gb.saveGame.save(1,(int) gb.inmate.getX(),(int) gb.inmate.getY(), gb.inmate.getTimer(), gb.inmate.getScore(), gb.inmate.getNumKeys(), 0, 0);       
            }
        }
    }

    public void GameWonMenuControls(int mouseX, int mouseY){
        
        //next level button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2-130 && mouseY <= gb.screenHeight/2-60){
                //System.out.println(mouseX + " "+ mouseY);
            }
        }

        //return to main menu button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 30 && mouseY <= gb.screenHeight/2 + 40){
                gb.saveGame.save(1,(int) gb.inmate.getX(),(int) gb.inmate.getY(), gb.inmate.getTimer(), gb.inmate.getScore(), gb.inmate.getNumKeys(), 0, 0);   
                GamePanel.state = STATE.MENU;
            }
        }
    }


}