package com.group10.app.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import com.group10.app.main.GamePanel.STATE;

public class MouseManager implements MouseListener{

    GamePanel gb;
    

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
        /* g2.drawImage(newGame,  gp.screenWidth/2-100, 100, 206, 70, null);
        g2.drawImage(continue_, gp.screenWidth/2-100, 200, 206, 70, null);
        g2.drawImage(quitGame, gp.screenWidth/2-100, 300, 206, 70, null);*/

        //Mouse control works only in main menu
        if(GamePanel.state != STATE.GAME && GamePanel.state != STATE.PAUSED){
            MainMenuControls(mouseX, mouseY);
        }

        //Mouse control works only in pause menu
        if(GamePanel.state != STATE.GAME && GamePanel.state != STATE.MENU){
            PauseMenuControls(mouseX, mouseY);
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
                System.out.println(mouseY);
            }
        }

        //Continue button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 200 && mouseY <= gb.screenHeight/2 - 130){
                System.out.println(mouseY);
            }
        }

        //Exit game button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(106)){
            if(mouseY >= gb.screenHeight/2 - 100 && mouseY <= gb.screenHeight/2 - 30){
                //Close the screen
                GameLauncher.window.dispatchEvent(new WindowEvent(GameLauncher.window, WindowEvent.WINDOW_CLOSING));
                System.out.println(mouseY);
            }
        }
    }

    public void PauseMenuControls(int mouseX, int mouseY){
        //Resume button
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(103)){
            if(mouseY >= gb.screenHeight/2 - 130 && mouseY <= gb.screenHeight/2-60){
                GamePanel.state = GamePanel.STATE.GAME;
                System.out.println(mouseY);
            }
        }
        
        //Save game controls
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(103)){
            if(mouseY >= gb.screenHeight/2 -30 && mouseY <= gb.screenHeight/2 + 40){
                System.out.println(mouseY);
            }
        }

        //return to main menu controls
        if(mouseX >= gb.screenWidth/2-103 && mouseX <= gb.screenWidth/2+(103)){
            if(mouseY >= gb.screenHeight/2 + 70 && mouseY <= gb.screenHeight/2 + 140){
                GamePanel.state = STATE.MENU;
                System.out.println(mouseY);
            }
        }
    }
}