package com.group10.app.menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import com.group10.app.main.GamePanel;


/**
 * GameOverMenu will display a Menu screen when the player has made contact with the enemy or the timer has ran out.
 * @author Ilia Fatemi
 */
public class GameOverMenu extends Menu{

    /**
     * returnMenu: will register the return to menu button picture
     * retry: will register the retry button picture
     * gameOverTemplate: will register the gameover display template
     */
    private BufferedImage returnMenu, gameOverTemplate, retry;

    /**
     * <p>The contructor for GameOverMenu will setup the registered images</p>
     * @param gp the GamePanel object
     */
    public GameOverMenu(GamePanel gp){
        super(gp);
        registerMenuGraphics();

    }

    /**
     * <p>Registering images for game over background, return button, and return to main menu button.</p>
     */
    public void registerMenuGraphics(){
        try{
            gameOverTemplate = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/gameOver.png")));
            retry = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/retryBtn.png")));
            returnMenu = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/returnToMenuBtn.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * <p>Display the graphics onto the screen. This method will display the background image, retry button, return menu button.</p>
     * @param g2 using the Graphics2D to draw the registered images onto the display
     */
    public void renderMenu(Graphics2D g2){
        g2.drawImage(gameOverTemplate, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(retry, gp.screenWidth/2 - 103, gp.screenHeight/2 - 130, 206, 70, null);
        g2.drawImage(returnMenu, gp.screenWidth/2-103, gp.screenHeight/2 - 30, 206, 70, null);
    }
}