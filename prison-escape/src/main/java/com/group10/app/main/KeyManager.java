package com.group10.app.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    public boolean pressedUp, pressedDown, pressedRight, pressedLeft;

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            pressedUp = true;
        }

        if(code == KeyEvent.VK_S){
            pressedDown = true;
        }

        if(code == KeyEvent.VK_A){
            pressedLeft = true;
        }

        if(code == KeyEvent.VK_D){
            pressedRight = true;
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
