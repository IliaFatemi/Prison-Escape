package com.group10.app.objects;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

public class ObjDoor extends Entity {

    public ObjDoor(GamePanel gp){
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

    public void drawDoor() {

    }
}
