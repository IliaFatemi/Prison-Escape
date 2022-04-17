package com.group10.app.it;

import com.group10.app.SavedData.LoadGame;
import com.group10.app.SavedData.SaveGame;
import com.group10.app.entity.nonStaticEntities.Inmate;
import com.group10.app.main.GamePanel;
import com.group10.app.main.KeyManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveLoadTestIT {
    private GamePanel gp;

    @BeforeEach
    void setUp() {
        gp = new GamePanel();
        gp.keyH = new KeyManager();
        gp.inmate = new Inmate(gp, gp.keyH);
        gp.saveGame = new SaveGame(gp);
        gp.loadGame = new LoadGame(gp);
    }

    @Test
    public void fileNameTest() {
        gp.saveGame.setFileName("SaveTest");
        assertEquals("SaveTest", gp.saveGame.getFileName());

        gp.loadGame.setFileName("LoadTest");
        assertEquals("LoadTest", gp.loadGame.getFileName());
    }

    @Test
    public void saveLoadInmateLevel1Test() throws IOException {
        gp.saveGame.setFileName("test.txt");
        gp.setGameLevel(1);
        gp.levelCheck();
        gp.saveGame.saveInmate();
        InputStream level = new FileInputStream("src/main/SavedGame/" + gp.saveGame.getFileName());
        BufferedReader br = new BufferedReader(new InputStreamReader(level));
        String line = br.readLine();
        assertEquals("1 279 717 2 100.0 0 0", line);
        br.close();
    }

    @Test
    public void saveInmateLevel2Test() throws IOException {
        gp.saveGame.setFileName("test.txt");
        gp.setGameLevel(2);
        gp.levelCheck();
        gp.saveGame.saveInmate();
        InputStream level = new FileInputStream("src/main/SavedGame/" + gp.saveGame.getFileName());
        BufferedReader br = new BufferedReader(new InputStreamReader(level));
        String line = br.readLine();
        assertEquals("2 610 562 2 100.0 0 0", line);
        br.close();
    }

    @Test
    public void saveInmateLevel3Test() throws IOException {
        gp.saveGame.setFileName("test.txt");
        gp.setGameLevel(3);
        gp.levelCheck();
        gp.saveGame.saveInmate();
        InputStream level = new FileInputStream("src/main/SavedGame/" + gp.saveGame.getFileName());
        BufferedReader br = new BufferedReader(new InputStreamReader(level));
        String line = br.readLine();
        assertEquals("3 110 101 2 100.0 0 0", line);
        br.close();
    }

    @Test
    public void loadInmateLevel1Test() throws IOException {
        gp.setGameLevel(1);
        gp.levelCheck();
        gp.saveGame.saveData("test.txt");
        gp.loadGame.loadData("test.txt");
        assertEquals(1, gp.getGameLevel());
        assertEquals(279, gp.inmate.getX());
        assertEquals(717, gp.inmate.getY());
        assertEquals(2, gp.inmate.getSpeed());
        assertEquals(100, gp.inmate.getTimer(), 0.00001);
        assertEquals(0, gp.inmate.getScore());
        assertEquals(0, gp.inmate.getNumKeys());
    }

    @Test
    public void loadInmateLevel2Test() throws IOException {
        gp.setGameLevel(2);
        gp.levelCheck();
        gp.saveGame.saveData("test.txt");
        gp.loadGame.loadData("test.txt");
        assertEquals(2, gp.getGameLevel());
        assertEquals(610, gp.inmate.getX());
        assertEquals(562, gp.inmate.getY());
        assertEquals(2, gp.inmate.getSpeed());
        assertEquals(100, gp.inmate.getTimer(), 0.00001);
        assertEquals(0, gp.inmate.getScore());
        assertEquals(0, gp.inmate.getNumKeys());
    }

    @Test
    public void loadInmateLevel3Test() throws IOException {
        gp.setGameLevel(3);
        gp.levelCheck();
        gp.saveGame.saveData("test.txt");
        gp.loadGame.loadData("test.txt");
        assertEquals(3, gp.getGameLevel());
        assertEquals(110, gp.inmate.getX());
        assertEquals(101, gp.inmate.getY());
        assertEquals(2, gp.inmate.getSpeed());
        assertEquals(100, gp.inmate.getTimer(), 0.00001);
        assertEquals(0, gp.inmate.getScore());
        assertEquals(0, gp.inmate.getNumKeys());
    }
}
