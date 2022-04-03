package com.group10.app.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import com.group10.app.main.GamePanel.STATE;

import static com.group10.app.main.GameStates.*;

/**
 * KeyManager is responsible for managing the user keyboard inputs
 * KeyManager implements from the KeyListener interface
 */
public class KeyManager implements KeyListener {
    GamePanel gp;

    public boolean pressedUp, pressedDown, pressedRight, pressedLeft, pressedEscape;
    int keyCount = 0;

    /**
     * KeyManager is a constructor responsible to initializing the game panel for this class
     */
    public KeyManager() {this.gp = gp;}

    public void keyTyped(KeyEvent e) {}

    /**
     * keyPressed is a method that tracks what key is pressed by the user
     * @param e the event of a key being pressed
     */
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){pressedUp     = true;}
        if(code == KeyEvent.VK_S){pressedDown   = true;}
        if(code == KeyEvent.VK_A){pressedLeft   = true;}
        if(code == KeyEvent.VK_D){pressedRight  = true;}

        if(code == KeyEvent.VK_ESCAPE){
            if(GamePanel.state != MENU){

                pressedEscape = true;
                GamePanel.state = PAUSED;
                keyCount++;
                if (keyCount%2 == 0){
                    pressedEscape = false;
                    GamePanel.state = GAME;
                }
            }
        }
    }

    /**
     * keyReleased is a method that tracks what key was just released by the user
     * @param e the event of a key being released
     */
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
