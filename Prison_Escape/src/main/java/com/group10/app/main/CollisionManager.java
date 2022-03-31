package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.entity.nonStaticEntities.Inmate;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Collision class for wall collision and object collision.
 */
public class CollisionManager {

    /**
     * Game panel will be used to access it's methods and variables
     */
    GamePanel gp;

    /**
     * @param gp the GamePanel object
     */
    public CollisionManager(GamePanel gp){
        this.gp = gp;
    }


    /**
     * wallCheck will check to see if the player is within a certain range of the block and will set the entity collision to true if the condiiton is true.
     * @param entity The entity that will be interacting with walls of the map
     */
    public void wallCheck(Entity entity){
        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int LeftCol = entityLeftWorldX/gp.cellSize;
        int RightCol = entityRightWorldX/gp.cellSize;
        int TopRow = entityTopWorldY/gp.cellSize;
        int BottomRow = entityBottomWorldY/gp.cellSize;

        int tile1, tile2;

        switch (entity.direction) {
            case "up":
                TopRow = (entityTopWorldY - entity.speed) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][TopRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
            break;
            case "down":
                BottomRow = (entityBottomWorldY + entity.speed) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][BottomRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
            break;
            case "left":
                LeftCol = (entityLeftWorldX - entity.speed) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[LeftCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
            break;
            case "right":
                RightCol = (entityRightWorldX + entity.speed) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[RightCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
            break;
        }
    }

    /**
     * To check if any object in obj list collide with inmate.
     *
     * <p>
     *     Check objects one by one in obj list.
     *     Get each object's solidArea, and inmate's solidArea by direction.
     *     Judge the object if collide with inmate.
     *     If any of object collide with inmate, return the object index.
     *     Otherwise, return 999 means no object touch inmate.
     * </p>
     *
     * @param entity passing entity to charge as a parameter
     * @return       index of the object if anyone collides with inmate, or 999
     */
    public int checkObject(Entity entity) {

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++){
            if (gp.obj[i] != null){

                //Get entity's solid area position
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                //Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].x + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].y + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up" : {
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collision = true;
                            }
                            index = i;

                        }
                    }
                    case "down" : {
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collision = true;
                            }
                            index = i;
                        }
                    }
                    case "left" : {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collision = true;
                            }
                            index = i;
                        }
                    }
                    case "right" : {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collision = true;
                            }
                            index = i;
                        }
                    }
                }

                entity.solidArea.x = entity.solidX;
                entity.solidArea.y = entity.solidY;
                gp.obj[i].solidArea.x = gp.obj[i].solidX;
                gp.obj[i].solidArea.y = gp.obj[i].solidY;
            }
        }

        return index;
    }


    /**
     * Setting up collision between two objects.
     * @param inmate The player object
     * @param objectX position X of the object
     * @param objectY position Y of the object
     * @param collision_type The collision distance between the player and the object
     * @return boolean
     */
    public boolean checkGuard(Inmate inmate, double objectX, double objectY, int collision_type){
        double distance = sqrt(pow(inmate.getX() - objectX, 2) + pow(inmate.getY() - objectY, 2));
        return distance <= collision_type;
    }
}
