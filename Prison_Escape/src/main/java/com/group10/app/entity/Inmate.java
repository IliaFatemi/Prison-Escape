package com.group10.app.entity;

import com.group10.app.main.GamePanel;
import com.group10.app.main.KeyManager;

import java.awt.*;
import java.awt.image.BufferedImage;
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
public class Inmate extends Entity{
    GamePanel gp;
    KeyManager keyH;
    public int hasKey = 0;
    public int score = 0;
    public double time = 100;
    int standCounter = 0;

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

        solidArea = new Rectangle(8, 16, 32, 32);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

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
                direction = "up";
            } else if (keyH.pressedDown) {
                direction = "down";
            } else if (keyH.pressedLeft) {
                direction = "left";
            } else {
                direction = "right";
            }
            int objectIndex = gp.collisionCheck.checkObject(this);
            pickUpObject(objectIndex);
            collisionUpdate();
            spriteUpdate();
        }
        else {
            standCounter++;
            if (standCounter > 10){
                spriteNum = 2;
                standCounter = 0;
            }
        }
    }

    /**
     * Updating the sprite on the player
     * <p>
     * The spriteUpdate method is responsible for updating the sprite png while 
     * the player is moving around. 
     * </p>
     */
    public void spriteUpdate(){
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    /**
     * 
     */
    public void collisionUpdate(){
        collision = false;
        gp.collisionCheck.wallCheck(this);
        if(!collision){
            switch (direction) {
                case "up":      y -= speed; break;
                case "down":    y += speed; break;
                case "left":    x -= speed; break;
                case "right":   x += speed; break;
            }
        }
    }

    /**
     * Get the x position of the player
     * @return x the x position of the inmate
     */
    public double getX(){return x;}

    /**
     * Get the y position of the player
     * @return y the y position of the inmate
     */
    public double getY(){return y;}

    /**
     * Get player speed
     * @return speed
     */
    public double getSpeed(){return speed;}

    /**
     * Get player direction
     * @return String
     */
    public String getDirection(){return direction;}

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
    public int getTimer(){return (int)time;}

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
     * @param posX
     * @param posY
     */
    public void setPos(int posX, int posY){x = posX; y = posY;}

    /**
     * set player speed
     * @param newSpeed
     */
    public void setSpeed(int newSpeed){speed = newSpeed;}

    /**
     * set players direction
     * @param newDir
     */
    public void setDirection(String newDir){direction = newDir;}

    /**
     * set key amount
     * @param newNumKeys
     */
    public void setNumKeys(int newNumKeys){hasKey = newNumKeys;}

    /**
     * reset the keys
     */
    public void resetKeys(){hasKey = 0;}

    /**
     * reset the score
     */
    public void resetScore(){score = 0;}

    /**
     * reset the speed, time, score, and hasKey(Key number)
     */
    public void resetInmate(){
        speed = 2;
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

            String objectName = gp.obj[i].name;
            String text = "Got a " + gp.obj[i].name + "!";

            if (!Objects.equals(gp.obj[i].name, "Door")){
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
     * in charge of drawing the inmate and changing the inmates current sprite
     * @param g2 used for drawing the 2D sprites
     */
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
            break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
            break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
            break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
            break;
            default: break;
            
        }
        g2.drawImage(image, x , y, gp.cellSize, gp.cellSize, null);
    }

    /**
     * <p>if the player is within the area of the gate the mehtod will return true, otherwise false</p>
     * @return boolean
     */
    public boolean reachedGate(){
        return x >= 1344 && x <= 1350 && y >= 292 && y <= 544;
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