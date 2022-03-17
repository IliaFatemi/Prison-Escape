package com.group10.app.objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
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
        tile = new Tiles[31];
        mapTileNum = new int[gp.screenColNumber][gp.screenRowNumber];
        registerImage();
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
        setup(26, "cell1", true);
        setup(27, "cornerTopLeft", true);
        setup(28, "cornerTopRight", true);
        setup(29, "cornerBottomLeft", true);
        setup(30, "cornerBottomRight", true);
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

            while(col < gp.screenColNumber && row < gp.screenRowNumber){
                String line = br.readLine();
                while(col < gp.screenColNumber){
                    String[] numbers = line.split(" ");
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

        }catch(Exception ignored){

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