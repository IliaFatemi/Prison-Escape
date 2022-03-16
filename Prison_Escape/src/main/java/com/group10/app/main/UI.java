package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.objects.ObjKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    BufferedImage keyImage;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
//    public String currentDialogue = "";
//    public int commandNum = 0;
//    public int titleScreenState = 0; //0: the first screen, 1: the second screen

    DecimalFormat dFormat = new DecimalFormat("#0.0");

    public UI (GamePanel gp) {

        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);

        Entity key = new ObjKey(gp);
        keyImage = key.down1;
    }

    public void addMessage (String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw (Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //Play state
        if (GamePanel.state == GamePanel.STATE.GAME){
            drawScoreTimeKey();
            drawMessage();
        }

    }

    public void drawScoreTimeKey() {

        // Draw the Score
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawString("Score: " + dFormat.format(gp.inmate.score), gp.cellSize, gp.cellSize);

        // Draw the Time
        gp.inmate.time -= (double) 1/60;
        g2.drawString("Time: " + dFormat.format(gp.inmate.time), gp.cellSize * 11, gp.cellSize);

        // Draw the Key
        g2.drawImage(keyImage, gp.cellSize * 21, gp.cellSize - 32, null);
        g2.drawString("X " + gp.inmate.hasKey, gp.cellSize * 22, gp.cellSize);

    }

    public void drawMessage() {
        int messageX = gp.cellSize;
        int messageY = gp.cellSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for (int i = 0; i < message.size(); i++){

            if (message.get(i) != null){

                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; //messageCounter++
                messageCounter.set(i, counter); //Set the counter to the array
                messageY += 50;

                if (messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
}
