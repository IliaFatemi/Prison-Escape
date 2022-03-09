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

public class TileManager {
    GamePanel gp;
    Tiles[] tile;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tiles[10];
        registerImage();
    }

    public void registerImage(){
        try{
            //Creating 5 different tiles
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
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        //Fill out the whole screen with concrete tiles
        int nextCol = 0, nextRow = 0;
        for(int row = 0; row < gp.cellSize; row++){
            for(int col = 0; col < gp.cellSize; col++){
                g2.drawImage(tile[0].image, nextCol, nextRow, gp.cellSize, gp.cellSize, null);
                nextCol += gp.cellSize;
            }
            nextRow += gp.cellSize;
            nextCol = 0;
        }
    }
}
