package com.group10.app.entity.staticEntities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import javax.imageio.ImageIO;
import com.group10.app.main.GamePanel;

import java.awt.*;
import java.util.Objects;

/**
 * This class handles the various items that appear on a map as barriers, paths or
 * furniture.
 * <p>
 *     TileManager handles background items, registers sizes, sets filepath, loads the
 *     respective map for each level and draws/places objects onto the map.
 * </p>
 */
public class TileManager {
    GamePanel gp;
    public Tiles[] tile;
    public int[][] mapTileNum;

    /**
     * Constructor method to assign initial valies to TileManagar's variables.
     * <p>
     *     Assigns the background items to an array for easier access, storage and organization.
     *     Creates a 2D grid of background objects and sets out the map using mapTileNum.
     * </p>
     * @param gp
     */
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tiles[56];
        mapTileNum = new int[gp.screenColNumber][gp.screenRowNumber];
        registerImage();
    }

    /**
     * Assigns a number to each type of item for easier visualization and defines their
     * opaqueness/interactability using the collision variable.
     * <p>
     *     Mainly used for associating a number to each item in order to identify each item
     *     and set their interactability to either true or false, true being that the object
     *     cannot be passed through and false being that the object can be walked through.
     * </p>
     */
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
        setup(31, "TUp", true);
        setup(32, "TDown", true);
        setup(33, "TRight", true);
        setup(34, "TLeft", true);
        setup(35, "4WayWall", true);
        setup(36, "desk1", true);
        setup(37, "desk2", true);
        setup(38, "chair", true);
        setup(39, "RockGround", false);
        setup(40, "creamTile", false);
        setup(41, "stove1", true);
        setup(42, "stove2", true);
        setup(43, "showerDown", true);
        setup(44, "showerUp", true);
        setup(45, "freezer1", true);
        setup(46, "freezer2", true);
        setup(47, "box", true);
        setup(48, "bigCell1", true);
        setup(49, "bigCell2", true);
        setup(50, "couch", true);
        setup(51, "sink1", true);
        setup(52, "sink2", true);
        setup(53, "gunStash1", true);
        setup(54, "gunStash2", true);
        setup(55, "locker", true);
    }

    /**
     * Sets up the respective images for each item and scales their size accordingly.
     * <p>
     *     setup method assigns the filepath to each item along with their interactability
     *     and their sizes. This helps define the characteristic of each type of object by either
     *     making them behave as a barrier or a path.
     * </p>
     * @param index         stores the unique number associated with each type of object.
     * @param imageName     used to assign the filepath depending upon the filename of the specific object.
     * @param collision     boolean variable to make an object behave as either a component of a barrier or a path.
     */
    public void setup(int index, String imageName, boolean collision) {

        try {
            tile[index] = new Tiles();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Loads the associated map with each level.
     * <p>
     *     Processes a text file containing a 2D mapping of the numbers associated with each item,
     *     to generate another 2D map of tile numbers. Contains a loop that loops through row by
     *     row, assigning the numbers to the tile number array.
     * </p>
     * @param filePath      Contains the filepath for each item's image.
     */
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

        }catch(Exception ignored){}
    }

    /**
     * This method places the image of each item onto the generated map.
     * <p>
     *     Contains a loop that runs through the 2D tile number array to place the
     *     assigned images of each item onto the map. It accomodates for the size of
     *     each image as well in order to create a viewable aesthetic map.
     * </p>
     * @param g2
     */
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