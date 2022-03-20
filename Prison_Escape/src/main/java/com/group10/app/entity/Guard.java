package com.group10.app.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import com.group10.app.main.GamePanel;


public class Guard extends Entity{
    GamePanel gp;

    public Guard(GamePanel gp, int setX, int setY){
        super(gp);
        this.gp = gp;
        getGuardImage();
        setGuardValues(setX, setY);
    }

    //Registering the images
    public void getGuardImage(){
        up1 = setup("/prisonGuard/WalkUp1", gp.cellSize, gp.cellSize);
        up2 = setup("/prisonGuard/WalkUp2", gp.cellSize, gp.cellSize);
        up3 = setup("/prisonGuard/WalkUp3", gp.cellSize, gp.cellSize);
        down1 = setup("/prisonGuard/WalkDown1", gp.cellSize, gp.cellSize);
        down2 = setup("/prisonGuard/WalkDown2", gp.cellSize, gp.cellSize);
        down3 = setup("/prisonGuard/WalkDown3", gp.cellSize, gp.cellSize);
        left1 = setup("/prisonGuard/WalkLeft1", gp.cellSize, gp.cellSize);
        left2 = setup("/prisonGuard/WalkLeft2", gp.cellSize, gp.cellSize);
        left3 = setup("/prisonGuard/WalkLeft3", gp.cellSize, gp.cellSize);
        right1 = setup("/prisonGuard/WalkRight1", gp.cellSize, gp.cellSize);
        right2 = setup("/prisonGuard/WalkRight2", gp.cellSize, gp.cellSize);
        right3 = setup("/prisonGuard/WalkRight3", gp.cellSize, gp.cellSize);
    }

    //Get Guards x position
    public double getX(){
        return x;
    }
    
    //Get Guards Y position
    public double getY(){
        return y;
    }

    //set the value for guard
    public void setGuardValues(int setX, int setY){
        x = setX;
        y = setY;
        speed = 4;
        direction = "down";
    }


    public void draw(Graphics2D g2){
        BufferedImage image = down1;
        g2.drawImage(image, x , y, gp.cellSize, gp.cellSize, null);
    }
}
