package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.objects.Chicken;
import com.group10.app.objects.Door;
import com.group10.app.objects.Key;
import com.group10.app.objects.Timer;
import com.group10.app.objects.Trap;

import java.util.Objects;
import java.util.Random;

/**
 *
 */
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
        createObj(new Key(gp), 11, 11);
        createObj(new Key(gp), 2, 15);
        createObj(new Key(gp), 18, 11);
        createObj(new Timer(gp), 3, 3);
        createObj(new Trap(gp), 19, 10);
        createObj(new Trap(gp), 19, 9);

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
        createObj(new Key(gp), 8, 11);
        createObj(new Key(gp), 16, 11);
        createObj(new Key(gp), 27, 15);
        createObj(new Key(gp), 26, 2);
        createObj(new Timer(gp), 2, 7);
        createObj(new Trap(gp), 6, 9);
        createObj(new Trap(gp), 7, 9);
        createObj(new Trap(gp), 8, 9);

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
        createObj(new Key(gp), 8, 8);
        createObj(new Key(gp), 9, 16);
        createObj(new Key(gp), 22, 7);
        createObj(new Key(gp), 4, 10);
        createObj(new Key(gp), 18, 13);
        createObj(new Timer(gp), 17, 7);
        createObj(new Trap(gp), 16, 10);
        createObj(new Trap(gp), 16, 11);
        createObj(new Trap(gp), 16, 5);
        createObj(new Trap(gp), 15, 5);
        createObj(new Trap(gp), 25, 2);
        createObj(new Trap(gp), 25, 3);
        createObj(new Trap(gp), 19, 15);
        createObj(new Trap(gp), 19, 16);
        // Create Door
        createDoor();
    }

    public void update(){
        createRandomObj(new Chicken(gp));
        deleteExpiredObj(1200);
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

        createObj(new Door(gp), 29, 7);
        createObj(new Door(gp), 29, 8);
        createObj(new Door(gp), 29, 9);
        createObj(new Door(gp), 29, 10);
        createObj(new Door(gp), 29, 11);
    }
}

