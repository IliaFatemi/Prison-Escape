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

        if(GamePanel.state != STATE.GAME){

            //Play button
            if(mouseX >= gb.screenWidth/2-100 && mouseX <= gb.screenWidth/2+(106)){
                if(mouseY >= 100 && mouseY <= 170){
                    GamePanel.state = GamePanel.STATE.GAME;
                    System.out.println(mouseY);
                }
            }
    
            //Continue button
            if(mouseX >= gb.screenWidth/2-100 && mouseX <= gb.screenWidth/2+(106)){
                if(mouseY >= 200 && mouseY <= 270){
                    System.out.println(mouseY);
                }
            }
    
            //Exit game button
            if(mouseX >= gb.screenWidth/2-100 && mouseX <= gb.screenWidth/2+(106)){
                if(mouseY >= 300 && mouseY <= 370){
                    //Close the screen
                    GameLauncher.window.dispatchEvent(new WindowEvent(GameLauncher.window, WindowEvent.WINDOW_CLOSING));
                    System.out.println(mouseY);
                    
                }
            }
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
    
}
