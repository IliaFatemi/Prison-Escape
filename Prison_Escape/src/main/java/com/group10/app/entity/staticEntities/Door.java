package com.group10.app.entity.staticEntities;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

import java.awt.*;

/**
 * This class embodies the door through which the player escapes at the end of each level.
 * <p>
 *     Creates the Door object which opens when all the keys on a particular level are collected.
 *     This Door object flashes when opened, indicating to the user that they can now escape and
 *     continue to the next level.
 * </p>
 */
public class Door extends Entity {

    GamePanel gp;

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

        this.gp = gp;

        name = "Door";

        door1 = setup("/tiles/exit1");
        door2 = setup("/tiles/exit2");
        door3 = setup("/tiles/exit3");
        door4 = setup("/tiles/exit4");
        door5 = setup("/tiles/exit5");

        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void draw(Graphics2D g2) {

        int i = 25;

        if (gp.inmate.hasKey == 2 + GamePanel.GAME_LEVEL){

            if (doorLightly < i) {
                changeAlpha(g2, 0.5f);
                System.out.println("GAME_LEVEL is " + GamePanel.GAME_LEVEL);
            }
            else {
                changeAlpha(g2, 1f);
            }

            doorLightly++;

            if (doorLightly > 50){
                doorLightly = 0;
            }
        }

        g2.drawImage(down1, x, y, gp.cellSize, gp.cellSize, null);
        changeAlpha(g2, 1f);
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
