package com.group10.app.entity;

import com.group10.app.main.GamePanel;
import com.group10.app.main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Entity is the super class for all objects and characters
 * <p>
 *  This class is in charge of managing the whether there are collisions, managing the
 *  different sprite directions for the inmate and the guard, and direction management of the
 *  characters
 * </p>
 */
public class Entity {


    GamePanel gp;

    public BufferedImage up1, up2, up3, up4, up5, down1, down2, down3, down4, down5, left1, left2, left3, left4, left5, right1, right2, right3, right4, right5;
    public BufferedImage door1, door2, door3, door4, door5;
    public BufferedImage image;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;

    // States
    public String direction = "down";

    // Character Status
    public String name;
    public int speed = 2;

    // Counter
    public int disappears = 0;
    int doorLightly = 0;

    //
    public int x, y;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int solidX, solidY;
    //

    /**
     * The constructor for the Entity class
     * @param gp used for building entites on the gamepanel
     */
    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * This method is in charge of changing the appearance of the main gate
     * <p>
     *     This method checks the whether of not the inmate had collected all the
     *     keys for the round and then changes the appearance of the door (lessen the brightness)
     *     of it
     * </p>
     * @param g2
     */
    public void draw(Graphics2D g2) {

        int i = 25;

        if (gp.inmate.hasKey == 3){

            if (Objects.equals(name, "Door")) {

                if (doorLightly < i) {
                    changeAlpha(g2, 0.5f);
                }
                else {
                    changeAlpha(g2, 1f);
                }

                doorLightly++;

                if (doorLightly > 50){
                    doorLightly = 0;
                }
            }
        }

        g2.drawImage(down1, x, y, gp.cellSize, gp.cellSize, null);
        changeAlpha(g2, 1f);
    }

    /**
     * The method setup is in charge of registering the image directories for the objects/characters
     *
     * @param imagePath
     * @param width
     * @param height
     * @return image which is of type BufferedImage
     */
    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    /**
     * changes the brightness of the door based off the value of alphavalue after
     * all keys have been collected
     *
     * @param g2
     * @param alphaValue (scale factor for adjusting the brightness of the door)
     */

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

}
