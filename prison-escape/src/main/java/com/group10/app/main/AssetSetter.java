package com.group10.app.main;

import com.group10.app.objects.ObjChicken;
import com.group10.app.objects.ObjKey;
import com.group10.app.objects.ObjTimer;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }

    public void setObject(){

        gp.obj[0] = new ObjKey();
        gp.obj[0].x = 11 * gp.cellSize;
        gp.obj[0].y = 7 * gp.cellSize;

        gp.obj[1] = new ObjKey();
        gp.obj[1].x = 15 * gp.cellSize;
        gp.obj[1].y = 7 * gp.cellSize;

        gp.obj[2] = new ObjKey();
        gp.obj[2].x = 10 * gp.cellSize;
        gp.obj[2].y = 10 * gp.cellSize;

        gp.obj[3] = new ObjTimer();
        gp.obj[3].x = 15 * gp.cellSize;
        gp.obj[3].y = 3 * gp.cellSize;

        gp.obj[4] = new ObjTimer();
        gp.obj[4].x = 5 * gp.cellSize;
        gp.obj[4].y = 10 * gp.cellSize;

        gp.obj[5] = new ObjChicken();
        gp.obj[5].x = 8 * gp.cellSize;
        gp.obj[5].y = 11 * gp.cellSize;

        gp.obj[6] = new ObjChicken();
        gp.obj[6].x = 17 * gp.cellSize;
        gp.obj[6].y = 2 * gp.cellSize;



    }
}

