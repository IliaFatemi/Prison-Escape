package com.group10.app.entity;

import com.group10.app.main.GamePanel;
import com.group10.app.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage image;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;

    // State
    public int worldX, worldY;
    public String direction = "down";

    // Character Status
    public String name;
    public int speed;

    // Type
    public int type;

    //
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int solidX, solidY;
    //

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.inmate.worldX + gp.inmate.screenX;
        int screenY = worldY - gp.inmate.worldY + gp.inmate.screenY;

        if (worldX + gp.cellSize > gp.inmate.worldX - gp.inmate.screenX &&
                worldX - gp.cellSize < gp.inmate.worldX + gp.inmate.screenX &&
                worldY + gp.cellSize > gp.inmate.worldY - gp.inmate.screenY &&
                worldY - gp.cellSize < gp.inmate.worldY + gp.inmate.screenY) {

            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                case "left" -> {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                case "right" -> {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
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

        g2.drawImage(image, x, y, gp.cellSize, gp.cellSize, null);
    }

    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

}
