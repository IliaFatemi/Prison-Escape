package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.entity.Inmate;
import com.group10.app.entity.Guard;

import com.group10.app.objects.TileManager;
import com.group10.app.menu.GameOverMenu;
import com.group10.app.menu.MenuScreen;
import com.group10.app.menu.PauseMenu;
import com.group10.app.menu.WonMenu;

import com.group10.app.SavedData.LoadGame;
import com.group10.app.SavedData.SaveGame;

import static java.lang.Math.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
   
    // Screen size settings
    final int originalCellSize = 12;
    final int scaleFactor = 4;
    public final int cellSize = originalCellSize * scaleFactor; //48x48 cells
    public final int screenColNumber = 30;
    public final int screenRowNumber = 18;
    public final int screenWidth = cellSize * screenColNumber;//1920 pixels
    public final int screenHeight = cellSize * screenRowNumber;//1080 pixels

    final int framePerSecond = 60;

    //The distance where the player and enemy will collide at
    int ENEMY_COLLISION_DISTANCE = 40;

    //The level the player is on
    int GAME_LEVEL = 1;
    Boolean GAME_SAVED = false;

    //Load saved data
    LoadGame load = new LoadGame();

    //set up save game
    SaveGame saveGame = new SaveGame();

    //Set up the keyboard keys
    KeyManager keyH = new KeyManager(this);

    Thread gameThread;
    
    //set up the tiles
    TileManager tileManage = new TileManager(this);
    
    //set player default position
    public Inmate inmate = new Inmate(this, keyH);

    //Set up the Mouse Keys
    MouseManager mouseK = new MouseManager(this);

    //set guards position
    Guard guard = new Guard(this, 200, 200);

    //Set up the main menu screen 
    MenuScreen mainMenu = new MenuScreen(this);

    //set up the pause menu
    PauseMenu pauseMenu = new PauseMenu(this, keyH);

    //set up the win screen
    WonMenu wonMenu = new WonMenu(this, keyH);

    //set up game over screen
    GameOverMenu gameOver = new GameOverMenu(this);
    
    // Create object array;
    public Entity[] obj = new Entity[30];
    
    // Set up asset;
    public AssetSetter asset = new AssetSetter(this);
    
    // Set up collision check;
    public Collision collisionCheck = new Collision(this);

    // Set up UI
    public UI ui = new UI(this);

    // Set up music and sound effect
    Sound music = new Sound();
    Sound soundEffect = new Sound();

    public static enum STATE{MENU, GAME, EXIT, PAUSED, GAMEOVER, GAMEWON, RETRY}
    public static STATE state = STATE.MENU;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseK);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpAsset() {
        asset.setObject();
        playMusic(0);
    }

    public void startGameThread(){
            gameThread = new Thread(this);
            gameThread.start();
    }

    
    //Setting up collision between two objects.This function takes two parameters as turtles.
    //Input: (Inmate:Obj, ObjectX: int, ObjectY: int: collision_type:int)   
    public boolean isCollision(Inmate inmate, double objectX, double objectY, int collision_type){   
        double distance = sqrt(pow(inmate.getX() - objectX, 2) + pow(inmate.getY() - objectY, 2));
        //System.out.println(distance);
        return distance <= collision_type;
    }

    public boolean reachedGate(){
        return inmate.getX() >= 1344 && inmate.getX() <= 1350 && inmate.getY() >= 292 && inmate.getY() <= 544;
    }

    public void run() {
        double drawInterval = 1000000000/ framePerSecond;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            
            //render graphics
            repaint();

            //Pause the game if pause menu is active
            update();
            if(state != STATE.PAUSED && state != STATE.MENU && state != STATE.GAMEWON && state != STATE.GAMEOVER){
                //Testing for collision detection with a guard
                if (isCollision(inmate, guard.getX(), guard.getY(), ENEMY_COLLISION_DISTANCE)){
                    System.out.println("ENEMY COLLIDED");
                    System.out.println("===================================");
                    state = STATE.GAMEOVER;
                }

                if(inmate.getNumKeys() == 3 && reachedGate()){
                    state = STATE.GAMEWON;
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
    }

    public void update(){
        inmate.update();
        asset.update();
    }

    public void paintComponent(Graphics graphic){
        super.paintComponent(graphic);
        Graphics2D g2 = (Graphics2D) graphic;
        
        if (state == STATE.GAME){
    
            //draw tiles
            tileManage.draw(g2);

            // Draw door

    
            // Draw objects
            for (Entity entity : obj) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
    
            //Draw guard
            guard.draw(g2);
    
            //Draw the inmate
            inmate.draw(g2);

            // Draw UI
            ui.draw(g2);

            g2.dispose();
        }
        else if(state == STATE.PAUSED){
            pauseMenu.renderPauseMenu(g2);
            g2.dispose();
        }
        else if (state == STATE.MENU){
            //Render the main menu
            mainMenu.renderMain(g2);
            g2.dispose();
        }
        else if (state == STATE.GAMEWON){
            //render the game won menu
            wonMenu.renderWonGraphics(g2);
            g2.dispose();
        }
        else if (state == STATE.GAMEOVER){
            //render game over menu
            gameOver.renderGameOverMenu(g2);
            g2.dispose();
        }
    }

    public void playMusic (int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic () {
        music.stop();
    }

    public void playSE(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}