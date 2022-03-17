package com.group10.app.menu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

import com.group10.app.main.GamePanel;
import com.group10.app.main.KeyManager;

public class WonMenu {
    GamePanel gp;
    KeyManager keyH;
    DecimalFormat dFormat = new DecimalFormat("#0.0");

    public BufferedImage returnMenu, gameWonTemp, nextLevel;

    public WonMenu(GamePanel gp, KeyManager keyH){
        this.gp = gp;
        this.keyH = keyH;
        getGameWonGraphics();
    }

    public void getGameWonGraphics(){
        try{
            gameWonTemp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/gameWon.png")));
            returnMenu = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/returnToMenuBtn.png")));
            nextLevel = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/menu/nextLevelBtn.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void renderWonGraphics(Graphics2D g2){
        g2.drawImage(gameWonTemp,  0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(nextLevel,  gp.screenWidth/2 - 103, gp.screenHeight/2 - 130, 206, 70, null);
        g2.drawImage(returnMenu, gp.screenWidth/2-103, gp.screenHeight/2 - 30, 206, 70, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
        g2.drawString(String.valueOf(gp.inmate.score), gp.cellSize * 24, (int) (gp.cellSize * 5.8));
        g2.drawString(String.valueOf(dFormat.format(gp.inmate.time)), gp.cellSize * 24, (int) (gp.cellSize * 7.5));

    }
}
