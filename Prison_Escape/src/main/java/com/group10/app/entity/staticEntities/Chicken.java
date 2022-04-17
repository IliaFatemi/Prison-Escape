package com.group10.app.entity.staticEntities;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

import java.awt.*;

/**
 * This is the class which embodies the chicken drumstick collectible.
 * <p>
 *  Chicken class creates objects which spawn across the map, and when collected
 *  by the player, it adds to the score. It is the main collectible that affects the score.
 * </p>
 */
public class Chicken extends Entity {

    GamePanel gp;
    /**
     * Constructor Method to assign initial values to chicken object.
     * <p>
     *     This method mainly assigns the initial values to the chicken drumstick collectible
     *     and assigns the image filepath and the size of the object when spawned to a variable
     *     for easier usage when necessary.
     * </p>
     * @param gp
     */
    public Chicken(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Chicken";
        down1 = setup("/collectibles/chickenDrumStick");
    }

    public void draw(Graphics2D g2) {

        if (disappears > 900){

            if (lightly < 25) {
                changeAlpha(g2, 0.5f);
            }

            lightly++;

            if (lightly > 50){
                lightly = 0;
            }
        }

        g2.drawImage(down1, x, y, gp.cellSize, gp.cellSize, null);
        changeAlpha(g2, 1f);
    }
}
