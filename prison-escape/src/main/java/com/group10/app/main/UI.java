package com.group10.app.main;

import com.group10.app.objects.ObjKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI (GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
//        arial_80B = new Font("Arial", Font.BOLD, 40);
//        ObjKey key = new ObjKey();
//        keyImage = key.image;
    }

    public void showMessage (String text) {
        message = text;
        messageOn = true;
    }

    public void draw (Graphics2D g2) {

        if (gameFinished) {

//            g2.setFont(arial_40);
//            g2.setColor(Color.white);
//
//            String text;
//            int textLength;
//            int x;
//            int y;
//
//            text = "You found the treasure!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 - (gp.tileSize*3);
//            g2.drawString(text, x, y);
//
//            text = "You time is: " + dFormat.format(playTime) + "!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + (gp.tileSize*4);
//            g2.drawString(text, x, y);
//
//            g2.setFont(arial_80B);
//            g2.setColor(Color.yellow);
//
//            text = "Congratulations!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + (gp.tileSize*2);
//            g2.drawString(text, x, y);
//
//            gp.gameThread = null;

        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawString("Score: " + dFormat.format(gp.inmate.score), 74, 65);

            //Time
            gp.inmate.time -= (double) 1/60;
            g2.drawString("Time: " + dFormat.format(gp.inmate.time), gp.cellSize*11, 65);

//            //Message
//            if (messageOn){
//
//                g2.setFont((g2.getFont().deriveFont(30F)));
//                g2.drawString(message, gp.cellSize/2, gp.tileSize*5);
//
//                messageCounter++;
//
//                if (messageCounter > 120){
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
        }
    }
}
