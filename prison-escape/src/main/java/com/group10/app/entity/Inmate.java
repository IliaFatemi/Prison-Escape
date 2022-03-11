package com.group10.app.entity;

import com.group10.app.main.GamePanel;
import com.group10.app.main.KeyManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;



public class Inmate extends Entity{
    GamePanel gp;
    KeyManager keyH;
    int hasKey = 0;
    int score = 0;
    int time = 0;

    public Inmate(GamePanel gp, KeyManager keyH){
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidArea.x = 8;
        solidArea.y = 8;
        solidX = solidArea.x;
        solidY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        solidX = 8;
        solidY = 16;

        setInmateValues();
        getInmateImage();
    }

    public void setInmateValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    
    public void getInmateImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkUp2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkUp3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkDown2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkDown3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkLeft2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkLeft3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkRight2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/inmate/walkRight3.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.pressedUp|| keyH.pressedDown || keyH.pressedLeft || keyH.pressedRight || keyH.pressedEscape) {
            if (keyH.pressedUp) {
                direction = "up";
                y -= speed;
            } else if (keyH.pressedDown) {
                direction = "down";
                y += speed;
            } else if (keyH.pressedLeft) {
                direction = "left";
                x -= speed;
            } else if (keyH.pressedRight) {
                direction = "right";
                x += speed;
            }

            int objectIndex = gp.collisionCheck.checkObject(this, true);
            pickUpObject(objectIndex);

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
    }

    //Get the x position of the player
    public double getX(){return x;}

    //Get the Y position of the player
    public double getY(){return y;}

    //Get player speed
    public double getSpeed(){return speed;}

    //Get player direction
    public String getDirection(){return direction;}

    //Setting the players position in the oposite direction
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

    public void pickUpObject (int i) {

        if (i != 999){

            String objectName = gp.obj[i].name;

            switch (objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key: " + hasKey);
                    break;
                case "Timer":
                    time += 10;
                    gp.obj[i] = null;
                    System.out.println("Time: " + hasKey);
                    break;
                case "Chicken":
                    score += 100;
                    gp.obj[i] = null;
                    System.out.println("Score: " + hasKey);
                    break;

            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
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