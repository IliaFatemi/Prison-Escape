package com.group10.app.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import com.group10.app.main.GamePanel;
import java.util.Objects;
import java.util.Random;


public class Gaurd extends Entity{
    GamePanel gp;

    public Gaurd(GamePanel gp, int setx, int sety){
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

    //Get guard direction
    public String getDirection(){
        return direction;
    }

    //Setting the guards position in the opposite direction
    public void revertPosition(String pos){
        if(pos == "down"){
            y *= -1;
            if(y >= 0){
                y -= 1;
            }
            else{
                y += 1;
            }
        }
        else if(pos == "up"){
            y *= -1;
            if(y >= 0){
                y -= 100;
            }
            else{
                y += 100;
            }
        }
        else if(pos == "left"){
            x *= -1;
            if(x >= 0){
                x -= 100;
            }
            else{
                x += 100;
            }
        }
        else if(pos == "right"){
            x *= -1;
            if(x >= 0){
                x -= 1;
            }
            else{
                x += 1;
            }
        }
    }

    //set the value for gaurd
    public void setgaurdValues(int setx, int sety){
        x = setx;
        y = sety;
        speed = 2;
        direction = "down";
    }

    public void getGuardAction(){
        spriteCounter++;
        if(spriteCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            spriteCounter = 0;
        }
    }

    public void update(){
        getGuardAction();
        if (Objects.equals(direction, "up")) {
            y -= speed;
        } else if (Objects.equals(direction, "down")) {
            y += speed;
        } else if (Objects.equals(direction, "left")) {
            x -= speed;
        } else if (Objects.equals(direction, "right")) {
            x += speed;
        }
        spriteCounter++;
        if (spriteCounter > 11) {
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

    
    public void draw(Graphics2D g2, GamePanel gP){
        BufferedImage image = down1;
        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                if(spriteNum == 3){
                    image = up3;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                if(spriteNum == 3){
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                if(spriteNum == 3){
                    image = left3;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                if(spriteNum == 3){
                    image = right3;
                }
                break;
        }
        g2.drawImage(image, x , y, gp.cellSize, gp.cellSize, null);
    }
}
