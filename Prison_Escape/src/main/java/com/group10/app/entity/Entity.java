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
    public String direction = "down";

    // Character Status
    public String name;
    public int speed;

    // Counter
    public int disappears = 0;

    // Type
    public int type;

    //
    public int x, y;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int solidX, solidY;
    //

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(down1, x, y, gp.cellSize, gp.cellSize, null);
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
