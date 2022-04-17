package com.group10.app.main;

import com.group10.app.entity.Entity;
import com.group10.app.entity.nonStaticEntities.Inmate;

import com.group10.app.entity.nonStaticEntities.MovingActor;
import com.group10.app.entity.staticEntities.TileManager;
import com.group10.app.menu.MenuScreen;
import com.group10.app.menu.Menu;

import com.group10.app.SavedData.LoadGame;
import com.group10.app.SavedData.SaveGame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
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

    //The level the player is on
    private int GAME_LEVEL = 1;
    Boolean GAME_SAVED = false;

    //Load saved data
    public LoadGame loadGame = new LoadGame(this);

    //set up save game
    public SaveGame saveGame = new SaveGame(this);

    //Set up the keyboard keys
    public KeyManager keyH = new KeyManager();

    Thread gameThread;
    
    //set up the tiles
    public TileManager tileManage = new TileManager(this);
    
    //set player default position
    public Inmate inmate = new Inmate(this, keyH);

    //Set up the Mouse Keys
    public MouseManager mouseK = new MouseManager(this);

    public Menu menu = new MenuScreen(this);

    // Create guard array;
    public MovingActor[] guard = new MovingActor[5];

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
    public SoundManager soundEffect = new SoundManager();

    public static GameStates state = MENU;

    public int getGameLevel() {
        return GAME_LEVEL;
    }

    public void setGameLevel(int gameLevel) {
        GAME_LEVEL = gameLevel;
    }

    public void increaseLevel(){
        GAME_LEVEL++;
    }

    public GameStates getState() {
        return state;
    }

    public void setState(GameStates newState) {
        state = newState;
    }

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

        music.playMusic(0);

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
            for (MovingActor guard : guard) {
                if (guard != null){
                    if (collisionCheck.checkGuard(inmate, guard.getX(), guard.getY(), ENEMY_COLLISION_DISTANCE)) {
                        music.stopMusic();
                        soundEffect.playSE(4);
                        soundEffect.playSE(6);
                        System.out.println("ENEMY COLLIDED");
                        System.out.println("===================================");
                        state = GAMEOVER;
                    }
                }
            }

            // Time is up or score is negative
            if (inmate.isTimeOver() || inmate.isScoreNegative()) {
                music.stopMusic();
                soundEffect.playSE(6);
                state = GAMEOVER;
            }

            // Got all keys and reached the gate
            if(inmate.gotAllKeys() && inmate.reachedGate()) {
                music.stopMusic();
                soundEffect.playSE(7);
                state = GAMEWON;

                if (GAME_LEVEL == 3) {
                    File file = new File("src/main/SavedGame/saveFile.txt");
                    file.delete();
                }
            }
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
        else{
            menu = menu.checkMenuType();
            menu.renderMenu(g2);

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
        }else if (getGameLevel() == 2){
            inmate.setPos(610, 562);
        }else if (getGameLevel() == 3){
            inmate.setPos(110, 101);
        }else if (getGameLevel() == 4){
            inmate.setPos(296, 389);
        }

        tileManage.loadMap("/levels/Level" + GAME_LEVEL + ".txt");

        Arrays.fill(obj, null);
        Arrays.fill(guard, null);

        asset.setUpAsset();
        music.playMusic(8);
    }
}