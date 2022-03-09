package com.group10.app.main;

import com.group10.app.entity.Inmate;
import com.group10.app.entity.Gaurd;
import static java.lang.Math.*;

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

    //set gaurds position
    Gaurd gaurd = new Gaurd(this, 200, 200);

    //The distance where the player and enemy will colide at
    int COLLISION_DISTANCE = 40;


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


    
    //Setting up collision between two objects.This function takes two perameters as turtles.    
    public boolean isCollision(Inmate inmate, Gaurd gaurd){   
        double distance = sqrt(pow(inmate.getX() - gaurd.getX(), 2) + pow(inmate.getY() - gaurd.getY(), 2));
            if (distance <= COLLISION_DISTANCE){
                return true;
            }
            return false;
    }

    public void run() {
        double drawInterval = 1000000000/ framePerSecond;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            update();
            repaint();
            
            //Testing for collision detection
            if (isCollision(inmate, gaurd)){
                System.out.println("COLLIDED");
                System.out.println("===========colision========================");
            }

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

        gaurd.draw(g2, this);
        inmate.draw(g2);

        g2.dispose();
    }
}
