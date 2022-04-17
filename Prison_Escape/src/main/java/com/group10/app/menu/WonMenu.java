package com.group10.app.menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

import com.group10.app.main.GamePanel;


/**
 * WonMenu will create an object for win menu.
 * @author Ilia Fatemi
 */
public class WonMenu extends Menu{

    /**
     * Creates a DecimalFormat using the given pattern and the symbols for the default FORMAT locale.
     */
    DecimalFormat dFormat = new DecimalFormat("#0.0");

    /**
     * nextLevel: will register the next level button
     * returnMenu: will register the return to main menu button
     * gameWonTemp: will register the background image
     */ 
    private BufferedImage returnMenu, gameWonTemp, nextLevel;

    /**
     * <p>The contractor for WonMenu will setup the registered images</p>
     * @param gp the GamePanel object
     */
    public WonMenu(GamePanel gp){
        super(gp);
        registerMenuGraphics();

    }

    /**
     * <p>Registering the images for the background, next level button, return to main menu button.</p>
     */
    public void registerMenuGraphics(){
        try{
            gameWonTemp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/gameWon.png")));
            returnMenu = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/returnToMenuBtn.png")));
            nextLevel = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/nextLevelBtn.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * <p>Registering the images for the background, next level button, return to main menu button.
     * This method will also display the time the player finished the level at and will display the score.</p>
     */
    public void renderMenu(Graphics2D g2){
        g2.drawImage(gameWonTemp,  0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(nextLevel,  gp.screenWidth/2 - 103, gp.screenHeight/2 - 130, 206, 70, null);
        g2.drawImage(returnMenu, gp.screenWidth/2-103, gp.screenHeight/2 - 30, 206, 70, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
        g2.drawString(String.valueOf(gp.inmate.getScore()), gp.cellSize * 24, (int) (gp.cellSize * 5.8));
        g2.drawString(String.valueOf(dFormat.format(gp.inmate.getTimer())), gp.cellSize * 24, (int) (gp.cellSize * 7.5));

    }
}