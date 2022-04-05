package com.group10.app.entity.staticEntities;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

/**
 * This is the class which embodies the collectible timer needed to increase the time left.
 * <p>
 *  Timer class creates timer objects which spawn across the map, waiting to be collected.
 *  Upon collection, they disappear from the map and add extra time to the countdown timer,
 *  depending upon the level.
 * </p>
 */
public class Timer extends Entity {

    /**
     * Constructor Method to assign initial values to timer object.
     * <p>
     *     This method mainly assigns the initial values to the timer collectible
     *     and assigns the image filepath and the size of the object when spawned to a variable
     *     for easier usage when necessary.
     * </p>
     * @param gp
     */
    public Timer(GamePanel gp){
        super(gp);
        name = "Timer";
        down1 = setup("/collectibles/timer");
    }
}
