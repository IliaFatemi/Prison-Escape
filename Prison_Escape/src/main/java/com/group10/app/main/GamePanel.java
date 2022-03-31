package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.entity.nonStatisEntities.Inmate;

import com.group10.app.entity.staticEntities.TileManager;
import com.group10.app.menu.GameOverMenu;
import com.group10.app.menu.MenuScreen;
import com.group10.app.menu.PauseMenu;
import com.group10.app.menu.WonMenu;

import com.group10.app.SavedData.LoadGame;
import com.group10.app.SavedData.SaveGame;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static com.group10.app.main.GameStates.*;

/**
 * GamePanel is responsible for running all the game.
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
     * screenWidth is 1440 pixels
     */
    public final int screenWidth = cellSize * screenColNumber;

    /**
     * screenWidth is 864 pixels
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
    LoadGame load = new LoadGame(this);

    //set up save game
    SaveGame saveGame = new SaveGame();

    //Set up the keyboard keys
    KeyManager keyH = new KeyManager(this);

    Thread gameThread;
    
    //set up the tiles
    public TileManager tileManage = new TileManager(this);
    
    //set player default position
    public Inmate inmate = new Inmate(this, keyH);

    //Set up the Mouse Keys
    MouseManager mouseK = new MouseManager(this);

    //Set up the main menu screen 
    MenuScreen mainMenu = new MenuScreen(this);

    //set up the pause menu
    PauseMenu pauseMenu = new PauseMenu(this);

    //set up the win screen
    WonMenu wonMenu = new WonMenu(this);

    //set up game over screen
    GameOverMenu gameOver = new GameOverMenu(this);

    // Create guard array;
    public Entity[] guard = new Entity[5];

    // Create object array;
    public Entity[] obj = new Entity[30];
    
    // Set up asset;
    public EntityManager asset = new EntityManager(this);
    
    // Set up collision check;
    public CollisionManager collisionCheck = new CollisionManager(this);

    // Set up UI
    public GameDisplay ui = new GameDisplay(this);

    // Set up music and sound effect
    public SoundManager music = new SoundManager();

    public static GameStates state = MENU;

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
     * Setting up the game thread
     */
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * <p>The run method is the main loop for the game.</p>
     */
    public void run() {
        double drawInterval = 1000000000/ framePerSecond;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            update();
            //render graphics
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

    /**
     * <p>Updating the player, objects and guards when the state of the game is GAME.</p>
     */
    public void update(){
        if(state == GAME){
            inmate.update();
            asset.update();
            // Guard
            for (Entity entity : guard) {
                if (entity != null) {
                    entity.update();
                }
            }

            // Guard collision
            for (Entity entity : guard) {
                if (entity != null){
                    if (collisionCheck.checkGuard(inmate, entity.x, entity.y, ENEMY_COLLISION_DISTANCE)) {
                        System.out.println("ENEMY COLLIDED");
                        System.out.println("===================================");
                        state = GAMEOVER;
                    }
                }
            }

            // Time is up or score is negative
            if (inmate.isTimeOver() || inmate.isScoreNegative()) {
                state = GAMEOVER;
            }

            // Got all keys and reached the gate
            if(inmate.gotAllKeys() && inmate.reachedGate()){state = GAMEWON;}
        }

    }

    /**
     * Draw for different State.
     *
     * <p>
     *     For STATE GAME, draw tiles first by using tileManage.draw
     *                      then draw objects, guards and inmate in the screen.
     *     For STATE PAUSE, MENU, GAMEWON, GAMEOVER, draw corresponding picture.
     * </p>
     * @param graphic passing graphic to charge as a parameter
     */
    public void paintComponent(Graphics graphic){
        super.paintComponent(graphic);
        Graphics2D g2 = (Graphics2D) graphic;
        
        if (state == GAME){
    
            //draw tiles
            tileManage.draw(g2);

            // Draw objects
            for (Entity entity : obj) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
    
            // Draw guards
            for (Entity entity : guard) {
                if (entity != null){
                    entity.draw(g2);
                }
            }
    
            //Draw the inmate
            inmate.draw(g2);

            // Draw UI
            ui.draw(g2);

            g2.dispose();
        }
        else if(state == PAUSED){
            //render the pause menu
            pauseMenu.renderPauseMenu(g2);
            g2.dispose();
        }
        else if (state == MENU){
            //Render the main menu
            mainMenu.renderMain(g2);
            g2.dispose();
        }
        else if (state == GAMEWON){
            //render the game won menu
            wonMenu.renderWonGraphics(g2);
            g2.dispose();
        }
        else if (state == GAMEOVER){
            //render game over menu
            gameOver.renderGameOverMenu(g2);
            g2.dispose();
        }
    }

    /**
     * <p>
     *     Setting up the level accordingly to each level.
     *     Setting up inmate's position for different level
     *     This method can be used to New Game and Restart
     * </p>
     */
    public void levelCheck(){
        GamePanel.state = GAME;

        inmate.resetInmate();
        if(GAME_LEVEL == 1){
            inmate.setPos(279, 717);
        }else if (GamePanel.GAME_LEVEL == 2){
            inmate.setPos(610, 562);
        }else if (GamePanel.GAME_LEVEL == 3){
            inmate.setPos(110, 101);
        }

        tileManage.loadMap("/levels/Level" + GAME_LEVEL + ".txt");

        if (music.clip != null){
            music.stopMusic();
        }

        Arrays.fill(obj, null);
        Arrays.fill(guard, null);

        asset.setUpAsset();
    }
}