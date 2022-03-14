package com.group10.app.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        try{
        up1 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkUp1.png"));
        up2 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkUp2.png"));
        up3 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkUp3.png"));
        down1 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkDown1.png"));
        down2 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkDown2.png"));
        down3 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkDown3.png"));
        left1 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkLeft1.png"));
        left2 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkLeft2.png"));
        left3 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkLeft3.png"));
        right1 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkRight1.png"));
        right2 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkRight2.png"));
        right3 = ImageIO.read(getClass().getResourceAsStream("/prisonGaurd/WalkDown3.png"));
        }catch(IOException e){e.printStackTrace();}
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
