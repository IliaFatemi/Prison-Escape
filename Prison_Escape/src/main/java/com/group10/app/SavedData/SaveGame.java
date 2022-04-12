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

    public void mySave(String fileName) {
        setFileName(fileName);
        saveInmate();
        saveEntity();
        saveGuard();
    }
}