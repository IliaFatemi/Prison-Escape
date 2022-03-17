package com.group10.app;

import javax.swing.*;
import com.group10.app.main.GamePanel;

/**
 * Hello world!
 *
 */
public class App{
    public static JFrame window;

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

        gamePanel.setUpAsset();
        gamePanel.startGameThread();
    }
    public static void main( String[] args ){
        new App();
    }
}
