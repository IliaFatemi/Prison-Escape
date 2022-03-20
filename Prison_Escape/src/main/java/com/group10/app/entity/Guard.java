package com.group10.app.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import com.group10.app.main.GamePanel;


public class Guard extends Entity{
    GamePanel gp;
    int standCounter = 0;

    public Guard(GamePanel gp, int setX, int setY){
        super(gp);
        this.gp = gp;
        getGuardImage();
        setGuardValues(setX, setY);
        solidArea = new Rectangle(0, 0, 48, 48);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    //Registering the images
    public void getGuardImage(){
        up1 = setup("/prisonGuard/WalkUp1", gp.cellSize, gp.cellSize);
        up2 = setup("/prisonGuard/WalkUp2", gp.cellSize, gp.cellSize);
        up3 = setup("/prisonGuard/WalkUp3", gp.cellSize, gp.cellSize);
        up4 = setup("/prisonGuard/WalkUp4", gp.cellSize, gp.cellSize);
        up5 = setup("/prisonGuard/WalkUp5", gp.cellSize, gp.cellSize);
        down1 = setup("/prisonGuard/WalkDown1", gp.cellSize, gp.cellSize);
        down2 = setup("/prisonGuard/WalkDown2", gp.cellSize, gp.cellSize);
        down3 = setup("/prisonGuard/WalkDown3", gp.cellSize, gp.cellSize);
        down4 = setup("/prisonGuard/WalkDown4", gp.cellSize, gp.cellSize);
        down5 = setup("/prisonGuard/WalkDown5", gp.cellSize, gp.cellSize);
        left1 = setup("/prisonGuard/WalkLeft1", gp.cellSize, gp.cellSize);
        left2 = setup("/prisonGuard/WalkLeft2", gp.cellSize, gp.cellSize);
        left3 = setup("/prisonGuard/WalkLeft3", gp.cellSize, gp.cellSize);
        left4 = setup("/prisonGuard/WalkLeft4", gp.cellSize, gp.cellSize);
        left5 = setup("/prisonGuard/WalkLeft5", gp.cellSize, gp.cellSize);
        right1 = setup("/prisonGuard/WalkRight1", gp.cellSize, gp.cellSize);
        right2 = setup("/prisonGuard/WalkRight2", gp.cellSize, gp.cellSize);
        right3 = setup("/prisonGuard/WalkRight3", gp.cellSize, gp.cellSize);
        right4 = setup("/prisonGuard/WalkRight4", gp.cellSize, gp.cellSize);
        right5 = setup("/prisonGuard/WalkRight5", gp.cellSize, gp.cellSize);
    }

    //Get Guards x position
    public double getX(){
        return x;
    }
    
    //Get Guards Y position
    public double getY(){
        return y;
    }

    //Get guard direction
    public String getDirection(){
        return direction;
    }

    //set the value for guard
    public void setGuardValues(int setX, int setY){
        x = setX;
        y = setY;
        speed = 1;
        direction = "vishaal";
    }

    public void update(int xcor, int ycor) {
        if (xcor < x) {
            direction = "left";
        }
        else if (xcor > x) {
            direction = "right";
        }

        if (xcor - x < (gp.cellSize/2) && x - xcor < (gp.cellSize/2)){
            if (ycor < y) {
                direction = "up";
            }
            else if (ycor > y) {
                direction = "down";
            }
        }


        collision = false;
        gp.collisionCheck.wallCheck(this);
        if (!collision) {
            switch (direction) {
                case "up":
                    y -= speed;
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
            }else if (spriteNum == 4) {
                spriteNum = 5;
            }else if (spriteNum == 5) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        } else {
            standCounter++;

            if (standCounter > 10) {
                spriteNum = 2;
                standCounter = 0;
            }
        }
    }

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
