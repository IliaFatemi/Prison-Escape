package com.group10.app.MenuPanel;

import java.awt.event.MouseAdapter;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import com.group10.app.main.GamePanel;

public class MenuScreen extends MouseAdapter {

    GamePanel gp;

    //Screen graphics 
    public BufferedImage newGame, continue_, quitGame, gameTitle, backgroundImg;

    public MenuScreen(GamePanel gp){
        this.gp = gp;
        getMenuGraphics();
    }

    public void getMenuGraphics(){
        try{
            gameTitle = ImageIO.read(getClass().getResourceAsStream("/menuInterface/PrisonEscapeTitle.png"));
            newGame = ImageIO.read(getClass().getResourceAsStream("/menuInterface/newGameBtn.png"));
            continue_ = ImageIO.read(getClass().getResourceAsStream("/menuInterface/continueBtn.png"));
            quitGame = ImageIO.read(getClass().getResourceAsStream("/menuInterface/quitGameBtn.png"));
            backgroundImg = ImageIO.read(getClass().getResourceAsStream("/menuInterface/backgroundMenuImg.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void renderMain(Graphics2D g2){
        g2.drawImage(backgroundImg, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(gameTitle, gp.screenWidth/2 - 211, 28, 423, 57, null);
        g2.drawImage(newGame,  gp.screenWidth/2-103, gp.screenHeight/2 - 300, 206, 70, null);
        g2.drawImage(continue_, gp.screenWidth/2-103, gp.screenHeight/2 - 200, 206, 70, null);
        g2.drawImage(quitGame, gp.screenWidth/2-103, gp.screenHeight/2 - 100, 206, 70, null);
    }
}