package com.group10.app.objects;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.group10.app.main.GamePanel;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import com.group10.app.main.GamePanel;

public class WallManager {
    GamePanel gp;
    Walls[] wall;

    public WallManager(GamePanel gp){
        this.gp = gp;
        wall = new Walls[10];
        registerImage();
    }

    //Register the images
    public void registerImage(){
        try{
            //horizontal wall texture 
            wall[0] = new Walls();
            wall[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/horizontalWall.png"));

            //vertical wall texture 
            wall[1] = new Walls();
            wall[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/verticalWall.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Draw a boarder across the screen
    public void drawBoarder(Graphics2D g2){

        int nextCol = 0, nextRow = 0;

        //boarder on the top of the screen
        for(int i = 0; i < gp.screenWidth; i++){
            g2.drawImage(wall[0].image, nextCol, nextRow, gp.cellSize, gp.cellSize, null);
            nextCol += gp.cellSize;
        }

        //boarder on the Left side of the screen
        nextCol = 0; nextRow = 0;
        for(int row = 0; row < gp.screenWidth; row++){
            for(int col = 0; col < gp.screenHeight; col++){
                if(col == gp.cellSize - (gp.cellSize-1)){
                    g2.drawImage(wall[1].image, col, nextRow, gp.cellSize, gp.cellSize, null);
                    
                }
            }
            nextRow += gp.cellSize;
        }

        //boarder on the bottom of the screen
        nextCol = 0; nextRow = 0;
        for(int row = 0; row < gp.screenWidth; row++){
            g2.drawImage(wall[0].image, nextCol, gp.screenHeight-gp.cellSize, gp.cellSize, gp.cellSize, null);
            nextCol += gp.cellSize;
        }

        //border on the right side of the screen
        nextCol = 0; nextRow = 0;
        for(int row = 0; row < gp.screenWidth; row++){
            for(int col = 0; col <gp.screenHeight; col++){
                nextCol+=gp.cellSize;
                if(nextCol == gp.screenWidth-gp.cellSize){
                    g2.drawImage(wall[1].image, nextCol, nextRow, gp.cellSize, gp.cellSize, null);
                }
            }
            nextRow+=gp.cellSize;
            nextCol = 0;
        }
    }
}
