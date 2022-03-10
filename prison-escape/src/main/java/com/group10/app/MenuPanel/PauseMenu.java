package com.group10.app.MenuPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import com.group10.app.main.GamePanel;
import com.group10.app.main.KeyManager;

public class PauseMenu {
    GamePanel gp;
    KeyManager keyH;

    //Screen graphics 
    public BufferedImage resume, saveGame, returnMenu, pauseTemplate;

    public PauseMenu(GamePanel gp, KeyManager keyH){
        this.gp = gp;
        this.keyH = keyH;
        getPausedGraphics();
    }

    public void resume(){
        keyH.pressedEscape = false;
    }

    public void getPausedGraphics(){
        try{
            resume = ImageIO.read(getClass().getResourceAsStream("/menuInterface/resumeBtn.png"));
            saveGame = ImageIO.read(getClass().getResourceAsStream("/menuInterface/saveGameBtn.png"));
            returnMenu = ImageIO.read(getClass().getResourceAsStream("/menuInterface/returnToMenuBtn.png"));
            pauseTemplate = ImageIO.read(getClass().getResourceAsStream("/menuInterface/pausedTemplate.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void renderPauseMenu(Graphics2D g2){
        g2.drawImage(pauseTemplate, gp.screenWidth/2-350, gp.screenHeight/2-250, 700, 500, null);
        g2.drawImage(resume, gp.screenWidth/2 - 103, gp.screenHeight/2 - 130, 206, 70, null);
        g2.drawImage(saveGame,  gp.screenWidth/2-103, gp.screenHeight/2 - 30, 206, 70, null);
        g2.drawImage(returnMenu, gp.screenWidth/2-103, gp.screenHeight/2 + 70, 206, 70, null);
    }
}
