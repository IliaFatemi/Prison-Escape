package com.group10.app.menu;

import com.group10.app.main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class CreditsMenu extends Menu{

    private BufferedImage returnMenu, CreditsTemplate, gameTitle;

    public CreditsMenu(GamePanel gp){
        super(gp);
        registerMenuGraphics();
    }
    
    public void registerMenuGraphics(){
        try{
            gameTitle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/PrisonEscapeTitle.png")));
            CreditsTemplate = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/creditsBackground.png")));
            returnMenu = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/returnToMenuBtn.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void renderMenu(Graphics2D g2){
        g2.drawImage(CreditsTemplate, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(gameTitle, gp.screenWidth/2 - 211, gp.screenHeight/2 - 400, 423, 57, null);
        g2.drawImage(returnMenu, gp.screenWidth/2-103, gp.screenHeight/2 + 350, 206, 70, null);
    }
}
