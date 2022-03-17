package com.group10.app.objects;

import com.group10.app.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
//    public int worldX, worldY;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidX = 0;
    public int solidY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {

//        int screenX = worldX - gp.inmate.worldX + gp.inmate.screenX;
//        int screenY = worldY - gp.inmate.worldY + gp.inmate.screenY;
//
//        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
//                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
//                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
//                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
//
//
//        }
        g2.drawImage(image, x, y, gp.cellSize, gp.cellSize, null);

    }


}
