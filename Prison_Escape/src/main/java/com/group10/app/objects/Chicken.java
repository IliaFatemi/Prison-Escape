package com.group10.app.objects;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

/**
 * This is the class which embodies the chicken drumstick collectible.
 * <p>
 *  Chicken class creates objects which spawn across the map, and when collected
 *  by the player, it adds to the score. It is the main collectible that affects the score.
 * </p>
 */
public class Chicken extends Entity {

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

        name = "Chicken";
        down1 = setup("/collectibles/chickenDrumStick");
    }

}
