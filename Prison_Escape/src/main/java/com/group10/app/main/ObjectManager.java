package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.objects.ObjChicken;
import com.group10.app.objects.ObjDoor;
import com.group10.app.objects.ObjKey;
import com.group10.app.objects.ObjTimer;
import com.group10.app.objects.ObjTrap;

import java.util.Objects;
import java.util.Random;

/**
 * This is for create and delete objects
 */

public class ObjectManager {

    GamePanel gp;
    int doorIndex = 1;
    int randomObjCounter = 0;

    /**
     * This is the constructor for the AssetSetter class
     *
     * @param gp
     */
    public ObjectManager(GamePanel gp){
        this.gp = gp;
    }

    /**
     * This is for create objects at level 1
     */
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
        createObj(new ObjTimer(gp), 3, 3);
        createObj(new ObjTrap(gp), 19, 10);
        createObj(new ObjTrap(gp), 19, 9);

        // Create Door
        createDoor();
    }

    /**
     * This is for create objects at level 2
     */
    public void setObjectLevel2(){

        for (int i = 0; i < gp.obj.length; i++){
            if (gp.obj[i] != null){
                gp.obj[i] = null;
            }
        }

        // Create Key
        createObj(new ObjKey(gp), 8, 11);
        createObj(new ObjKey(gp), 16, 11);
        createObj(new ObjKey(gp), 27, 15);
        createObj(new ObjKey(gp), 26, 2);
        createObj(new ObjTimer(gp), 2, 7);
        createObj(new ObjTrap(gp), 6, 9);
        createObj(new ObjTrap(gp), 7, 9);
        createObj(new ObjTrap(gp), 8, 9);

        // Create Door
        createDoor();
    }

    /**
     * This is for create objects at level 2
     */
    public void setObjectLevel3(){
        for (int i = 0; i < gp.obj.length; i++){
            if (gp.obj[i] != null){
                gp.obj[i] = null;
            }
        }

        // Create Key
        createObj(new ObjKey(gp), 8, 8);
        createObj(new ObjKey(gp), 9, 16);
        createObj(new ObjKey(gp), 22, 7);
        createObj(new ObjKey(gp), 4, 10);
        createObj(new ObjKey(gp), 18, 13);
        createObj(new ObjTimer(gp), 17, 7);
        createObj(new ObjTrap(gp), 16, 10);
        createObj(new ObjTrap(gp), 16, 11);
        createObj(new ObjTrap(gp), 16, 5);
        createObj(new ObjTrap(gp), 15, 5);
        createObj(new ObjTrap(gp), 25, 2);
        createObj(new ObjTrap(gp), 25, 3);
        createObj(new ObjTrap(gp), 19, 15);
        createObj(new ObjTrap(gp), 19, 16);
        // Create Door
        createDoor();
    }

    /**
     * This is for update the random objects, including create objects and delete the expired objects
     */
    public void update(){
        createRandomObj(new ObjChicken(gp));
        deleteExpiredObj(1200);
    }

    /**
     * This is for create random objects that not locate in any walls
     *
     * @param entity passing in objects of entity
     */
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

    /**
     * This is for create object at position(worldX, worldY)
     *
     * @param entity passing in objects  of entity
     * @param worldX the object's x coordination
     * @param worldY the object's y coordination
     */
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

    /**
     * This is for counting random objects disappears time and delete if expired
     *
     * @param expiredTime the random objects will be deleted after expiredTime
     */
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

    /**
     * This is for create door
     */
    public void createDoor() {

        createObj(new ObjDoor(gp), 29, 7);
        createObj(new ObjDoor(gp), 29, 8);
        createObj(new ObjDoor(gp), 29, 9);
        createObj(new ObjDoor(gp), 29, 10);
        createObj(new ObjDoor(gp), 29, 11);
    }
}

