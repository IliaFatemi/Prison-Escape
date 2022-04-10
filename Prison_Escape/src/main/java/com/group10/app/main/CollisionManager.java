package com.group10.app.main;

import com.group10.app.entity.nonStaticEntities.Inmate;
import com.group10.app.entity.nonStaticEntities.MovingActor;

import java.util.Objects;

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
    public void wallCheck(MovingActor entity){

        int entityLeftWorldX = entity.getX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int LeftCol = entityLeftWorldX/gp.cellSize;
        int RightCol = entityRightWorldX/gp.cellSize;
        int TopRow = entityTopWorldY/gp.cellSize;
        int BottomRow = entityBottomWorldY/gp.cellSize;

        int tile1, tile2;

        switch (entity.getDirection()) {
            case "up":
                TopRow = (entityTopWorldY - entity.getSpeed()) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][TopRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
            break;
            case "down":
                BottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][BottomRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
            break;
            case "left":
                LeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[LeftCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
            break;
            case "right":
                RightCol = (entityRightWorldX + entity.getSpeed()) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[RightCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
//                if (Objects.equals(entity.getName(), "Guard")) {
//                    System.out.println("guard entity.getY() is " + entity.getY());
//                    System.out.println("guard entity.getSolidArea().y is " + entity.getSolidArea().y);
//                    System.out.println("guard entity.getSolidArea().height is " + entity.getSolidArea().height);
//                    System.out.println("RightCol is " + RightCol);
//                    System.out.println("TopRow is " + TopRow);
//                    System.out.println("BottomRow is " + BottomRow);
//                    System.out.println("[RightCol][TopRow] is " + gp.tileManage.tile[tile1].collision);
//                    System.out.println("[RightCol][BottomRow] is " + gp.tileManage.tile[tile2].collision);
//                }
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
     * @param inmate passing entity to charge as a parameter
     * @return       index of the object if anyone collides with inmate, or 999
     */
    public int checkObject(MovingActor inmate) {

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++){
            if (gp.obj[i] != null){

                //Get inmate's solid area position
                inmate.getSolidArea().x = inmate.getX() + inmate.getSolidArea().x;
                inmate.getSolidArea().y = inmate.getY() + inmate.getSolidArea().y;
                //Get object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].x + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].y + gp.obj[i].solidArea.y;

                switch (inmate.getDirection()) {
                    case "up" : {
                        inmate.getSolidArea().y -= inmate.getSpeed();
                        if (inmate.getSolidArea().intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                inmate.collision = true;
                            }
                            index = i;
                        }
                    }
                    case "down" : {
                        inmate.getSolidArea().y += inmate.getSpeed();
                        if (inmate.getSolidArea().intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                inmate.collision = true;
                            }
                            index = i;
                        }
                    }
                    case "left" : {
                        inmate.getSolidArea().x -= inmate.getSpeed();
                        if (inmate.getSolidArea().intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                inmate.collision = true;
                            }
                            index = i;
                        }
                    }
                    case "right" : {
                        inmate.getSolidArea().x += inmate.getSpeed();
                        if (inmate.getSolidArea().intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                inmate.collision = true;
                            }
                            index = i;
                        }
                    }
                }

                inmate.getSolidArea().x = inmate.getSolidAreaDefaultX();
                inmate.getSolidArea().y = inmate.getSolidAreaDefaultY();
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
