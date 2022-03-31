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
public class Guard extends MovingEntities {
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

        direction = "down";
        speed = 1;

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
        up4 = setup("/prisonGuard/WalkUp4");
        up5 = setup("/prisonGuard/WalkUp5");
        down1 = setup("/prisonGuard/WalkDown1");
        down2 = setup("/prisonGuard/WalkDown2");
        down3 = setup("/prisonGuard/WalkDown3");
        down4 = setup("/prisonGuard/WalkDown4");
        down5 = setup("/prisonGuard/WalkDown5");
        left1 = setup("/prisonGuard/WalkLeft1");
        left2 = setup("/prisonGuard/WalkLeft2");
        left3 = setup("/prisonGuard/WalkLeft3");
        left4 = setup("/prisonGuard/WalkLeft4");
        left5 = setup("/prisonGuard/WalkLeft5");
        right1 = setup("/prisonGuard/WalkRight1");
        right2 = setup("/prisonGuard/WalkRight2");
        right3 = setup("/prisonGuard/WalkRight3");
        right4 = setup("/prisonGuard/WalkRight4");
        right5 = setup("/prisonGuard/WalkRight5");
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
     * Gets guard direction
     * @return direction of type String
     */
    public String getDirection(){
        return direction;
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
        int i = getSpeed();
        if (!collision) {
            switch (getDirection()) {
                case "up":
                    y -= i;
                    break;
                case "down":
                    y += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
                case "right":
                    x += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 20) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 5;
            } else if (spriteNum == 5) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        pixelCounter += speed;

        if (pixelCounter == 48) {
            moving = false;
            pixelCounter = 0;
        }
    }

    /**
     * in charge of drawing the guard and changing the guards current sprite
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
                if (spriteNum == 4) {
                    image = up4;
                }
                if (spriteNum == 5) {
                    image = up5;
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
                if (spriteNum == 4) {
                    image = down4;
                }
                if (spriteNum == 5) {
                    image = down5;
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
                if (spriteNum == 4) {
                    image = left4;
                }
                if (spriteNum == 5) {
                    image = left5;
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
                if (spriteNum == 4) {
                    image = right4;
                }
                if (spriteNum == 5) {
                    image = right5;
                }
                break;
            default: break;

        }
        g2.drawImage(image, x , y, gp.cellSize, gp.cellSize, null);
    }
}
