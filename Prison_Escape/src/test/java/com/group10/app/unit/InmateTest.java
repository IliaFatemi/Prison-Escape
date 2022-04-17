package com.group10.app.unit;

import com.group10.app.entity.nonStaticEntities.Inmate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.group10.app.main.GamePanel;
import com.group10.app.main.GameStates;
import com.group10.app.main.KeyManager;


import org.junit.jupiter.api.BeforeEach;


public class InmateTest {
    GamePanel gp;
    KeyManager keyH;

    @BeforeEach
    void setUp(){
        gp = new GamePanel();
        gp.keyH = new KeyManager();
        gp.keyH.pressedUp = true;

        gp.setState(GameStates.GAME);

        gp.inmate = new Inmate(gp, gp.keyH);

        gp.inmate.setX(50);
        gp.inmate.setY(50);

    }

    @Test
    public void testPosition(){
        Inmate inmate = new Inmate(gp, keyH);
        inmate.setPos(50, 50);
        Assert.assertEquals(50, (int)inmate.getX());
        Assert.assertEquals(50, (int)inmate.getY());
        inmate.setPos(150, 550);
        Assert.assertEquals(150, (int)inmate.getX());
        Assert.assertEquals(550, (int)inmate.getY());
    }

    @Test
    public void testDirection(){
        Inmate inmate = new Inmate(gp, keyH);
        inmate.setDirection("up");
        Assert.assertEquals("up", inmate.getDirection());
        inmate.setDirection("down");
        Assert.assertEquals("down", inmate.getDirection());
        inmate.setDirection("left");
        Assert.assertEquals("left", inmate.getDirection());
        inmate.setDirection("right");
        Assert.assertEquals("right", inmate.getDirection());
    }

    @Test
    public void testSpeed(){
        Inmate inmate = new Inmate(gp, keyH);
        inmate.setSpeed(0);
        Assert.assertEquals(0, (int)inmate.getSpeed());
        inmate.setSpeed(2);
        Assert.assertEquals(2, (int)inmate.getSpeed());
        inmate.setSpeed(2000);
        Assert.assertEquals(2000, (int)inmate.getSpeed());
        inmate.setSpeed(-280);
        Assert.assertEquals(-280, (int)inmate.getSpeed());
    }

    @Test
    public void testNumKeys(){
        Inmate inmate = new Inmate(gp, keyH);
        Assert.assertEquals(0, inmate.getNumKeys());
        inmate.setNumKeys(50);
        Assert.assertEquals(50, inmate.getNumKeys());
        inmate.resetKeys();
        Assert.assertEquals(0, inmate.getNumKeys());
    }

    @Test
    public void testScore(){
        Inmate inmate = new Inmate(gp, keyH);
        Assert.assertEquals(0, inmate.getScore());
        inmate.setScore(900);
        Assert.assertEquals(900, inmate.getScore());
        inmate.resetScore();
        Assert.assertEquals(0, inmate.getScore());
    }

    @Test
    public void testTimer(){
        Inmate inmate = new Inmate(gp, keyH);
        Assert.assertEquals(100, inmate.getTimer(), 0.001);
        inmate.setTimer(10);
        Assert.assertEquals(10, inmate.getTimer(), 0.001);
    }

    @Test
    public void testReachedGate(){
        Inmate inmate = new Inmate(gp, keyH);
        Assert.assertEquals(false, inmate.reachedGate());
        inmate.setPos(1345, 300);
        Assert.assertEquals(true, inmate.reachedGate());
        inmate.setPos(1345, 100);
        Assert.assertEquals(false, inmate.reachedGate());
        inmate.setPos(1320, 300);
        Assert.assertEquals(false, inmate.reachedGate());
        inmate.setPos(100, 0);
        Assert.assertEquals(false, inmate.reachedGate());
    }

    @Test
    public void testGotAllKeys(){
        Inmate inmate = new Inmate(gp, keyH);
        gp.setGameLevel(1);
        Assert.assertEquals(false, inmate.gotAllKeys());
        inmate.setNumKeys(3);
        Assert.assertEquals(true, inmate.gotAllKeys());
        gp.setGameLevel(2);
        Assert.assertEquals(false, inmate.gotAllKeys());
        inmate.setNumKeys(4);
        Assert.assertEquals(true, inmate.gotAllKeys());
        gp.setGameLevel(3);
        Assert.assertEquals(false, inmate.gotAllKeys());
        inmate.setNumKeys(5);
        Assert.assertEquals(true, inmate.gotAllKeys());
        gp.setGameLevel(4);
        Assert.assertEquals(false, inmate.gotAllKeys());
    }
    
    @Test
    public void testIsTimeOver(){
        Inmate inmate = new Inmate(gp, keyH);
        Assert.assertEquals(false, inmate.isTimeOver());
        inmate.setTimer(50);
        Assert.assertEquals(false, inmate.isTimeOver());
        inmate.setTimer(0);
        Assert.assertEquals(true, inmate.isTimeOver());
        inmate.setTimer(-50);
        Assert.assertEquals(true, inmate.isTimeOver());
    }

    @Test
    public void testIsScoreNegative(){
        Inmate inmate = new Inmate(gp, keyH);
        Assert.assertEquals(false, inmate.isScoreNegative());
        inmate.setScore(50);
        Assert.assertEquals(false, inmate.isScoreNegative());
        inmate.setScore(0);
        Assert.assertEquals(false, inmate.isScoreNegative());
        inmate.setScore(-30);
        Assert.assertEquals(true, inmate.isScoreNegative());
    }

    @Test
    public void testUpdate(){
        keyH = new KeyManager();
        Inmate inmate = new Inmate(gp, keyH);
        keyH.keySet("up");
        inmate.updateDirection();
        if (keyH.pressedUp) {
            Assert.assertEquals("up", inmate.getDirection());
        } else if (keyH.pressedDown) {
            Assert.assertEquals("down", inmate.getDirection());
        } else if (keyH.pressedLeft) {
            Assert.assertEquals("left", inmate.getDirection());
        } else {
            Assert.assertEquals("right", inmate.getDirection());
        }
    }

    @Test
    public void testReset(){
        Inmate inmate = new Inmate(gp, keyH);
        inmate.resetInmate();
        Assert.assertEquals(100, inmate.getTimer(), 0.0001);
        Assert.assertEquals(0, inmate.getScore());
        Assert.assertEquals(0, inmate.getNumKeys());
    }
}
