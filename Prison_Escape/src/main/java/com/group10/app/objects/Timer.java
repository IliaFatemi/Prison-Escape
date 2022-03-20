package com.group10.app.objects;

import com.group10.app.entity.Entity;
import com.group10.app.main.GamePanel;

public class Timer extends Entity {

    public Timer(GamePanel gp){
        super(gp);

        name = "Timer";
        down1 = setup("/collectibles/timer", gp.cellSize, gp.cellSize);
    }

}
