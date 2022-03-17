package com.group10.app.SavedData;

import java.io.FileWriter; 
import java.io.IOException;

public class SaveGame {
    // index 0: level
    // index 1: player x cor
    // index 2: player y cor
    // index 3: timer
    // index 4: score
    // index 5: number of keys
    // index 6: enemy x cor
    // index 7: enemy y cor
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
