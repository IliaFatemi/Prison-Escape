package com.group10.app.entity.staticEntities;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

/**
 * This is the class which embodies the collectible keys needed to escape a level.
 * <p>
 *  Key class creates key objects which spawn across the map, waiting to be collected.
 *  Upon collection, they disappear from the map and when all keys are collected,
 *  the player can escape the level through the escape door.
 * </p>
 */
public class Key extends Entity {

    /**
     * Constructor Method to assign initial values to key object.
     * <p>
     *     This method mainly assigns the initial values to the key collectible
     *     and assigns the image filepath and the size of the object when spawned to a variable
     *     for easier usage when necessary.
     * </p>
     * @param gp
     */
    public Key(GamePanel gp){
        super(gp);

        name = "Key";
        down1 = setup("/collectibles/key");
    }

}