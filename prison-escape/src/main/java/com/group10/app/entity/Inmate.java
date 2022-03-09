package com.group10.app.entity;

import com.group10.app.main.GamePanel;
import com.group10.app.main.KeyManager;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Inmate extends Entity{
    GamePanel gp;
    KeyManager keyH;

    public Inmate(GamePanel gp, KeyManager keyH){
        this.gp = gp;
        this.keyH = keyH;
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
        if(keyH.pressedUp|| keyH.pressedDown || keyH.pressedLeft || keyH.pressedRight) {
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
