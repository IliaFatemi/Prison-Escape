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
 * This is for create and delete objects.
 */

public class ObjectManager {

    GamePanel gp;
    int doorIndex = 1;
    int randomObjCounter = 0;

    /**
     * This is the constructor for the AssetSetter class.
     *
     * @param gp
     */
    public ObjectManager(GamePanel gp){
        this.gp = gp;
    }

    /**
     * This is for create objects at level 1
     *
     * <p>
     *     This method will find a empty index in obj, then create
     *     object(Keys, Timer and Traps) by using createObj method.
     * </p>
     */
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

    /**
     * This is for create objects at level 2
     *
     * <p>
     *     This method will find a empty index in obj, then create
     *     object(Keys, Timer and Traps) by using createObj method.
     * </p>
     */
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
        createObj(new Timer(gp), 22, 14);
        createObj(new Trap(gp), 6, 9);
        createObj(new Trap(gp), 7, 9);
        createObj(new Trap(gp), 8, 9);

        // Create Door
        createDoor();
    }

    /**
     * This is for create objects at level 3
     *
     * <p>
     *     This method will find a empty index in obj, then create
     *     object(Keys, Timer and Traps) by using createObj method.
     * </p>
     */    public void setObjectLevel3(){
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

    /**
     * This is for update the random objects
     *
     * <p>
     *     This method will create objects periodic by using createRandomObj,
     *     and delete object that over the expired time by using deleteExpiredObj.
     * </p>
     */
    public void update(){
        createRandomObj(new Chicken(gp));
        deleteExpiredObj(1200);
    }

    /**
     * This is for create random objects that not locate in any walls
     *
     * <p>
     *     This method will create a random position object periodic.
     *     Before it create the object, it will check whether current position
     *     is valid. If it is invalid position (any collision tile), it will
     *     find a new random position, until the position is valid.
     * </p>
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
     * <p>
     *     This method will find a empty index of obj, then create this object
     *     in this index and set the object's position(worldX, worldY).
     *     If the object is door, also reset the image since the door consists
     *     of five pictures.
     * </p>
     *
     * @param entity passing in objects of entity
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
     * <p>
     *     Scan the whole obj, find the all objects named Chicken.
     *     Check each Chicken's disappear time, if it is larger than expired time, delete the object.
     * </p>
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
     *
     * <p>
     *     This method create the whole door.
     * </p>
     */
    public void createDoor() {

        createObj(new Door(gp), 29, 7);
        createObj(new Door(gp), 29, 8);
        createObj(new Door(gp), 29, 9);
        createObj(new Door(gp), 29, 10);
        createObj(new Door(gp), 29, 11);
    }
}


