package com.group10.app.objects;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

/**
 * This class embodies the door through which the player escapes at the end of each level.
 * <p>
 *     Creates the Door object which opens when all the keys on a particular level are collected.
 *     This Door object flashes when opened, indicating to the user that they can now escape and
 *     continue to the next level.
 * </p>
 */
public class Door extends Entity {

    /**
     * Constructor method to assign initial values to the Door object.
     * <p>
     *     This constructor method sets up the Door's filepath and size when spawned on the map.
     *     Initially it assigns it's collision variable to "true" since it is locked at the start
     *     of the round but when all the keys are collected, it is opened.
     * </p>
     * @param gp
     */
    public Door(GamePanel gp){
        super(gp);

        name = "Door";

        door1 = setup("/tiles/exit1", gp.cellSize, gp.cellSize);
        door2 = setup("/tiles/exit2", gp.cellSize, gp.cellSize);
        door3 = setup("/tiles/exit3", gp.cellSize, gp.cellSize);
        door4 = setup("/tiles/exit4", gp.cellSize, gp.cellSize);
        door5 = setup("/tiles/exit5", gp.cellSize, gp.cellSize);

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
