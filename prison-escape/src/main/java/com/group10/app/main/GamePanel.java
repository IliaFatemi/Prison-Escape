package com.group10.app.main;

import com.group10.app.entity.Inmate;
import com.group10.app.entity.Gaurd;
import com.group10.app.objects.TileManager;
import com.group10.app.objects.WallManager;

import javafx.scene.input.KeyEvent;

import com.group10.app.MenuPanel.MenuScreen;
import com.group10.app.MenuPanel.PauseMenu;

import static java.lang.Math.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
   
    // Screen size settings
    final int originalCellSize = 16;
    final int scaleFactor = 3;
    public final int cellSize = originalCellSize * scaleFactor; //48x48 cells
    public final int screenColNumber = 20;
    public final int screenRowNumber = 14;
    public final int screenWidth = cellSize * screenColNumber;//960 pixels
    public final int screenHeight = cellSize * screenRowNumber;//672 pixels

    final int framePerSecond = 60;

    //The distance where the player and enemy will colide at
    int ENEMY_COLLISION_DISTANCE = 40;

    //Set up the keyboard keys
    KeyManager keyH = new KeyManager();

    //Set up the Mouse Keys
    MouseManager mouseK = new MouseManager(this);

    Thread gameThread;

    //setup the tiles
    TileManager tileManage = new TileManager(this);

    //setup the walls
    WallManager wallmanager = new WallManager(this);

    //set player default position
    Inmate inmate = new Inmate(this, keyH);
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    //set gaurds position
    Gaurd gaurd = new Gaurd(this, 200, 200);

    //Set up the main menu screen 
    MenuScreen mainMenu = new MenuScreen(this);

    //set up the pause menu
    PauseMenu pauseMenu = new PauseMenu(this, keyH);

    public static enum STATE{MENU, GAME, EXIT, PAUSED}
    public static STATE state = STATE.MENU;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseK);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
            gameThread = new Thread(this);
            gameThread.start();
    }

    
    //Setting up collision between two objects.This function takes two perameters as turtles. 
    //Input: (Inmate:Obj, ObjectX: int, ObjectY: int: collision_type:int)   
    public boolean isCollision(Inmate inmate, double objectX, double objectY, int collision_type){   
        double distance = sqrt(pow(inmate.getX() - objectX, 2) + pow(inmate.getY() - objectY, 2));
        //System.out.println(distance);
            if (distance <= collision_type){
                return true;
            }
            return false;
    }

    //Checking the boundary for the player
    //If player steps out of the screen area, the function will return true
    public boolean isBoundary(Inmate inmate){
        if(inmate.getX() > screenWidth-2*cellSize || inmate.getX() < 0 + cellSize || inmate.getY() > screenHeight-2*cellSize || inmate.getY() < 0 + cellSize){
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

            //Testing for collision detection with a gaurd
            if (isCollision(inmate, gaurd.getX(), gaurd.getY(), ENEMY_COLLISION_DISTANCE)){
                System.out.println("ENEMY COLLIDED");
                System.out.println("===================================");
            }

            //Testing collision with border boundary
            if (isBoundary(inmate)){
                System.out.println("BOUNDARY COLLLISION");
                System.out.println("===================================");
                inmate.revertPosition(inmate.getDirection());
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
        
        if (state == STATE.GAME){
    
            //draw tiles
            tileManage.draw(g2);
    
            //draw walls
            wallmanager.drawBoarder(g2);
    
            //Draw gaurd
            gaurd.draw(g2, this);
    
            //Draw the inmate
            inmate.draw(g2);
    
            g2.dispose();
            
        }
        else if(state == STATE.PAUSED){
            pauseMenu.renderPauseMenu(g2);
            g2.dispose();
        }
        else{
            //Render the main menu
            mainMenu.renderMain(g2);
            g2.dispose();
        }
    }
}