package com.group10.app.main;

import com.group10.app.entity.Entity;

public class Collision {
    GamePanel gp;

    public Collision (GamePanel gp){
        this.gp = gp;

    }

    public void wallCheck(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

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

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++){
            if (gp.obj[i] != null){

                //Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                nextSolidArea(entity);

                if (entity.solidArea.intersects(gp.obj[i].solidArea)){
                    if (gp.obj[i].collision){
                        entity.collision = true;
                    }
                    if (player){
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        //Get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //Get the object's solid area position
        gp.inmate.solidArea.x = gp.inmate.worldX + gp.inmate.solidArea.x;
        gp.inmate.solidArea.y = gp.inmate.worldY + gp.inmate.solidArea.y;

        nextSolidArea(entity);

        if (entity.solidArea.intersects(gp.inmate.solidArea)){
            entity.collision = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.inmate.solidArea.x = gp.inmate.solidAreaDefaultX;
        gp.inmate.solidArea.y = gp.inmate.solidAreaDefaultY;

        return contactPlayer;
    }

    private void nextSolidArea(Entity entity) {
        switch (entity.direction) {
            case "up" -> entity.solidArea.y -= entity.speed;
            case "down" -> entity.solidArea.y += entity.speed;
            case "left" -> entity.solidArea.x -= entity.speed;
            case "right" -> entity.solidArea.x += entity.speed;
        }
    }

}
