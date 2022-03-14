package com.group10.app.MenuPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import com.group10.app.main.GamePanel;
import com.group10.app.main.KeyManager;

public class WonMenu {
    GamePanel gp;
    KeyManager keyH;

    public BufferedImage returnMenu, gameWonTemp, nextLevel;

    public WonMenu(GamePanel gp, KeyManager keyH){
        this.gp = gp;
        this.keyH = keyH;
        getGameWonGraphics();
    }

    public void getGameWonGraphics(){
        try{
            gameWonTemp = ImageIO.read(getClass().getResourceAsStream("/menu/gameWon.png"));
            returnMenu = ImageIO.read(getClass().getResourceAsStream("/menu/returnToMenuBtn.png"));
            nextLevel = ImageIO.read(getClass().getResourceAsStream("/menu/nextLevelBtn.png"));

            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void renderWonGraphics(Graphics2D g2){
        g2.drawImage(gameWonTemp,  0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(nextLevel,  gp.screenWidth/2 - 103, gp.screenHeight/2 - 130, 206, 70, null);
        g2.drawImage(returnMenu, gp.screenWidth/2-103, gp.screenHeight/2 - 30, 206, 70, null);
    }
}
