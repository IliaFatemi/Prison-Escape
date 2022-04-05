package com.group10.app.entity.staticEntities;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

/**
 * This is the class which embodies the collectible trap needed to decrease the score when collected.
 * <p>
 *  Trap class creates trap objects which spawn across the map, waiting to be collected.
 *  Upon collection, they disappear from the map and decrease the current score.
 * </p>
 */
public class Trap extends Entity {

    /**
     * Constructor Method to assign initial values to trap object.
     * <p>
     *     This method mainly assigns the initial values to the trap collectible
     *     and assigns the image filepath and the size of the object when spawned to a variable
     *     for easier usage when necessary.
     * </p>
     * @param gp
     */
    public Trap(GamePanel gp){
        super(gp);

        name = "Trap";
        down1 = setup("/trap/trap");
    }

}
