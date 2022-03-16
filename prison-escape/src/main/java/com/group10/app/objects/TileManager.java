package com.group10.app.objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.FileInputStream;  


import javax.imageio.ImageIO;
import com.group10.app.main.GamePanel;
import com.group10.app.main.UtilityTool;

import java.awt.*;

public class TileManager {
    GamePanel gp;
    public Tiles[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tiles[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        registerImage();
        loadMap("/levels/Level1.txt");
    }

    public void registerImage(){

        // Placeholder
        setup(0, "ConcreteBlock", false);
        setup(1, "ConcreteBlock", false);
        setup(2, "ConcreteBlock", false);
        setup(3, "ConcreteBlock", false);
        setup(4, "ConcreteBlock", false);
        setup(5, "ConcreteBlock", false);
        setup(6, "ConcreteBlock", false);
        setup(7, "ConcreteBlock", false);
        setup(8, "ConcreteBlock", false);
        setup(9, "ConcreteBlock", false);
        // Placeholder

        setup(10, "ConcreteBlock", false);
        setup(11, "AsphaltGround", false);
        setup(12, "GravelGround", false);
        setup(13, "TileGround", false);
        setup(14, "WoodenGround", false);
        setup(15, "horizontalWall", true);
        setup(16, "verticalWall", true);
        setup(17, "CeramicGround", false);
        setup(18, "cell2", true);
        setup(19, "toilet", true);
        setup(20, "bed", true);
        setup(21, "exit1", true);
        setup(22, "exit2", true);
        setup(23, "exit3", true);
        setup(24, "exit4", true);
        setup(25, "exit5", true);

        /*
        try{
            //registering 

            //Concrete texture 
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ConcreteBlock.png"));

            //AsphaltGround texture
            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/AsphaltGround.png"));

            //Gravel texture 
            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GravelGround.png"));

            //Tile texture 
            tile[3] = new Tiles();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/TileGround.png"));

            //Wooden texture 
            tile[4] = new Tiles();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/WoodenGround.png"));

            //horizontal wall texture 
            tile[5] = new Tiles();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/horizontalWall.png"));
            tile[5].collision = true;

            //vertical wall texture 
            tile[6] = new Tiles();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/verticalWall.png"));
            tile[6].collision = true;

            //ceramic ground texture
            tile[7] = new Tiles();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/CeramicGround.png"));

            //prison cell block
            tile[8] = new Tiles();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cell2.png"));
            tile[8].collision = true;

            //toilet
            tile[9] = new Tiles();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/toilet.png"));
            tile[9].collision = true;

            //bed
            tile[10] = new Tiles();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bed.png"));
            tile[10].collision = true;

            //exit gate
            tile[11] = new Tiles();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/exit1.png"));
            tile[11].collision = true;

            tile[12] = new Tiles();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/exit2.png"));
            tile[12].collision = true;

            tile[13] = new Tiles();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/exit3.png"));
            tile[13].collision = true;

            tile[14] = new Tiles();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/exit4.png"));
            tile[14].collision = true;

            tile[15] = new Tiles();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/exit5.png"));
            tile[15].collision = true;
             

        } catch(IOException e){
            e.printStackTrace();
        }
        */
    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tiles();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.cellSize, gp.cellSize);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //Loading level
    public void loadMap(String filePath){
        try{
            InputStream level = getClass().getResourceAsStream(filePath);
            assert level != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(level));
            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col  = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception ignored){

        }
    }

    //Draw the object
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow <gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.cellSize;
            int worldY = worldRow * gp.cellSize;
            int screenX = worldX - gp.inmate.worldX + gp.inmate.screenX;
            int screenY = worldY - gp.inmate.worldY + gp.inmate.screenY;


            //Stop moving the camera at the edge
            if (gp.inmate.screenX > gp.inmate.worldX){
                screenX = worldX;
            }
            if (gp.inmate.screenY > gp.inmate.worldY) {
                screenY = worldY;
            }

            int rightOffset = gp.screenWidth - gp.inmate.screenX;
            if (rightOffset > gp.worldWidth - gp.inmate.worldX){
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }

            int bottomOffset = gp.screenHeight - gp.inmate.screenY;
            if (bottomOffset > gp.worldHeight - gp.inmate.worldY){
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            if (worldX + gp.cellSize > gp.inmate.worldX - gp.inmate.screenX &&
                    worldX - gp.cellSize < gp.inmate.worldX + gp.inmate.screenX &&
                    worldY + gp.cellSize > gp.inmate.worldY - gp.inmate.screenY &&
                    worldY - gp.cellSize < gp.inmate.worldY + gp.inmate.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            }
            else if (gp.inmate.screenX > gp.inmate.worldX ||
                    gp.inmate.screenY > gp.inmate.worldY ||
                    rightOffset > gp.worldWidth - gp.inmate.worldX ||
                    bottomOffset > gp.worldHeight - gp.inmate.worldY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}