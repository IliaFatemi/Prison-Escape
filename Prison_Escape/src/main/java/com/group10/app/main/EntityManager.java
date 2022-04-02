package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.entity.nonStaticEntities.Guard;
import com.group10.app.entity.nonStaticEntities.MovingActor;
import com.group10.app.entity.staticEntities.Chicken;
import com.group10.app.entity.staticEntities.Door;
import com.group10.app.entity.staticEntities.Key;
import com.group10.app.entity.staticEntities.Timer;
import com.group10.app.entity.staticEntities.Trap;

import java.util.Objects;
import java.util.Random;

/**
 * This is for create and delete entities.
 */

public class EntityManager {

    GamePanel gp;
    int doorIndex = 1;
    int randomObjCounter = 0;

    /**
     * This is the constructor for the AssetSetter class.
     *
     * @param gp passing the GamePanel to use GamePanel's variables
     */
    public EntityManager(GamePanel gp){
        this.gp = gp;
    }

    /**
     * This is for create objects at level 1
     *
     * <p>
     *     This method create object(Keys, Timer and Traps)
     *     and guards by using createObj/createGuard method.
     * </p>
     */
    public void setEntityLevel1(){

        // Create Objects
        createObj(new Key(gp), 11 * gp.cellSize, 11 * gp.cellSize);
        createObj(new Key(gp), 2 * gp.cellSize, 15 * gp.cellSize);
        createObj(new Key(gp), 18 * gp.cellSize, 11 * gp.cellSize);
        createObj(new Timer(gp), 3 * gp.cellSize, 3 * gp.cellSize);
        createObj(new Trap(gp), 19 * gp.cellSize, 10 * gp.cellSize);
        createObj(new Trap(gp), 19 * gp.cellSize, 9 * gp.cellSize);

        // Create Door
        createDoor();

        // Create Guard
        createGuard(new Guard(gp), 6 * gp.cellSize, 8 * gp.cellSize);
        createGuard(new Guard(gp), 7 * gp.cellSize, 8 * gp.cellSize);
        createGuard(new Guard(gp), 8 * gp.cellSize, 8 * gp.cellSize);

        System.out.println("Creat lvl1 obj");
    }

    /**
     * This is for create objects at level 2
     *
     * <p>
     *     This method create object(Keys, Timer and Traps)
     *     and guards by using createObj/createGuard method.
     * </p>
     */
    public void setEntityLevel2(){

        // Create Objects
        createObj(new Key(gp), 8 * gp.cellSize, 11 * gp.cellSize);
        createObj(new Key(gp), 16 * gp.cellSize, 11 * gp.cellSize);
        createObj(new Key(gp), 27 * gp.cellSize, 15 * gp.cellSize);
        createObj(new Key(gp), 26 * gp.cellSize, 2 * gp.cellSize);
        createObj(new Timer(gp), 2 * gp.cellSize, 7 * gp.cellSize);
        createObj(new Timer(gp), 22 * gp.cellSize, 14 * gp.cellSize);
        createObj(new Trap(gp), 6 * gp.cellSize, 9 * gp.cellSize);
        createObj(new Trap(gp), 7 * gp.cellSize, 9 * gp.cellSize);
        createObj(new Trap(gp), 8 * gp.cellSize, 9 * gp.cellSize);

        // Create Door
        createDoor();

        // Create Guard
        createGuard(new Guard(gp), 6 * gp.cellSize, 8 * gp.cellSize);
        createGuard(new Guard(gp), 7 * gp.cellSize, 8 * gp.cellSize);
        createGuard(new Guard(gp), 8 * gp.cellSize, 8 * gp.cellSize);
    }

    /**
     * This is for create objects at level 3
     *
     * <p>
     *     This method create object(Keys, Timer and Traps)
     *     and guards by using createObj/createGuard method.
     * </p>
     */
    public void setEntityLevel3(){
        for (int i = 0; i < gp.obj.length; i++){
            if (gp.obj[i] != null){
                gp.obj[i] = null;
            }
        }

        // Create Objects
        createObj(new Key(gp), 8 * gp.cellSize, 8 * gp.cellSize);
        createObj(new Key(gp), 9 * gp.cellSize, 16 * gp.cellSize);
        createObj(new Key(gp), 22 * gp.cellSize, 7 * gp.cellSize);
        createObj(new Key(gp), 4 * gp.cellSize, 10 * gp.cellSize);
        createObj(new Key(gp), 18 * gp.cellSize, 13 * gp.cellSize);
        createObj(new Timer(gp), 17 * gp.cellSize, 7 * gp.cellSize);
        createObj(new Trap(gp), 16 * gp.cellSize, 10 * gp.cellSize);
        createObj(new Trap(gp), 16 * gp.cellSize, 11 * gp.cellSize);
        createObj(new Trap(gp), 16 * gp.cellSize, 5 * gp.cellSize);
        createObj(new Trap(gp), 15 * gp.cellSize, 5 * gp.cellSize);
        createObj(new Trap(gp), 25 * gp.cellSize, 2 * gp.cellSize);
        createObj(new Trap(gp), 25 * gp.cellSize, 3 * gp.cellSize);
        createObj(new Trap(gp), 19 * gp.cellSize, 15 * gp.cellSize);
        createObj(new Trap(gp), 19 * gp.cellSize, 16 * gp.cellSize);

        // Create Door
        createDoor();

        // Create Guards
        createGuard(new Guard(gp), 6 * gp.cellSize, 8 * gp.cellSize);
        createGuard(new Guard(gp), 7 * gp.cellSize, 8 * gp.cellSize);
        createGuard(new Guard(gp), 8 * gp.cellSize, 8 * gp.cellSize);
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
                    createObj(entity, randomX * gp.cellSize, randomY * gp.cellSize);
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

        if (Objects.equals(entity.getName(), "Door")) {

            entity.down1 = entity.setup("/tiles/exit" + doorIndex);
            doorIndex++;

            if (doorIndex > 5) {
                doorIndex = 1;
            }
        }

        gp.obj[i] = entity;
        gp.obj[i].x = worldX;
        gp.obj[i].y = worldY;
    }

    /**
     * This is for create guard at position(worldX, worldY)
     *
     * <p>
     *     This method will find a empty index in guard list, then create a guard
     *     in this index and set the guard's position(worldX, worldY).
     * </p>
     *
     * @param guard passing in guard of entity
     * @param x the guard's x coordination
     * @param y the guard's y coordination
     */
    public void createGuard(MovingActor guard, int x, int y) {

        int i = 0;

        while (gp.guard[i] != null){
            i++;
        }

        gp.guard[i] = guard;
        gp.guard[i].setX(x);
        gp.guard[i].setY(y);
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
                if (Objects.equals(gp.obj[i].getName(), "Chicken")){
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

        createObj(new Door(gp), 29 * gp.cellSize, 7 * gp.cellSize);
        createObj(new Door(gp), 29 * gp.cellSize, 8 * gp.cellSize);
        createObj(new Door(gp), 29 * gp.cellSize, 9 * gp.cellSize);
        createObj(new Door(gp), 29 * gp.cellSize, 10 * gp.cellSize);
        createObj(new Door(gp), 29 * gp.cellSize, 11 * gp.cellSize);
    }

    /**
     * <p>Setting up the objects such as timer, keys, traps and drum sticks for each level in the game.</p>
     */
    public void setUpAsset() {
        if (GamePanel.GAME_LEVEL == 1){setEntityLevel1();}
        else if (GamePanel.GAME_LEVEL == 2){setEntityLevel2();}
        else if (GamePanel.GAME_LEVEL == 3){setEntityLevel3();}
    }
}


