package com.group10.app.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import com.group10.app.main.GamePanel;


public class Gaurd extends Entity{
    GamePanel gp;

    public Gaurd(GamePanel gp, int setx, int sety){
        super(gp);
        this.gp = gp;
        getGaurdImage();
        setgaurdValues(setx, sety);
    }

    //Registering the images
    public void getGaurdImage(){
        up1 = setup("/prisonGaurd/WalkUp1", gp.cellSize, gp.cellSize);
        up2 = setup("/prisonGaurd/WalkUp2", gp.cellSize, gp.cellSize);
        up3 = setup("/prisonGaurd/WalkUp3", gp.cellSize, gp.cellSize);
        down1 = setup("/prisonGaurd/WalkDown1", gp.cellSize, gp.cellSize);
        down2 = setup("/prisonGaurd/WalkDown2", gp.cellSize, gp.cellSize);
        down3 = setup("/prisonGaurd/WalkDown3", gp.cellSize, gp.cellSize);
        left1 = setup("/prisonGaurd/WalkLeft1", gp.cellSize, gp.cellSize);
        left2 = setup("/prisonGaurd/WalkLeft2", gp.cellSize, gp.cellSize);
        left3 = setup("/prisonGaurd/WalkLeft3", gp.cellSize, gp.cellSize);
        right1 = setup("/prisonGaurd/WalkRight1", gp.cellSize, gp.cellSize);
        right2 = setup("/prisonGaurd/WalkRight2", gp.cellSize, gp.cellSize);
        right3 = setup("/prisonGaurd/WalkRight3", gp.cellSize, gp.cellSize);
    }

    //Get Gaurds x position
    public double getX(){
        return x;
    }
    
    //Get gaurds Y position
    public double getY(){
        return y;
    }

    //set the value for gaurd
    public void setgaurdValues(int setx, int sety){
        x = setx;
        y = sety;
        speed = 4;
        direction = "down";
    }

    
    public void draw(Graphics2D g2, GamePanel gP){
        BufferedImage image = down1;
        g2.drawImage(image, x , y, gp.cellSize, gp.cellSize, null);
    }
}
