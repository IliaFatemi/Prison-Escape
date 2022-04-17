package com.group10.app.menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import com.group10.app.main.GamePanel;

/**
 * PauseMenu will create an object for the pause menue screen.
 * @author Ilia Fatemi
 */
public class PauseMenu extends Menu{

    /**
     * resume: will register the resume button
     * returnMenu: will register the return to main menu button
     * pauseTemplate: will register the background image
     */ 
    private BufferedImage resume, returnMenu, pauseTemplate;

    /**
     * <p>The contructor for PauseMenu will setup the registered images</p>
     * @param gp the GamePanel object
     */
    public PauseMenu(GamePanel gp){
        super(gp);
        registerMenuGraphics();

    }

    /**
     * <p>Registering the images for the background, resume button, save game button, return to main menu button.</p>
     */
    public void registerMenuGraphics(){
        try{
            resume = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/resumeBtn.png")));
            returnMenu = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/returnToMenuBtn.png")));
            pauseTemplate = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/ui_pause_title.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * <p>Display the graphics onto the screen. This method will display the baground image, resume button, save game button, return to main menu button.</p>
     * @param g2 using the Graphics2D to draw the registered images onto the display
     */
    public void renderMenu(Graphics2D g2){
        g2.drawImage(pauseTemplate, gp.screenWidth/2-140, gp.screenHeight/2-250, 280, 70, null);
        g2.drawImage(resume, gp.screenWidth/2 - 103, gp.screenHeight/2 - 130, 206, 70, null);
        g2.drawImage(returnMenu, gp.screenWidth/2-103, gp.screenHeight/2 - 30, 206, 70, null);
    }
}