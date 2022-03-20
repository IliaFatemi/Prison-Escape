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

/**
 * GamePanel is resposible for running all of the game.
 */
public class GamePanel extends JPanel implements Runnable{
   
    /**
     * originalCellSize pixel
     */
    final int originalCellSize = 12;

    /**
     * scaleFactor is set to 4
     */
    final int scaleFactor = 4;

    /**
     * Each block is 48x48
     */
    public final int cellSize = originalCellSize * scaleFactor;

    /**
     * Screen column size of the window is 30
     */
    public final int screenColNumber = 30;

    /**
     * Screen rows size of the window is 18
     */
    public final int screenRowNumber = 18;

    /**
     * scrennWidth is 1440 pixels
     */
    public final int screenWidth = cellSize * screenColNumber;

    /**
     * scrennWidth is 864 pixels
     */
    public final int screenHeight = cellSize * screenRowNumber;

    /**
     * FPS 
     */
    final int framePerSecond = 60;

    /**
     * The distance where the player and enemy will collide at
     */
    int ENEMY_COLLISION_DISTANCE = 40;

    /**
     * Level 1 file location
     */
    String LEVEL1 = "/levels/Level1.txt";

    /**
     * Level 2 file location
     */
    String LEVEL2 = "/levels/Level2.txt";

    /**
     * Level 3 file location
     */
    String LEVEL3 = "/levels/Level3.txt";

    //The level the player is on
    public static int GAME_LEVEL = 1;
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
    Guard guard = new Guard(this, 302, 160);
    Guard guard1 = new Guard(this, 565, 424);
    Guard guard2 = new Guard(this, 565, 424);

    //Set up the main menu screen 
    MenuScreen mainMenu = new MenuScreen(this);

    //set up the pause menu
    PauseMenu pauseMenu = new PauseMenu(this);

    //set up the win screen
    WonMenu wonMenu = new WonMenu(this);

    //set up game over screen
    GameOverMenu gameOver = new GameOverMenu(this);
    
    // Create object array;
    public Entity[] obj = new Entity[30];
    
    // Set up asset;
    public ObjectManager asset = new ObjectManager(this);
    
    // Set up collision check;
    public Collision collisionCheck = new Collision(this);

    // Set up UI
    public GameDisplay ui = new GameDisplay(this);

    // Set up music and sound effect
    Sound music = new Sound();
    Sound soundEffect = new Sound();

    /**
     * The different states throughout the game
     */
    public static enum STATE{MENU, GAME, EXIT, PAUSED, GAMEOVER, GAMEWON, RETRY}

    /**
     * Initial state of the game will start at the main menu
     */
    public static STATE state = STATE.MENU;

