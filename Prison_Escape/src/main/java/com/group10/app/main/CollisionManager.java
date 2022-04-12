package com.group10.app.main;

import com.group10.app.entity.nonStaticEntities.Inmate;
import com.group10.app.entity.nonStaticEntities.MovingActor;

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
     * @param actor The entity that will be interacting with walls of the map
     */
    public void wallCheck(MovingActor actor){

        int entityLeftWorldX = actor.getX() + actor.getSolidArea().x;
        int entityRightWorldX = actor.getX() + actor.getSolidArea().x + actor.getSolidArea().width;
        int entityTopWorldY = actor.getY() + actor.getSolidArea().y;
        int entityBottomWorldY = actor.getY() + actor.getSolidArea().y + actor.getSolidArea().height;

        int LeftCol = entityLeftWorldX/gp.cellSize;
        int RightCol = entityRightWorldX/gp.cellSize;
        int TopRow = entityTopWorldY/gp.cellSize;
        int BottomRow = entityBottomWorldY/gp.cellSize;

        int tile1, tile2;

        switch (actor.getDirection()) {
            case "up":
                TopRow = (entityTopWorldY - actor.getSpeed()) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][TopRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    actor.collision = true;
                }
            break;
            case "down":
                BottomRow = (entityBottomWorldY + actor.getSpeed()) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][BottomRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    actor.collision = true;
                }
            break;
            case "left":
                LeftCol = (entityLeftWorldX - actor.getSpeed()) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[LeftCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    actor.collision = true;
                }
            break;
            case "right":
                RightCol = (entityRightWorldX + actor.getSpeed()) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[RightCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    actor.collision = true;
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
     * @param actor passing entity to charge as a parameter
     * @return       index of the object if anyone collides with inmate, or 999
     */
    public int checkObject(MovingActor actor) {

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++){
            if (gp.obj[i] != null){

                //Get inmate's solid area position
                actor.getSolidArea().x = actor.getX() + actor.getSolidArea().x;
                actor.getSolidArea().y = actor.getY() + actor.getSolidArea().y;
                //Get object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].x + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].y + gp.obj[i].solidArea.y;

                switch (actor.getDirection()) {
                    case "up" : {
                        actor.getSolidArea().y -= actor.getSpeed();
                        if (actor.getSolidArea().intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                actor.collision = true;
                            }
                            index = i;
                        }
                    }
                    case "down" : {
                        actor.getSolidArea().y += actor.getSpeed();
                        if (actor.getSolidArea().intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                actor.collision = true;
                            }
                            index = i;
                        }
                    }
                    case "left" : {
                        actor.getSolidArea().x -= actor.getSpeed();
                        if (actor.getSolidArea().intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                actor.collision = true;
                            }
                            index = i;
                        }
                    }
                    case "right" : {
                        actor.getSolidArea().x += actor.getSpeed();
                        if (actor.getSolidArea().intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                actor.collision = true;
                            }
                            index = i;
                        }
                    }
                }

                actor.getSolidArea().x = actor.getSolidAreaDefaultX();
                actor.getSolidArea().y = actor.getSolidAreaDefaultY();
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
