package com.group10.app.entity.nonStaticEntities;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MovingActor extends Entity {
    private int speed;
    private String direction;
    private GamePanel gp;
    private int spriteNum;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDirection() {
        return direction;
    }

    public GamePanel getGp() {
        return gp;
    }

    public int getSpriteNum() {
        return spriteNum;
    }



    /**
     * The constructor for the Entity class
     *
     * @param gp used for building entities on the game panel
     */
    public MovingActor(GamePanel gp) {
        super(gp);
    }

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
                    image = left3;
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

}
