package com.group10.app.objects;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

public class ObjChicken extends Entity {

    public ObjChicken(GamePanel gp){
        super(gp);

        name = "Chicken";
        down1 = setup("/collectibles/chickenDrumStick", gp.cellSize, gp.cellSize);
    }

}
