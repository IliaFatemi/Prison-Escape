package com.group10.app;

import javax.swing.*;
import com.group10.app.main.GamePanel;

/**
 * class the runs the game
 *
 * <p>
 *     The App class created the main window visible to the users when they play the game. Most of the
 *     games initial conditions like the title and main game thread are created in the constructor of this class.
 * </p>
 */
public class App{
    public static JFrame window;

    /**
     * The constructor for the App class used for the initializations and set up of the game window
     */
    public App(){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Prison Escape");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }

    public static void main( String[] args ){
        new App();
    }
}
