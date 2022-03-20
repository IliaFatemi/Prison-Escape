package com.group10.app.objects;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

public class Trap extends Entity {

    public Trap(GamePanel gp){
        super(gp);

        name = "Trap";
        down1 = setup("/trap/trap", gp.cellSize, gp.cellSize);
    }

}
