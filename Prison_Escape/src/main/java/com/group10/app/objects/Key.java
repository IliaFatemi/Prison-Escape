package com.group10.app.objects;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

public class Key extends Entity {

    public Key(GamePanel gp){
        super(gp);

        name = "Key";
        down1 = setup("/collectibles/key", gp.cellSize, gp.cellSize);
    }

}