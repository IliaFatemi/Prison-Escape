package com.group10.app.main;

import com.group10.app.entity.Entity;

public class Collision {
    GamePanel gp;

    public Collision (GamePanel gp){
        this.gp = gp;
    }

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
            case "up" -> {
                TopRow = (entityTopWorldY - entity.speed) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][TopRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
            }
            case "down" -> {
                BottomRow = (entityBottomWorldY - entity.speed) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][BottomRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
            }
            case "left" -> {
                LeftCol = (entityLeftWorldX + entity.speed) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[LeftCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[LeftCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
            }
            case "right" -> {
                RightCol = (entityRightWorldX - entity.speed) / gp.cellSize;
                tile1 = gp.tileManage.mapTileNum[RightCol][TopRow];
                tile2 = gp.tileManage.mapTileNum[RightCol][BottomRow];
                if (gp.tileManage.tile[tile1].collision || gp.tileManage.tile[tile2].collision) {
                    entity.collision = true;
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean inmate) {

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
                            if (inmate) {
                                index = i;
                            }
                        }
                    }
                    case "down" : {
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collision = true;
                            }
                            if (inmate) {
                                index = i;
                            }
                        }
                    }
                    case "left" : {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collision = true;
                            }
                            if (inmate) {
                                index = i;
                            }
                        }
                    }
                    case "right" : {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collision = true;
                            }
                            if (inmate) {
                                index = i;
                            }
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
}
