package com.group10.app.it;

import com.group10.app.SavedData.SaveGame;
import com.group10.app.entity.nonStaticEntities.Inmate;
import com.group10.app.main.GamePanel;
import com.group10.app.main.KeyManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SaveLoadTestIT {
    private GamePanel gp;

    @BeforeEach
    void setUp() {
        gp = new GamePanel();
        gp.keyH = new KeyManager();
        gp.inmate = new Inmate(gp, gp.keyH);
        gp.saveGame = new SaveGame(gp);

    }

    @Test
    public void justTest() {
        gp.levelCheck();
        gp.saveGame.setFileName("test.txt");
        gp.saveGame.saveInmate();
        gp.saveGame.saveEntity();
        gp.saveGame.saveGuard();

    }
}
