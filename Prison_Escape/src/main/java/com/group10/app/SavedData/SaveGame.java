package com.group10.app.SavedData;

import com.group10.app.entity.Entity;
import com.group10.app.entity.nonStaticEntities.MovingActor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

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
     */
    public void save(int level, int playerX, int playerY, int speed, double timer, int score, int numKeys, Entity[] objects, MovingActor[] guards){
        try {
            FileWriter myWriter = new FileWriter("src/main/SavedGame/save0.txt");
            myWriter.write(level + " " + playerX + " " + playerY + " " + speed + " " + timer + " " + score + " " + numKeys + " ");
            myWriter.close();

            BufferedWriter bw = new BufferedWriter( new FileWriter("src/main/SavedGame/saveEntity.txt"));
            int i = 0;
            for (Entity object : objects) {
                if (object != null){
                    i++;
                }
            }
            bw.write("" + i);

            bw.newLine();
            for (Entity object : objects) {

                if (object != null){
                    bw.write(object.name);
                    bw.newLine();
                    bw.write("" + object.x);
                    bw.newLine();
                    bw.write("" + object.y);
                    bw.newLine();

                    if (Objects.equals(object.name, "Chicken")){
                        bw.write("" + object.disappears);
                        bw.newLine();
                    }
                }
            }

            i = 0;
            for (MovingActor movingActor : guards) {
                if (movingActor != null) {
                    i++;
                }
            }
            bw.write("" + i);
            bw.newLine();
            for (MovingActor guard : guards) {
                if (guard != null) {
                    bw.write("" + guard.getX());
                    bw.newLine();
                    bw.write("" + guard.getY());
                    bw.newLine();
                }
            }

            bw.close();

          } catch (IOException e) {
            System.out.println("An error occurred. Could not save game.");
            e.printStackTrace();
          }
    }
    
}
