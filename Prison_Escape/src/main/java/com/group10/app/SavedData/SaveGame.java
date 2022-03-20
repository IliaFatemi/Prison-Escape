package com.group10.app.SavedData;

import java.io.FileWriter; 
import java.io.IOException;

/**
 * SaveGame will save the players position, timer, score, number of keys collected, enemies position, and the players current level.
 * @author Ilia Fatemi
 */
public class SaveGame {
    /**
     * <p>save will collect the players information at the current state and will save it into a text file called save0.txt</p>
     * @param level An integer for the level
     * @param playerX An integer for players X position
     * @param playerY An integer for players Y position
     * @param timer An integer for the timer 
     * @param score An integer for the score
     * @param numKeys An integer for the number of keys collected on the map
     * @param enemyX An integer for the ememies X position
     * @param enemyY An integer for the enemies Y position 
     */
    public void save(int level, int playerX, int playerY, int timer, int score, int numKeys, int enemyX, int enemyY){
        try {
            FileWriter myWriter = new FileWriter("src/main/SavedGame/save0.txt");
            myWriter.write(level + " " + playerX + " " + playerY + " " + timer + " " + score + " " + numKeys + " " + enemyX + " " + enemyY);
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred. Could not save game.");
            e.printStackTrace();
          }
    }
    
}
