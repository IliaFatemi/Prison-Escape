package com.group10.app.entity.nonStaticEntities;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

/**
 * The guard class in charge of the Guard implementation
 *
 * <p>
 *     This class implements the guard functionality for the game
 *     manages the guards movements, interactions, drawing the sprites and
 *     changing them with the guards movements.
 * </p>
 *
 */
public class Guard extends MovingActor {
    GamePanel gp;
    boolean moving = false;
    int pixelCounter = 0;

    /**
     * The constructor for the Guard class
     *
     * <p>
     *     Default setting include: direction = down
     *                              speed = 1
     *                              SolidArea(8, 16, 32, 32)
     *                              Get guard's images
     * </p>
     *
     * @param gp main game panel
     */
    public Guard(GamePanel gp){
        super(gp);
        this.gp = gp;

        setDirection("down");
        setSpeed(1);

        getGuardImage();
        solidArea = new Rectangle(8, 16, 32, 32);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    /**
     * getGuardImage method is in charge of registering the image directories for the guard enemy
     */
    public void getGuardImage(){
        up1 = setup("/prisonGuard/WalkUp1");
        up2 = setup("/prisonGuard/WalkUp2");
        up3 = setup("/prisonGuard/WalkUp3");
        down1 = setup("/prisonGuard/WalkDown1");
        down2 = setup("/prisonGuard/WalkDown2");
        down3 = setup("/prisonGuard/WalkDown3");
        left1 = setup("/prisonGuard/WalkLeft1");
        left2 = setup("/prisonGuard/WalkLeft2");
        left3 = setup("/prisonGuard/WalkLeft3");
        right1 = setup("/prisonGuard/WalkRight1");
        right2 = setup("/prisonGuard/WalkRight2");
        right3 = setup("/prisonGuard/WalkRight3");
    }

    /**
     * Get Guards x position
     * @return x of type int
     */
    public double getX(){
        return x;
    }

    /**
     * Get Guards y position
     * @return y of type int
     */
    public double getY(){
        return y;
    }

    /**
     * set the value for guard
     *
     * @param setX x value to set guards x position
     * @param setY y value to set guards y position
     */
    public void setGuardValues(int setX, int setY){
        x = setX;
        y = setY;
        setSpeed(1);
        setDirection("default");
    }

    /**
     * update method is in charge of updating the position of the guard
     * <p>
     *     The update method in the Guard class is responsible for the movement
     *     of the guard when the inmate comes within range of the guard. Once the inmate
     *     is in range the guard will follow the player
     * </p>
     */
    public void update() {

        if (!moving) {

            if (gp.inmate.x < x) {
                setDirection("left");
            }
            else if (gp.inmate.x > x) {
                setDirection("right");
            }

            if (gp.inmate.x - x < (gp.cellSize/2) && x - gp.inmate.x < (gp.cellSize/2)){
                if (gp.inmate.y < y) {
                    setDirection("up");
                }
                else if (gp.inmate.y > y) {
                    setDirection("down");
                }
            }

            moving = true;
        }

        collision = false;
        gp.collisionCheck.wallCheck(this);

        int guardSpeed = getSpeed();
        if (!collision) {
            switch (getDirection()) {
                case "up":
                    y -= guardSpeed;
                    break;
                case "down":
                    y += guardSpeed;
                    break;
                case "left":
                    x -= guardSpeed;
                    break;
                case "right":
                    x += guardSpeed;
                    break;
            }
        }
        collisionUpdate();
        spriteUpdate();

        pixelCounter += guardSpeed;

        if (pixelCounter == 48) {
            moving = false;
            pixelCounter = 0;
        }
    }

}