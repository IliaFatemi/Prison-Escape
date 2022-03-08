package com.group10.app.main;

import com.group10.app.entity.Inmate;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Screen settings

    final int originalCellSize = 16;
    final int scaleFactor = 3;

    public final int cellSize = originalCellSize * scaleFactor; //48x48 cells
    final int screenColNumber = 20;
    final int screenRowNumber = 14;
    final int screenWidth = cellSize * screenColNumber;
    final int screenHeight = cellSize * screenRowNumber;

    final int framePerSecond = 60;

    KeyManager keyH = new KeyManager();
    Thread gameThread;
    Inmate inmate = new Inmate(this, keyH);
    //set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void run() {
        double drawInterval = 1000000000/ framePerSecond;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
        inmate.update();
    }

    public void paintComponent(Graphics graphic){
        super.paintComponent(graphic);
        Graphics2D g2 = (Graphics2D) graphic;

        inmate.draw(g2);

        g2.dispose();
    }
}
