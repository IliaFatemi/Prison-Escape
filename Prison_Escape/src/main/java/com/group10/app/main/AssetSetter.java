package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.objects.ObjChicken;
import com.group10.app.objects.ObjDoor;
import com.group10.app.objects.ObjKey;

import java.util.Objects;
import java.util.Random;

public class AssetSetter {

    GamePanel gp;
    int doorIndex = 1;
    int randomObjCounter = 0;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObjectLevel1(){

        for (int i = 0; i < gp.obj.length; i++){
            if (gp.obj[i] != null){
                gp.obj[i] = null;
            }
        }

        // Create Key
        createObj(new ObjKey(gp), 11, 11);
        createObj(new ObjKey(gp), 2, 15);
        createObj(new ObjKey(gp), 18, 11);

        // Create Door
        createDoor();
    }

    public void setObjectLevel2(){

        for (int i = 0; i < gp.obj.length; i++){
            if (gp.obj[i] != null){
                gp.obj[i] = null;
            }
        }

        // Create Key
        createObj(new ObjKey(gp), 27, 8);
        createObj(new ObjKey(gp), 27, 9);
        createObj(new ObjKey(gp), 27, 10);

        // Create Door
        createDoor();
    }

    public void setObjectLevel3(){

        for (int i = 0; i < gp.obj.length; i++){
            if (gp.obj[i] != null){
                gp.obj[i] = null;
            }
        }

        // Create Key
        createObj(new ObjKey(gp), 27, 8);
        createObj(new ObjKey(gp), 27, 9);
        createObj(new ObjKey(gp), 27, 10);

        // Create Door
        createDoor();
    }

    public void update(){
        createRandomObj(new ObjChicken(gp));
        deleteExpiredObj(1200);
        //deleteDoor(3);
    }

    public void createRandomObj(Entity entity) {

        randomObjCounter++;

        if (randomObjCounter > 400) {

            Random random = new Random();

            while (true) {

                int randomX;
                int randomY;

                randomX = random.nextInt(gp.screenColNumber);
                randomY = random.nextInt(gp.screenRowNumber);

                int tileNum = gp.tileManage.mapTileNum[randomX][randomY];

                if (!gp.tileManage.tile[tileNum].collision) {
                    createObj(entity, randomX, randomY);
                    break;
                }
            }

            randomObjCounter = 0;
        }
    }

    public void createObj(Entity entity, int worldX, int worldY) {

        int i = 0;

        while (gp.obj[i] != null){
            i++;
        }

        if (Objects.equals(entity.name, "Door")) {
            System.out.println("Door index: " + doorIndex);

            entity.down1 = entity.setup("/tiles/exit" + doorIndex, gp.cellSize, gp.cellSize);
            doorIndex++;

            if (doorIndex > 5) {
                doorIndex = 1;
            }
        }

        gp.obj[i] = entity;
        gp.obj[i].x = gp.cellSize * worldX;
        gp.obj[i].y = gp.cellSize * worldY;
    }

    public void deleteExpiredObj(int expiredTime) {

        for (int i = 0; i < gp.obj.length; i++){

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

    public void createDoor() {

        createObj(new ObjDoor(gp), 29, 7);
        createObj(new ObjDoor(gp), 29, 8);
        createObj(new ObjDoor(gp), 29, 9);
        createObj(new ObjDoor(gp), 29, 10);
        createObj(new ObjDoor(gp), 29, 11);
    }
}

