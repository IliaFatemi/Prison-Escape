package com.group10.app.menu;

import com.group10.app.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ErrorMenu extends Menu{

    private BufferedImage returnToMenuBtn, backgroundImg, gameTitle;

    ErrorMenu(GamePanel gp){
        super(gp);
        registerMenuGraphics();
    }

    /**
     * <p>Registering the images for the background, game title, new game button, continue button, and quit game button.</p>
     */
    public void registerMenuGraphics(){
        try{
            backgroundImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/loadError.png")));
            gameTitle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/PrisonEscapeTitle.png")));
            returnToMenuBtn = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/returnToMenuBtn.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * <p>Display the graphics onto the screen. This method will display the baground image, game title, new game button,
     * continue button and quit game button.</p>
     * @param g2 using the Graphics2D to draw the registered images onto the display
     */
    public void renderMenu(Graphics2D g2){
        g2.drawImage(backgroundImg, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(gameTitle, gp.screenWidth/2 - 211, gp.screenHeight/2 - 400, 423, 57, null);
        g2.drawImage(returnToMenuBtn, gp.screenWidth/2-103, gp.screenHeight/2 + 350, 206, 70, null);
    }
}
