package com.group10.app.entity;

import com.group10.app.main.GamePanel;

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

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage door1, door2, door3, door4, door5;
    public BufferedImage image;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;

    // States
    public String direction = "down";

    // Character Status
    public String name;
    public int speed;

    // Counter
    public int disappears = 0;
    public int lightly = 0;

    //
    public int x, y;
    public int spriteCounter = 0;
    public int solidX, solidY;
    //

    /**
     * The constructor for the Entity class
     * @param gp used for building entities on the game panel
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
     * @param g2 passing the GamePanel's Graphics2D
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(down1, x, y, gp.cellSize, gp.cellSize, null);
    }

    public void update(){}

    /**
     * The method setup is in charge of registering the image directories for the objects/characters
     *
     * @param imagePath the image's path
     * @return image which is of type BufferedImage
     */
    public BufferedImage setup(String imagePath) {

        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    /**
     * Returns the x value of the entity.
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x value of the entity.
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y value of the entity.
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y value of the entity.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the collision boolean to true of false as required.
     * @param collision
     */
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    /**
     * Changes the brightness
     */
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
}
