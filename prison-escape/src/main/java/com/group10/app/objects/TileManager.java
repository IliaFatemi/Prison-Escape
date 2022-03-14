package com.group10.app.objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.FileInputStream;  


import javax.imageio.ImageIO;
import com.group10.app.main.GamePanel;
import java.awt.*;

public class TileManager {
    GamePanel gp;
    public Tiles[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tiles[20];
        mapTileNum = new int[gp.screenColNumber][gp.screenRowNumber];
        registerImage();
        loadMap();
    }

    public void registerImage(){
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
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/decorate/cell2.png"));
            tile[8].collision = true;

            //toilet
            tile[9] = new Tiles();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/decorate/toilet.png"));
            tile[9].collision = true;

            //bed
            tile[10] = new Tiles();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/decorate/bed.png"));
            tile[10].collision = true;

            //exit gate
            tile[11] = new Tiles();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/decorate/exit1.png"));
            tile[11].collision = true;

            tile[12] = new Tiles();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/decorate/exit2.png"));
            tile[12].collision = true;

            tile[13] = new Tiles();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/decorate/exit3.png"));
            tile[13].collision = true;

            tile[14] = new Tiles();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/decorate/exit4.png"));
            tile[14].collision = true;

            tile[15] = new Tiles();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/decorate/exit5.png"));
            tile[15].collision = true;
             

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Loading level
    public void loadMap(){
        try{
            InputStream level = new FileInputStream("src/main/levels/Level1.txt");
            System.out.println(level);
            BufferedReader br = new BufferedReader(new InputStreamReader(level));
            int col = 0;
            int row = 0;

            while(col < gp.screenColNumber && row < gp.screenRowNumber){
                String line = br.readLine();
                while(col < gp.screenColNumber){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.screenColNumber){
                    col  = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){

        }
    }

    //Draw the object
    public void draw(Graphics2D g2){
        int nextCol = 0, nextRow = 0, x = 0, y = 0;
        while(nextCol < gp.screenColNumber && nextRow < gp.screenRowNumber){
            int tileNum = mapTileNum[nextCol][nextRow];
            g2.drawImage(tile[tileNum].image, x, y, gp.cellSize, gp.cellSize, null);
            nextCol++;
            x += gp.cellSize;
            if(nextCol == gp.screenColNumber){
                nextCol = 0;
                x = 0;
                nextRow++;
                y += gp.cellSize;
            }

        }
    }
}