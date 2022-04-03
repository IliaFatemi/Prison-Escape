package com.group10.app.it;


import com.group10.app.entity.nonStaticEntities.Inmate;
import com.group10.app.entity.staticEntities.Chicken;
import com.group10.app.main.GamePanel;
import com.group10.app.main.KeyManager;
import com.group10.app.main.GameStates.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InmateCollisionsIT {
    private GamePanel gp;
    private KeyManager key;
    private Inmate inmate;

    @BeforeEach
    void setUp(){
        gp = new GamePanel();
        key = new KeyManager();
        key.pressedUp = true;

        inmate = new Inmate(gp, key);

        inmate.setX(50);
        inmate.setY(50);

        inmate.setScore(200);
        inmate.setTimer(100);
    }

    @Test
    public void inmateCollidesWithChicken(){
        Chicken chicken = new Chicken(gp);
        chicken.setX(50);
        chicken.setY(50);
        gp.obj[0] = chicken;

        inmate.update();

        assertEquals(300, inmate.getScore());

    }
}
