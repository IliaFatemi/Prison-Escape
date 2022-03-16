package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.objects.ObjChicken;
import com.group10.app.objects.ObjKey;
import com.group10.app.objects.ObjTimer;
import com.group10.app.objects.ObjTrap;

public class AssetSetter {

    GamePanel gp;
    int objIndex = 0;

    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }

    public void setObject(){

//        gp.obj[1] = new ObjKey();
//        gp.obj[1].x = 15 * gp.cellSize;
//        gp.obj[1].y = 7 * gp.cellSize;
//
//        gp.obj[2] = new ObjKey();
//        gp.obj[2].x = 10 * gp.cellSize;
//        gp.obj[2].y = 10 * gp.cellSize;

        createObj(new ObjKey(gp), 2, 16);
        createObj(new ObjKey(gp), 13, 7);
        createObj(new ObjChicken(gp), 15, 6);
        createObj(new ObjTimer(gp), 18, 6);
        createObj(new ObjTrap(gp), 16, 10);

    }

    public void createObj(Entity entity, int worldX, int worldY) {
        gp.obj[objIndex] = entity;
        gp.obj[objIndex].worldX = gp.cellSize * worldX;
        gp.obj[objIndex].worldY = gp.cellSize * worldY;
        objIndex++;
    }
}

