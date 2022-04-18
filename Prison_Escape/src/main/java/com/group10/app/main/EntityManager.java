package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.entity.nonStaticEntities.Guard;
import com.group10.app.entity.nonStaticEntities.MovingActor;
import com.group10.app.entity.staticEntities.Chicken;
import com.group10.app.entity.staticEntities.Door;
import com.group10.app.entity.staticEntities.Key;
import com.group10.app.entity.staticEntities.Timer;
import com.group10.app.entity.staticEntities.Trap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

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
    public void setEntityLevel(int level){

        try {
            File file;

            if(level == 1) {
                file = new File("src/main/resources/entityLevelFiles/EntityLevel1.txt");
            }else if(level == 2){
                file = new File("src/main/resources/entityLevelFiles/EntityLevel2.txt");
            }else if(level == 3){
                file = new File("src/main/resources/entityLevelFiles/EntityLevel3.txt");
            }else{
                file = new File("src/main/resources/entityLevelFiles/EntityLevel4.txt");
            }

            Scanner myReader = new Scanner(file);

            String entityType;
            int entityX, entityY;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String [] setUpValues = data.split(" ");

                entityType = setUpValues[0];
                entityX = Integer.parseInt(setUpValues[1]);
                entityY = Integer.parseInt(setUpValues[2]);

                if(entityType.equals("Trap")){
                    createObj(new Trap(gp), entityX * gp.cellSize, entityY  * gp.cellSize);
                }else if(entityType.equals("Timer")){
                    createObj(new Timer(gp), entityX * gp.cellSize, entityY  * gp.cellSize);
                }else if(entityType.equals("Key")){
                    createObj(new Key(gp), entityX * gp.cellSize, entityY  * gp.cellSize);
                }else{
                    createGuard(new Guard(gp), entityX * gp.cellSize, entityY * gp.cellSize);
                }

            }
            createDoor();
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

        if (Objects.equals(entity.name, "Door")) {

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
        if (gp.getGameLevel() == 1){setEntityLevel(1);}
        else if (gp.getGameLevel() == 2){setEntityLevel(2);}
        else if (gp.getGameLevel() == 3){setEntityLevel(3);}
        else if (gp.getGameLevel() == 4){setEntityLevel(4);}
    }
}


