package com.group10.app.menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import com.group10.app.main.GamePanel;

/**
 * MenuScreen will create an object for the main menu screen.
 * @author Ilia Fatemi
 */
public class MenuScreen{

    /**
     * Game panel will be used to access it's methods and variables
     */
    GamePanel gp;

    /**
     * newGame: will register the return to menu button picture
     * continue_: will register the retry button picture
     * quitGame: will register the gameover display template
     */
    public BufferedImage newGame, continue_, quitGame, gameTitle, backgroundImg;

    /**
     * <p>The contructor for MenuScreen will setup the registered images</p>
     * @param gp the GamePanel object
     */
    public MenuScreen(GamePanel gp){
        this.gp = gp;
        registerMenuGraphics();
    }

    /**
     * <p>Registering the images for the background, game title, new game button, continue button, and quit game button.</p>
     */
    public void registerMenuGraphics(){
        try{
            gameTitle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/PrisonEscapeTitle.png")));
            newGame = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/newGameBtn.png")));
            continue_ = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/continueBtn.png")));
            quitGame = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/quitGameBtn.png")));
            backgroundImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/backgroundMenuImg.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * <p>Display the graphics onto the screen. This method will display the baground image, game title, new game button,
     * continue button and quit game button.</p>
     * @param g2 using the Graphics2D to draw the registered images onto the display
     */
    public void renderMain(Graphics2D g2){
        g2.drawImage(backgroundImg, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(gameTitle, gp.screenWidth/2 - 211, 28, 423, 57, null);
        g2.drawImage(newGame,  gp.screenWidth/2-103, gp.screenHeight/2 - 300, 206, 70, null);
        g2.drawImage(continue_, gp.screenWidth/2-103, gp.screenHeight/2 - 200, 206, 70, null);
        g2.drawImage(quitGame, gp.screenWidth/2-103, gp.screenHeight/2 - 100, 206, 70, null);
    }
}