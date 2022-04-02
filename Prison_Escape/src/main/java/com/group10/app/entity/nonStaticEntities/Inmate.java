package com.group10.app.entity.nonStaticEntities;

import com.group10.app.main.GamePanel;
import com.group10.app.main.KeyManager;

import java.awt.*;
import java.util.Objects;

/**
 * Inmate class responsible for the Inmates game implementation
 * inherits from the super class Entity
 *
 * <p>
 *     This class implements the guard functionality for the game
 *     manages the inmates movements (with respect to the user),
 *     interactions, and drawing the players sprites
 * </p>
 */
public class Inmate extends MovingActor {
    GamePanel gp;
    KeyManager keyH;
    private int hasKey = 0;
    private int score = 0;
    private double time = 100;
    private int standCounter = 0;

    /**
     * Constructor of the Inmate class
     *
     * <p>
     *     In charge of initializing the position of the Inmate with respect to the
     *     game panel gp
     * </p>
     *
     * @param gp main game panel
     * @param keyH used for managing user key input
     */
    public Inmate(GamePanel gp, KeyManager keyH){
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        setName("Inmate");
        setSpeed(2);

        solidAreaDefaultX = getSolidArea().x;
        solidAreaDefaultY = getSolidArea().y;

        getInmateImage();
    }

    /**
     * getInmateImage method is in charge of registering the image directories for the Inmate enemy
     */
    public void getInmateImage(){
        up1 = setup("/inmate/walkUp1");
        up2 = setup("/inmate/walkUp2");
        up3 = setup("/inmate/walkUp3");
        down1 = setup("/inmate/walkDown1");
        down2 = setup("/inmate/walkDown2");
        down3 = setup("/inmate/walkDown3");
        left1 = setup("/inmate/walkLeft1");
        left2 = setup("/inmate/walkLeft2");
        left3 = setup("/inmate/walkLeft3");
        right1 = setup("/inmate/walkRight1");
        right2 = setup("/inmate/walkRight2");
        right3 = setup("/inmate/walkRight3");
    }

    /**
     * update method for the player position/movement with respect to the users input
     * <p>
     *     The update method in the inmate class is responsible for the position/
     *     movement of the inmate with respect to the users input on the WASD commands.
     *     This method manages the collisions, the movement speed for the inmate, and the
     *     movement sprites for the inmate.
     * </p>
     *
     */
    public void update(){
        if(keyH.pressedUp|| keyH.pressedDown || keyH.pressedLeft || keyH.pressedRight) {
            if (keyH.pressedUp) {
                setDirection("up");
            } else if (keyH.pressedDown) {
                setDirection("down");
            } else if (keyH.pressedLeft) {
                setDirection("left");
            } else {
                setDirection("right");
            }
            collision = false;
            int objectIndex = gp.collisionCheck.checkObject(this);
            pickUpObject(objectIndex);
            collisionUpdate();
            spriteUpdate();
        }
        else {
            standCounter++;
            if (standCounter > 10){
                setSpriteNum(2);
                standCounter = 0;
            }
        }
    }

    /**
     * Get number of keys collected
     * @return hasKey which is the number of keys collected
     */
    public int getNumKeys(){return hasKey;}

    /**
     * Get the score collected
     * @return score which is the score maintained throughout the game/round
     */
    public int getScore(){return score;}

    /**
     * getting the current timer
     * @return time converted from double to int
     */
    public double getTimer(){return time;}

    /**
     * Set the timer
     * @param newTime
     */
    public void setTimer(double newTime){time = newTime;}

    /**
     * set the score
     * @param newScore
     */
    public void setScore(int newScore){score = newScore;}

    /**
     * set player position
     * @param x
     * @param y
     */
    public void setPos(int x, int y){setX(x); setY(y);}

    /**
     * set key amount
     * @param newNumKeys
     */
    public void setNumKeys(int newNumKeys){hasKey = newNumKeys;}

    public void resetKeys() {hasKey = 0;}

    public void resetScore() {score = 0;}

    /**
     * reset the time, score, and hasKey(Key number)
     */
    public void resetInmate(){
        time = 100;
        score = 0;
        hasKey = 0;
    }

    /**
     * pickUpObject is responsible for the managing the objects inmate
     * picks up
     *
     * <p>
     *    the method is responsible for handling the objects the inmate
     *    picks up. The int i corresponds to the index of an objects in
     *    the object array.
     * </p>
     * @param i index of the corresponding object in the obj array
     */
    public void pickUpObject (int i) {

        if (i != 999){

            String objectName = gp.obj[i].getName();
            String text = "Got a " + objectName + "!";

            if (!Objects.equals(objectName, "Door")){
                gp.ui.addMessage(text);
            }

            switch (objectName) {
                case "Key":
                    gp.music.playSE(1);
                    score += 50;
                    hasKey++;
                    gp.obj[i] = null;
                break;
                case "Timer":
                    gp.music.playSE(2);
                    time += 20;
                    gp.obj[i] = null;
                break;
                case "Chicken":
                    gp.music.playSE(3);
                    score += 100;
                    gp.obj[i] = null;
                break;
                case "Trap":
                    gp.music.playSE(4);
                    score -= 50;
                    gp.obj[i] = null;
                break;
            }
        }
    }


    /**
     * <p>if the player is within the area of the gate the mehtod will return true, otherwise false</p>
     * @return boolean
     */
    public boolean reachedGate(){
        return getX() >= 1344 && getX() <= 1350 && getY() >= 292 && getY() <= 544;
    }

    /**
     * <p>Based on the level of the player, the gotAllKeys will check if the player collect the right amount of keys for a specific level</p>
     * @return boolean
     */
    public boolean gotAllKeys(){
        if(GamePanel.GAME_LEVEL == 1 && hasKey >= 3){return true;}
        else if(GamePanel.GAME_LEVEL == 2 && hasKey >= 4){return true;}
        else if(GamePanel.GAME_LEVEL == 3 && hasKey >= 5){return true;}
        return false;
    }

    /**
     * <p>If the time has reached zero, the method will return true.</p>
     * @return boolean
     */
    public boolean isTimeOver(){
        if(time <= 0){
            System.out.println("Time reached zero");
            return true;
        }
        return false;
    }

    /**
     * isScoreNegative will be true if the score has reached a negative number
     * @return boolean
     */
    public boolean isScoreNegative(){
        if(score < 0){
            System.out.println("Score is negative");
            return true;
        }
        return false;
    }
}