package com.group10.app.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.group10.app.main.GamePanel.STATE;

public class KeyManager implements KeyListener {
    GamePanel gp;

    public boolean pressedUp, pressedDown, pressedRight, pressedLeft, pressedEscape;
    int keyCount = 0;

    //Debug
    boolean showDebugText = false;

    public KeyManager (GamePanel gp) {this.gp = gp;}

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){pressedUp     = true;}
        if(code == KeyEvent.VK_S){pressedDown   = true;}
        if(code == KeyEvent.VK_A){pressedLeft   = true;}
        if(code == KeyEvent.VK_D){pressedRight  = true;}

        if(code == KeyEvent.VK_ESCAPE){
            if(GamePanel.state != STATE.MENU){

                pressedEscape = true;
                GamePanel.state = STATE.PAUSED;
                keyCount++;
                if (keyCount%2 == 0){
                    pressedEscape = false;
                    GamePanel.state = STATE.GAME;
                }
            }
        }

        //Debug
        if (code == KeyEvent.VK_T){
            showDebugText = !showDebugText;
        }
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            pressedUp = false;
        }

        if(code == KeyEvent.VK_S){
            pressedDown = false;
        }

        if(code == KeyEvent.VK_A){
            pressedLeft = false;
        }

        if(code == KeyEvent.VK_D){
            pressedRight = false;
        }
    }
}
