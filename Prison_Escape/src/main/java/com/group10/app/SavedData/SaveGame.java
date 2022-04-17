package com.group10.app.SavedData;

import com.group10.app.entity.Entity;
import com.group10.app.entity.nonStaticEntities.MovingActor;
import com.group10.app.main.GamePanel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * SaveGame will save the players position, timer, score, number of keys collected, enemies position, and the players current level.
 * @author Ilia Fatemi
 */
public class SaveGame {
    private GamePanel gp;
    private String fileName;

    public SaveGame(GamePanel gp) {
        this.gp = gp;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * To save the map level and the Inmate's data
     * Using the first line of the file
     *
     * <p>
     *     For example: "1 279 717 2 96.76666666666685 50 2"
     *     "1" is Game Level
     *     "279 717" is the Inmate's position (x,y)
     *     "2" is the Inmate's speed
     *     "96.76666666666685" is the Inmate's rest of time
     *     "50" is the Inmate's score
     *     "2" is the Inmate's number of keys
     * </p>
     */
    public void saveInmate() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/SavedGame/" + fileName));
            bw.write(gp.getGameLevel() + " " + gp.inmate.getX() + " " + gp.inmate.getY() + " " +
                    gp.inmate.getSpeed() + " " + gp.inmate.getTimer() + " " +
                    gp.inmate.getScore() + " " + gp.inmate.getNumKeys());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred. Could not save inmate.");
            e.printStackTrace();
        }
    }

    /**
     * To save the Entity's data
     * The line that save first time is an integral that represent the number of Entities
     *
     * <p>
     *     For example: "19
     *                   Key 96 336
     *                   ..."
     *     "19" is total number of Entities
     *     "Key" is the kind of object
     *     "96 336" is the object's position
     * </p>
     */
    public void saveEntity() {
        try {
            File file = new File("src/main/SavedGame/" + fileName);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            int i = 0;
            for (Entity object : gp.obj) {
                if (object != null) {
                    i++;
                }
            }
            bw.write("" + i);
            bw.newLine();

            for (Entity object : gp.obj) {

                if (object != null) {
                    bw.write(object.name + " " + object.x + " " + object.y);

                    if (Objects.equals(object.name, "Chicken")) {
                        bw.write(" " + object.disappears);
                    }

                    bw.newLine();
                }
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred. Could not save Entity.");
            e.printStackTrace();
        }
    }

    /**
     * To save the Guard's data
     * The line that save first line is an integral that represent the number of Guard
     *
     * <p>
     *     For example: "3
     *                   960 96
     *                   672 720
     *                   144 144"
     *     "3" is total number of Guards
     *     "960 96" is the Guard's position
     * </p>
     */
    public void saveGuard() {
        try {
            File file = new File("src/main/SavedGame/" + fileName);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            int i = 0;
            for (Entity object : gp.guard) {
                if (object != null) {
                    i++;
                }
            }
            bw.write("" + i);
            bw.newLine();

            for (MovingActor object : gp.guard) {

                if (object != null) {
                    bw.write("" + object.getX() + " " + object.getY());

                    bw.newLine();
                }
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred. Could not save Guard.");
            e.printStackTrace();
        }
    }

    /**
     * Save all date to the file
     *
     * <p>
     *     Call setFileName method to setup the filename
     *     Save Inmate, Map, Entity, Guard by using saveInmate(), saveEntity(), saveGuard()
     * </p>
     *
     * @param fileName the path of the file
     */
    public void saveData(String fileName) {
        setFileName(fileName);
        saveInmate();
        saveEntity();
        saveGuard();
    }
}