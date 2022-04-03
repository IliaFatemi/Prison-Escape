package com.group10.app.it;


import com.group10.app.entity.nonStaticEntities.Guard;
import com.group10.app.entity.nonStaticEntities.Inmate;
import com.group10.app.entity.staticEntities.Chicken;
import com.group10.app.entity.staticEntities.Door;
import com.group10.app.entity.staticEntities.Key;
import com.group10.app.entity.staticEntities.Timer;
import com.group10.app.main.GamePanel;
import com.group10.app.main.GameStates;
import com.group10.app.main.KeyManager;

import org.junit.Assert;
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

        gp.setState(GameStates.GAME);

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

    @Test
    public void inmateCollidesWithClock(){
        Timer timer = new Timer(gp);
        timer.setX(50);
        timer.setY(50);
        gp.obj[0] = timer;

        inmate.update();

        assertEquals(120, inmate.getTimer());
    }

    @Test
    public void inmateCollidesWithKey(){
        Key key = new Key(gp);

        key.setX(50);
        key.setY(50);
        gp.obj[0] = key;

        inmate.update();
        assertEquals(1, inmate.getNumKeys());
        assertEquals(250, inmate.getScore());
    }

    @Test
    public void inmateCollidesWithGuard(){
        Guard guard = new Guard(gp);


        guard.setX(50);
        guard.setY(50);

        gp.guard[0] = guard;

        gp.update();


        assertEquals(GameStates.GAMEOVER,gp.getState());
        assertEquals(0, inmate.getScore());
        assertEquals(0, inmate.getTimer());
        assertEquals(0, inmate.getNumKeys());
    }

    @Test
    public void inmateCollidesWithDoor(){
        Door door = new Door(gp);
        Key key1 = new Key(gp);
        key1.setX(0);
        key1.setY(51);

        Key key2 = new Key(gp);
        key2.setX(0);
        key2.setY(52);

        Key key3 = new Key(gp);
        key3.setX(0);
        key3.setY(53);

        gp.setGameLevel(1);

        door.setX(0);
        door.setY(50);

        gp.obj[0] = door;

        inmate.update();
        gp.update();

        assertEquals(GameStates.GAME,gp.getState());
        assertEquals(200, inmate.getScore());
        assertEquals(100, inmate.getTimer());
        assertEquals(0, inmate.getNumKeys());

        inmate.setX(0);
        inmate.setY(51);
        gp.obj[1] = key1;
        inmate.update();

        inmate.setX(0);
        inmate.setY(52);
        gp.obj[2] = key2;
        inmate.update();

        inmate.setX(0);
        inmate.setY(53);
        gp.obj[3] = key3;

        inmate.update();
        gp.update();

        assertEquals(GameStates.GAMEWON,gp.getState());
        assertEquals(0, inmate.getScore());
        assertEquals(0, inmate.getTimer());
        assertEquals(0, inmate.getNumKeys());
        assertEquals(0, -1);


    }


}
