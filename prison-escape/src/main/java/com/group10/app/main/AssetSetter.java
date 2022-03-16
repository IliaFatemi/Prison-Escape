package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.objects.ObjChicken;

import java.util.Objects;
import java.util.Random;

public class AssetSetter {

    GamePanel gp;
    int objIndex = 0;
    int randomObjCounter = 0;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){}

    public void update(){
        createRandomObj(new ObjChicken(gp));
        deleteExpiredObj(1200);
    }

    public void createRandomObj(Entity entity) {
        System.out.println("random1");

        randomObjCounter++;

        if (randomObjCounter > 400) {

            Random random = new Random();

            while (true) {
                System.out.println("random2");

                int randomX;
                int randomY;

                randomX = random.nextInt(gp.maxWorldCol);
                randomY = random.nextInt(gp.maxWorldRow);

                int tileNum = gp.tileManage.mapTileNum[randomX][randomY];

                if (!gp.tileManage.tile[tileNum].collision) {
                    System.out.println("random3");
                    createObj(entity, randomX, randomY);
                    break;
                }
            }

            randomObjCounter = 0;
        }
    }

    public void createObj(Entity entity, int worldX, int worldY) {
        gp.obj[objIndex] = entity;
        gp.obj[objIndex].worldX = gp.cellSize * worldX;
        gp.obj[objIndex].worldY = gp.cellSize * worldY;
        objIndex++;
    }

    public void deleteExpiredObj(int expiredTime) {

        for (int i = 0; i < objIndex; i++){

            if (gp.obj[i] != null){
                if (Objects.equals(gp.obj[i].name, "Chicken")){
                    gp.obj[i].disappears++;

                    if (gp.obj[i].disappears > expiredTime){
                        gp.obj[i] = null;
                    }
                }
            }
        }
    }

}

