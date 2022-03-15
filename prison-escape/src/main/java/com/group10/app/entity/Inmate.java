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
    public int hasKey = 0;
    public int score = 0;
    public double time = 100;

    public Inmate(GamePanel gp, KeyManager keyH){
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidArea.x = 0;
        solidArea.y = 0;
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
        speed = 2;
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
        if(keyH.pressedUp|| keyH.pressedDown || keyH.pressedLeft || keyH.pressedRight) {
            if (keyH.pressedUp) {
                direction = "up";
            } else if (keyH.pressedDown) {
                direction = "down";
            } else if (keyH.pressedLeft) {
                direction = "left";
            } else if (keyH.pressedRight) {
                direction = "right";
            }

            int objectIndex = gp.collisionCheck.checkObject(this, true);
            pickUpObject(objectIndex);

            collision = false;
            gp.collisionCheck.wallCheck(this);

            if(collision == false){
                switch (direction) {
                    case "up": y -= speed; break;
                    case "down": y += speed; break;
                    case "left": x -= speed; break;
                    case "right": x += speed; break;
                }
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
    }

    //Get the x position of the player
    public double getX(){return x;}

    //Get the Y position of the player
    public double getY(){return y;}

    //Get player speed
    public double getSpeed(){return speed;}

    //Get player direction
    public String getDirection(){return direction;}

    //Get number of keys collected
    public int getNumKeys(){return hasKey;}

    //Get the score collected 
    public int getScore(){return score;}

    //getting the current timer
    public int getTimer(){return (int)time;}

    //Set the timer
    public void setTimer(double newTime){time = newTime;}

    //set the score
    public void setScore(int newScore){score = newScore;}

    //set player position
    public void setPos(int posX, int posY){x = posX; y = posY;}

    //set player speed
    public void setSpeed(int newSpeed){speed = newSpeed;}
    
    //set players direction
    public void setDirection(String newDir){direction = newDir;}

    //set key amount
    public void setNumKeys(int newNumKeys){hasKey = newNumKeys;}

    //reset the keys
    public void resetKeys(){hasKey = 0;}

    //reset everything
    public void resetInmate(){
        x = 100;
        y = 100;
        time = 100;
        score = 0;
    }

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
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Timer":
                    gp.playSE(2);
                    time += 20;
                    gp.obj[i] = null;
                    break;
                case "Chicken":
                    gp.playSE(3);
                    score += 100;
                    gp.obj[i] = null;
                    break;
                case "Trap":
                    gp.playSE(4);
                    score -= 50;
                    gp.obj[i] = null;
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
            default:
            break;
        }

        g2.drawImage(image, x , y, gp.cellSize, gp.cellSize, null);
    }
}