    /**
     * Initializing the background, mouse keys, keyboard, screen size, 
     */
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseK);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     * <p>Setting up the objects such as timer, keys, traps and drum sticks for each level in the game.</p>
     */
    public void setUpAsset() {
        if (GAME_LEVEL == 1){asset.setObjectLevel1();}
        else if (GAME_LEVEL == 2){asset.setObjectLevel2();}
        else if (GAME_LEVEL == 3){asset.setObjectLevel3();}
        playMusic(0);
    }

    /**
     * Setting up the game thread
     */
    public void startGameThread(){
            gameThread = new Thread(this);
            gameThread.start();
    }
 
    /**
     * Setting up collision between two objects. 
     * @param inmate The player object
     * @param objectX position X of the object
     * @param objectY position Y of the object
     * @param collision_type The collision distance between the player and the object
     * @return boolean
     */
    public boolean isCollision(Inmate inmate, double objectX, double objectY, int collision_type){   
        double distance = sqrt(pow(inmate.getX() - objectX, 2) + pow(inmate.getY() - objectY, 2));
        return distance <= collision_type;
    }

    /**
     * <p>if the player is within the area of the gate the mehtod will return true, otherwise false</p>
     * @return boolean
     */
    public boolean reachedGate(){
        return inmate.getX() >= 1344 && inmate.getX() <= 1350 && inmate.getY() >= 292 && inmate.getY() <= 544;
    }

    /**
     * <p>Based on the level of the player, the gotAllKeys will check if the player collect the right amount of keys for a specific level</p>
     * @param level The level where to check the constraint
     * @return boolean
     */
    public boolean gotAllKeys(int level){
        if(level == 1 && inmate.getNumKeys() >= 3){return true;}
        else if(level == 2 && inmate.getNumKeys() >= 4){return true;}
        else if(level == 3 && inmate.getNumKeys() >= 5){return true;}
        return false;
    }

    /**
     * <p>If the time has reached zero, the method will return true.</p>
     * @return boolean
     */
    public boolean isTimeOver(){
        if(inmate.getTimer() <= 0){
            System.out.println("Time reached zero");
            return true;
        }
        return false;
    }

    /**
     * isScoreNegative will be true if the score has reached a negative number
     * @return boolean
     */
    public boolean isScoreNegative(){
        if(inmate.getScore() < 0){
            System.out.println("Score is negative");
            return true;
        }
        return false;
    }

    /**
     * <p>The run method is the main loop for the game.</p>
     */
    public void run() {
        double drawInterval = 1000000000/ framePerSecond;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            //render graphics
            repaint();
    
            //Pause the game if pause menu is active
            update();
            if(state != STATE.PAUSED && state != STATE.MENU && state != STATE.GAMEWON && state != STATE.GAMEOVER){

                //guard collision or time is up
                if (isCollision(inmate, guard.getX(), guard.getY(), ENEMY_COLLISION_DISTANCE) || isTimeOver() || isScoreNegative()){
                    System.out.println("ENEMY COLLIDED");
                    System.out.println("===================================");
                    state = STATE.GAMEOVER;
                    inmate.resetKeys();
                }
                else if (isCollision(inmate, guard1.getX(), guard1.getY(), ENEMY_COLLISION_DISTANCE)){
                    System.out.println("ENEMY COLLIDED");
                    System.out.println("===================================");
                    state = STATE.GAMEOVER;
                    inmate.resetKeys();
                }
                else if (isCollision(inmate, guard2.getX(), guard2.getY(), ENEMY_COLLISION_DISTANCE)){
                    System.out.println("ENEMY COLLIDED");
                    System.out.println("===================================");
                    state = STATE.GAMEOVER;
                    inmate.resetKeys();
                }

                //got all keys and reached the gate
                if(gotAllKeys(GAME_LEVEL) && reachedGate()){state = STATE.GAMEWON;}

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

    /**
     * <p>Updating the player, enemy and the world objects when the state of the game is GAME.</p>
     */
    public void update(){
        if(state == STATE.GAME){
            inmate.update();
            asset.update();
            guard.update(inmate.x, inmate.y);
            guard1.update(inmate.x, inmate.y);
            guard2.update(inmate.x, inmate.y);
        }
    }

    public void paintComponent(Graphics graphic){
        super.paintComponent(graphic);
        Graphics2D g2 = (Graphics2D) graphic;
        
        if (state == STATE.GAME){
    
            //draw tiles
            tileManage.draw(g2);

            // Draw objects
            for (Entity entity : obj) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
    
            //Draw guard
            guard.draw(g2);
            guard1.draw(g2);
            guard2.draw(g2);
    
            //Draw the inmate
            inmate.draw(g2);

            // Draw UI
            ui.draw(g2);

            g2.dispose();
        }
        else if(state == STATE.PAUSED){
            //render the pause menu
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

    /**
     * <p>Setting up the level accordingly to each level. This method can be used to check the level after states of the has been updated</p>
     */
    public void levelCheck(){
        if(GAME_LEVEL == 1){
            tileManage.loadMap(LEVEL1);
            inmate.setPos(279, 717);
            inmate.resetKeys();
            inmate.resetScore();
            inmate.setTimer(100);
            guard.setGuardValues(302, 160);
            guard1.setGuardValues(1297, 115);
            guard2.setGuardValues(250, 365);
        }else if (GamePanel.GAME_LEVEL == 2){
            inmate.setPos(610, 562);
            inmate.resetKeys();
            inmate.resetScore();
            inmate.setTimer(100);
            tileManage.loadMap(LEVEL2);
        }else if (GamePanel.GAME_LEVEL == 3){
            inmate.setPos(110, 101);
            inmate.resetKeys();
            inmate.resetScore();
            inmate.setTimer(100);
            tileManage.loadMap(LEVEL3);
        }
        GamePanel.state = STATE.GAME;

        if (music.clip != null){
            stopMusic();
        }
        setUpAsset();
    }
}