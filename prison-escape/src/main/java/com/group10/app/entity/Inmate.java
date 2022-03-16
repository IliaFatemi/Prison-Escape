package com.group10.app.entity;

import com.group10.app.main.GamePanel;
import com.group10.app.main.KeyManager;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Inmate extends Entity{

    KeyManager keyH;
    public int hasKey = 0;
    public int score = 0;
    public double time = 100;
    public final int screenX;
    public final int screenY;

    public Inmate(GamePanel gp, KeyManager keyH){
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.cellSize/2;
        screenY = gp.screenHeight/2 - gp.cellSize/2;

        //Solid area
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setInmateValues();
        getInmateImage();
    }

    public void setInmateValues(){
        worldX = gp.cellSize * 11;
        worldY = gp.cellSize * 2;
        speed = 2;
        direction = "down";
    }

    public void getInmateImage(){
        up1 = setup("/inmate/walkUp1", gp.cellSize, gp.cellSize);
        up2 = setup("/inmate/walkUp2", gp.cellSize, gp.cellSize);
        up3 = setup("/inmate/walkUp3", gp.cellSize, gp.cellSize);
        down1 = setup("/inmate/walkDown1", gp.cellSize, gp.cellSize);
        down2 = setup("/inmate/walkDown2", gp.cellSize, gp.cellSize);
        down3 = setup("/inmate/walkDown3", gp.cellSize, gp.cellSize);
        left1 = setup("/inmate/walkLeft1", gp.cellSize, gp.cellSize);
        left2 = setup("/inmate/walkLeft2", gp.cellSize, gp.cellSize);
        left3 = setup("/inmate/walkLeft3", gp.cellSize, gp.cellSize);
        right1 = setup("/inmate/walkRight1", gp.cellSize, gp.cellSize);
        right2 = setup("/inmate/walkRight2", gp.cellSize, gp.cellSize);
        right3 = setup("/inmate/walkRight3", gp.cellSize, gp.cellSize);
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

            // Check tile collision
            collision = false;
            gp.collisionCheck.wallCheck(this);

            // Check object collision
            int objectIndex = gp.collisionCheck.checkObject(this, true);
            pickUpObject(objectIndex);

            if(!collision){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
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
    public double getX(){return worldX;}

    //Get the Y position of the player
    public double getY(){return worldY;}

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
    public void setPos(int posX, int posY){worldX = posX; worldY = posY;}

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
        worldX = gp.cellSize * 11;
        worldY = gp.cellSize * 11;
        time = 100;
        score = 0;
    }

    /*
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
     */

    public void pickUpObject (int i) {

        if (i != 999){

            String objectName = gp.obj[i].name;
            String text = "Got a " + gp.obj[i].name + "!";;
            gp.ui.addMessage(text);

            switch (objectName) {
                case "Key" -> {
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                }
                case "Timer" -> {
                    gp.playSE(2);
                    time += 20;
                    gp.obj[i] = null;
                }
                case "Chicken" -> {
                    gp.playSE(3);
                    score += 100;
                    gp.obj[i] = null;
                }
                case "Trap" -> {
                    gp.playSE(4);
                    score -= 50;
                    gp.obj[i] = null;
                }
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
            }
            default -> {
            }
        }

        int x = screenX;
        int y = screenY;

        if (screenX > worldX){x = worldX;}
        if (screenY > worldY){y = worldY;}

        int rightOffset = gp.screenWidth - screenX;
        if (rightOffset > gp.worldWidth - worldX){
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }

        int bottomOffset = gp.screenHeight - screenY;
        if (bottomOffset > gp.worldHeight - worldY){
            y = gp.screenHeight - (gp.worldHeight - worldY);
        }

        g2.drawImage(image, x , y, null);
    }
}