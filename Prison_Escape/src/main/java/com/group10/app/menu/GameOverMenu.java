package com.group10.app.menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import com.group10.app.main.GamePanel;

public class GameOverMenu {
    GamePanel gp;

    public BufferedImage returnMenu, gameOverTemplate, retry;

    public GameOverMenu(GamePanel gp){
        this.gp = gp;
        getGameOverGraphics();
    }

    public void getGameOverGraphics(){
        try{
            gameOverTemplate = ImageIO.read(getClass().getResourceAsStream("/menu/gameOver.png"));
            retry = ImageIO.read(getClass().getResourceAsStream("/menu/retryBtn.png"));
            returnMenu = ImageIO.read(getClass().getResourceAsStream("/menu/returnToMenuBtn.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void renderGameOverMenu(Graphics2D g2){
        g2.drawImage(gameOverTemplate, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(retry, gp.screenWidth/2 - 103, gp.screenHeight/2 - 130, 206, 70, null);
        g2.drawImage(returnMenu, gp.screenWidth/2-103, gp.screenHeight/2 - 30, 206, 70, null);
    }
}
